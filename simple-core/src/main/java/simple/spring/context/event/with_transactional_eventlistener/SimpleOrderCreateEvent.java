package simple.spring.context.event.with_transactional_eventlistener;

import lombok.Getter;
import simple.spring.context.event.service_texture.SimpleOrder;

@Getter
public class SimpleOrderCreateEvent {
    private final SimpleOrder order;

    public SimpleOrderCreateEvent(SimpleOrder order) {
        this.order = order;
    }
}
