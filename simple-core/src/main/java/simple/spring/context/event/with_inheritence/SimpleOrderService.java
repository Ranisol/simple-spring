package simple.spring.context.event.with_inheritence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import simple.spring.context.event.service_texture.SimpleOrder;

@Service("simpleOrderServiceWithInheritence")
@RequiredArgsConstructor
@Slf4j
public class SimpleOrderService {
    private final ApplicationEventPublisher eventPublisher;
    public void createOrder() {
        log.info("Creating order");
        SimpleOrder order = new SimpleOrder("userEmail", 10L, 300L);
        SimpleOrderCreateEvent event = new SimpleOrderCreateEvent(this, order);
        publishCreateOrderEvent(event);
    }

    private void publishCreateOrderEvent(SimpleOrderCreateEvent event) {
        eventPublisher.publishEvent(event);
    }
}
