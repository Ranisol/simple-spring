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
import simple.spring.context.event.with_eventlistener.*;

import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WithEventListenerTest {
    @Autowired
    SimpleOrderService simpleOrderService;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UserPointRepository userPointRepository;
    @SpyBean
    SimpleOrderCreateEventHandlers handlers;

    @AfterEach
    public void cleanup() {
        ordersRepository.deleteAll();
        userPointRepository.deleteAll();
    }

    @Test
    public void successEvent() {
        simpleOrderService.createOrder();
        List<Orders> list = ordersRepository.findAll();
        Assertions.assertEquals(1, list.size());
        Mockito.verify(handlers, Mockito.times(1)).sendMail(Mockito.any(SimpleOrderCreateEvent.class));
        Mockito.verify(handlers, Mockito.times(1)).sendRealtimeStatics(Mockito.any(SimpleOrderCreateEvent.class));
        Mockito.verify(handlers, Mockito.times(1)).sendPoint(Mockito.any(SimpleOrderCreateEvent.class));
    }

    // 동기적으로 작동하고 같은 트랜잭션 공유 => 중간에 터지면, 이벤트 퍼블리셔 롤백 + 이벤트 리스터 롤백 + 이후 이벤트 호출되지 않음
    @Test
    public void staticsFailEvent() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            simpleOrderService.createOrderWithRealStaticsFail();
        });
        List<Orders> list = ordersRepository.findAll();
        List<UserPoint> userPoints = userPointRepository.findAll();

        Assertions.assertEquals(0, list.size());
        Assertions.assertEquals(0, userPoints.size());

        Mockito.verify(handlers, Mockito.times(1)).sendRealtimeStatics(Mockito.any(SimpleOrderCreateStaticsFailEvent.class));
        Mockito.verify(handlers, Mockito.times(1)).sendPoint(Mockito.any(SimpleOrderCreateStaticsFailEvent.class));
        Mockito.verify(handlers, Mockito.times(0)).sendMail(Mockito.any(SimpleOrderCreateStaticsFailEvent.class));
    }

    @Test
    public void pointFailEvent() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            simpleOrderService.createOrderWithPointFail();
        });
        List<Orders> list = ordersRepository.findAll();
        List<UserPoint> userPoints = userPointRepository.findAll();
        Assertions.assertEquals(0, list.size());
        Assertions.assertEquals(0, userPoints.size());

        Mockito.verify(handlers, Mockito.times(1)).sendPoint(Mockito.any(SimpleOrderCreatePointFailEvent.class));
        Mockito.verify(handlers, Mockito.times(0)).sendRealtimeStatics(Mockito.any(SimpleOrderCreatePointFailEvent.class));
        Mockito.verify(handlers, Mockito.times(0)).sendMail(Mockito.any(SimpleOrderCreatePointFailEvent.class));
    }

}
