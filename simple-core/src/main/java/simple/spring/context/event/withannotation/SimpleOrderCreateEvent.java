package simple.spring.context.event.withannotation;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import simple.spring.context.event.service.SimpleOrder;

@Getter
public class SimpleOrderCreateEvent {
    private final SimpleOrder order;

    public SimpleOrderCreateEvent(SimpleOrder order) {
        this.order = order;
    }
}
