package simple.spring.context.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import simple.spring.context.event.service.SimpleMailService;
import simple.spring.context.event.service.SimplePointService;
import simple.spring.context.event.service.SimpleRealtimeStaticsService;
import simple.spring.context.event.withannotation.SimpleOrderCreateEventHandlers;
import simple.spring.context.event.withannotation.SimpleOrderService;

@SpringBootTest(classes = {
        SimpleOrderCreateEventHandlers.class,
        SimpleOrderService.class,
        SimpleMailService.class,
        SimplePointService.class,
        SimpleRealtimeStaticsService.class
})
public class OrderCreateEventWithAnnotationTest {
    @Autowired
    SimpleOrderService simpleOrderService;

    // SimpleOrderService 이벤트 발행 -> SimpleMailEventListener 이벤트 수신
    @Test
    public void testEmailEvent() throws InterruptedException {
        simpleOrderService.createOrder();
    }
}
