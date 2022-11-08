package simple.spring.context.event.with_eventlistener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import simple.spring.context.event.service_texture.SimpleMailService;
import simple.spring.context.event.service_texture.SimplePointService;
import simple.spring.context.event.service_texture.SimpleRealtimeStaticsService;

@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleOrderCreateEventHandlers {
    private final SimpleMailService simpleMailService;
    private final SimpleRealtimeStaticsService simpleRealtimeStaticsService;
    private final SimplePointService simplePointService;

    // success events
    @EventListener
    public void sendMail(SimpleOrderCreateEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

    @EventListener
    public void sendRealtimeStatics(SimpleOrderCreateEvent event) {
        simpleRealtimeStaticsService.sendSimpleOrder(event.getOrder());
    }

    @EventListener
    public void sendPoint(SimpleOrderCreateEvent event) {
        simplePointService.sendSimpleOrder(event.getOrder());
    }

    // statics fail events
    @Order(1)
    @EventListener
    public void sendPoint(SimpleOrderCreateStaticsFailEvent event) {
        simplePointService.sendSimpleOrder(event.getOrder());
    }

    @Order(2)
    @EventListener
    public void sendRealtimeStatics(SimpleOrderCreateStaticsFailEvent event) {
        simpleRealtimeStaticsService.sendSimpleOrderFail(event.getOrder());
    }

    @Order(3)
    @EventListener
    public void sendMail(SimpleOrderCreateStaticsFailEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

    // point fail events
    @Order(1)
    @EventListener
    public void sendPoint(SimpleOrderCreatePointFailEvent event) {
        simplePointService.sendSimpleOrderFail(event.getOrder());
    }

    @Order(2)
    @EventListener
    public void sendRealtimeStatics(SimpleOrderCreatePointFailEvent event) {
        simpleRealtimeStaticsService.sendSimpleOrder(event.getOrder());
    }

    @Order(3)
    @EventListener
    public void sendMail(SimpleOrderCreatePointFailEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

}
