package simple.spring.context.event.withinheritence;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import simple.spring.context.event.service.SimpleOrder;

@Getter
public class SimpleOrderCreateEvent extends ApplicationEvent {
    private final SimpleOrder order;

    public SimpleOrderCreateEvent(Object source, SimpleOrder order) {
        super(source);
        this.order = order;
    }
}
