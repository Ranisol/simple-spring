package simple.spring.context.event;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import simple.spring.context.event.service_texture.entity.Orders;
import simple.spring.context.event.service_texture.repository.OrdersRepository;
import simple.spring.context.event.with_inheritence.SimpleOrderService;

import java.util.List;

@SpringBootTest
public class WithEventInheritenceTest {
    @Autowired
    SimpleOrderService simpleOrderService;
    @Autowired
    OrdersRepository ordersRepository;

    @AfterEach
    public void cleanup() {
        ordersRepository.deleteAll();
    }

    // SimpleOrderService 이벤트 발행 -> SimpleMailEventListener 이벤트 수신
    @Test
    public void testEvent() {
        simpleOrderService.createOrder();
        List<Orders> list = ordersRepository.findAll();
        Assertions.assertEquals(0, list.size());
    }
}
