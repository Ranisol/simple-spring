package simple.spring.context.event.with_transactional_eventlistener_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class SimpleOrderCreateEventTransactionalAsyncHandlers {
    private final SimpleMailService simpleMailService;
    private final SimpleRealtimeStaticsService simpleRealtimeStaticsService;
    private final SimplePointService simplePointService;

    // success event
    @Async
    @TransactionalEventListener
    public void sendMail(SimpleOrderCreateEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

    @Async
    @TransactionalEventListener
    public void sendRealtimeStatics(SimpleOrderCreateEvent event) {
        simpleRealtimeStaticsService.sendSimpleOrder(event.getOrder());
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void sendPoint(SimpleOrderCreateEvent event) {
        simplePointService.sendSimpleOrder(event.getOrder());
    }

    // statics fail event
    @Async
    @TransactionalEventListener
    public void sendMail(SimpleOrderCreateStaticsFailEvent event) {
        simpleMailService.sendMail(event.getOrder().getUserEmail());
    }

    @Async
    @TransactionalEventListener
    public void sendRealtimeStatics(SimpleOrderCreateStaticsFailEvent event) {
        simpleRealtimeStaticsService.sendSimpleOrderFail(event.getOrder());
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void sendPoint(SimpleOrderCreateStaticsFailEvent event) {
        simplePointService.sendSimpleOrder(event.getOrder());
    }
}
