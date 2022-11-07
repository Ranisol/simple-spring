package simple.spring.context.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import simple.spring.context.event.service.SimpleMailService;
import simple.spring.context.event.service.SimplePointService;
import simple.spring.context.event.service.SimpleRealtimeStaticsService;
import simple.spring.context.event.withasync.SimpleOrderCreateEventAsyncHandlers;
import simple.spring.context.event.withasync.SimpleOrderService;

@SpringBootTest(classes = {
        SimpleOrderCreateEventAsyncHandlers.class,
        SimpleOrderService.class,
        SimpleMailService.class,
        SimplePointService.class,
        SimpleRealtimeStaticsService.class
})
@EnableAsync
public class OrderCreateEventWithAsyncTest {
    @Autowired
    SimpleOrderService simpleOrderService;

    // SimpleOrderService 이벤트 발행 -> SimpleMailEventListener 이벤트 수신
    @Test
    public void testEmailEvent() throws InterruptedException {
        simpleOrderService.createOrder();
        Thread.sleep(2000);
    }
}
