package simple.spring.context.event.with_eventlistener_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import simple.spring.context.event.service_texture.SimpleMailService;
import simple.spring.context.event.service_texture.SimplePointService;
import simple.spring.context.event.service_texture.SimpleRealtimeStaticsService;

@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleOrderCreateEventAsyncHandlers {
    private final SimpleMailService simpleMailService;
    private final SimpleRealtimeStaticsService simpleRealtimeStaticsService;
    private final SimplePointService simplePointService;

    // success events
    @Async
    @EventListener
    public void sendMail(SimpleOrderCreateEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

    @Async
    @EventListener
    public void sendRealtimeStatics(SimpleOrderCreateEvent event) {
        simpleRealtimeStaticsService.processSimpleOrder(event.getOrder());
    }

    @Async
    @EventListener
    public void sendPoint(SimpleOrderCreateEvent event) {
        simplePointService.processSimpleOrder(event.getOrder());
    }

    // statics fail events
    @Async
    @EventListener
    public void sendMail(SimpleOrderCreateStaticsFailEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

    @Async
    @EventListener
    public void sendRealtimeStatics(SimpleOrderCreateStaticsFailEvent event) {
        simpleRealtimeStaticsService.processSimpleOrderFail(event.getOrder());
    }

    @Async
    @EventListener
    public void sendPoint(SimpleOrderCreateStaticsFailEvent event) {
        simplePointService.processSimpleOrder(event.getOrder());
    }

    // point fail events
    @Async
    @EventListener
    public void sendMail(SimpleOrderCreatePointFailEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

    @Async
    @EventListener
    public void sendRealtimeStatics(SimpleOrderCreatePointFailEvent event) {
        simpleRealtimeStaticsService.processSimpleOrder(event.getOrder());
    }

    @Async
    @EventListener
    public void sendPoint(SimpleOrderCreatePointFailEvent event) {
        simplePointService.processSimpleOrderFail(event.getOrder());
    }

}
