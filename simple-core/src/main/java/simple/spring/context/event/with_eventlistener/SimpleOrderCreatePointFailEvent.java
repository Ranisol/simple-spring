package simple.spring.context.event.with_eventlistener;

import lombok.Getter;
import simple.spring.context.event.service_texture.SimpleOrder;

@Getter
public class SimpleOrderCreatePointFailEvent {
    private final SimpleOrder order;

    public SimpleOrderCreatePointFailEvent(SimpleOrder order) {
        this.order = order;
    }
}
