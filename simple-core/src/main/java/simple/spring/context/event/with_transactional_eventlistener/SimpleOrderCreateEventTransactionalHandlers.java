package simple.spring.context.event.with_transactional_eventlistener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import simple.spring.context.event.service_texture.SimpleMailService;
import simple.spring.context.event.service_texture.SimplePointService;
import simple.spring.context.event.service_texture.SimpleRealtimeStaticsService;

@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleOrderCreateEventTransactionalHandlers {
    private final SimpleMailService simpleMailService;
    private final SimpleRealtimeStaticsService simpleRealtimeStaticsService;
    private final SimplePointService simplePointService;

    // success events
    @TransactionalEventListener
    public void sendMail(SimpleOrderCreateEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

    @TransactionalEventListener
    public void sendRealtimeStatics(SimpleOrderCreateEvent event) {
        simpleRealtimeStaticsService.processSimpleOrder(event.getOrder());
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void sendPoint(SimpleOrderCreateEvent event) {
        simplePointService.processSimpleOrder(event.getOrder());
    }

    // statics fail events
    @TransactionalEventListener
    public void sendMail(SimpleOrderCreateStaticsFailEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

    @TransactionalEventListener
    public void sendRealtimeStatics(SimpleOrderCreateStaticsFailEvent event) {
        simpleRealtimeStaticsService.processSimpleOrderFail(event.getOrder());
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void sendPoint(SimpleOrderCreateStaticsFailEvent event) {
        simplePointService.processSimpleOrder(event.getOrder());
    }

    // point fail events
    @Order(3)
    @TransactionalEventListener
    public void sendMail(SimpleOrderCreatePointFailEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

    @Order(2)
    @TransactionalEventListener
    public void sendRealtimeStatics(SimpleOrderCreatePointFailEvent event) {
        simpleRealtimeStaticsService.processSimpleOrder(event.getOrder());
    }

    @Order(1)
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void sendPoint(SimpleOrderCreatePointFailEvent event) {
        simplePointService.processSimpleOrderFail(event.getOrder());
    }

}
