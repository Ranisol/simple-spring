package simple.spring.context.event;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import simple.spring.context.event.service_texture.entity.Orders;
import simple.spring.context.event.service_texture.entity.UserPoint;
import simple.spring.context.event.service_texture.repository.OrdersRepository;
import simple.spring.context.event.service_texture.repository.UserPointRepository;
import simple.spring.context.event.with_transactional_eventlistener.*;

import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WithTransactionalEventListenerTest {
    @Autowired
    SimpleOrderService simpleOrderService;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UserPointRepository userPointRepository;
    @SpyBean
    SimpleOrderCreateEventTransactionalHandlers handlers;

    @BeforeEach
    public void cleanup() {
        ordersRepository.deleteAll();
        userPointRepository.deleteAll();
    }

    @Test
    public void successEvent() throws InterruptedException {
        simpleOrderService.createOrder();
        List<Orders> list = ordersRepository.findAll();
        Thread.sleep(2000);
        Assertions.assertEquals(1, list.size());
        Mockito.verify(handlers, Mockito.times(1)).sendMail(Mockito.any(SimpleOrderCreateEvent.class));
        Mockito.verify(handlers, Mockito.times(1)).sendRealtimeStatics(Mockito.any(SimpleOrderCreateEvent.class));
        Mockito.verify(handlers, Mockito.times(1)).sendPoint(Mockito.any(SimpleOrderCreateEvent.class));
    }

    // 비동기적으로 작동하고 다른 트랜잭션 공유 => 중간에 터지면, 이벤트 퍼블리셔 온전  + 터진 이벤트 리스너 롤백 + 모든 이벤트 호출
    @Test
    public void staticsFailEvent() throws InterruptedException {
        Assertions.assertDoesNotThrow(() -> {
            simpleOrderService.createOrderWithRealStaticsFail();
        });
        Thread.sleep(2000);
        List<Orders> list = ordersRepository.findAll();
        List<UserPoint> userPoints = userPointRepository.findAll();

        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(1, userPoints.size());

        Mockito.verify(handlers, Mockito.times(1)).sendRealtimeStatics(Mockito.any(SimpleOrderCreateStaticsFailEvent.class));
        Mockito.verify(handlers, Mockito.times(1)).sendPoint(Mockito.any(SimpleOrderCreateStaticsFailEvent.class));
        Mockito.verify(handlers, Mockito.times(1)).sendMail(Mockito.any(SimpleOrderCreateStaticsFailEvent.class));
    }

    @Test
    public void pointFailEventBeforeCommit() throws InterruptedException {
        Assertions.assertThrows(RuntimeException.class, () -> {
            simpleOrderService.createOrderWithPointFail();
        });
        Thread.sleep(2000);
        List<Orders> list = ordersRepository.findAll();
        List<UserPoint> userPoints = userPointRepository.findAll();

        Assertions.assertEquals(0, list.size());
        Assertions.assertEquals(0, userPoints.size());

        Mockito.verify(handlers, Mockito.times(1)).sendPoint(Mockito.any(SimpleOrderCreatePointFailEvent.class));
        Mockito.verify(handlers, Mockito.times(0)).sendRealtimeStatics(Mockito.any(SimpleOrderCreatePointFailEvent.class));
        Mockito.verify(handlers, Mockito.times(0)).sendMail(Mockito.any(SimpleOrderCreatePointFailEvent.class));
    }

    @Test
    public void pointFailEventAfterCommit() {
        Assertions.assertDoesNotThrow(() -> {
            simpleOrderService.createOrderWithPointFail();
        });
        List<Orders> list = ordersRepository.findAll();
        List<UserPoint> userPoints = userPointRepository.findAll();

        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(0, userPoints.size());

        Mockito.verify(handlers, Mockito.times(1)).sendPoint(Mockito.any(SimpleOrderCreatePointFailEvent.class));
        Mockito.verify(handlers, Mockito.times(1)).sendRealtimeStatics(Mockito.any(SimpleOrderCreatePointFailEvent.class));
        Mockito.verify(handlers, Mockito.times(1)).sendMail(Mockito.any(SimpleOrderCreatePointFailEvent.class));
    }
}
