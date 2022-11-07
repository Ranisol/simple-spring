package simple.spring.context.event.withannotation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import simple.spring.context.event.service.SimpleMailService;
import simple.spring.context.event.service.SimplePointService;
import simple.spring.context.event.service.SimpleRealtimeStaticsService;

@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleOrderCreateEventHandlers {
    private final SimpleMailService simpleMailService;
    private final SimpleRealtimeStaticsService simpleRealtimeStaticsService;
    private final SimplePointService simplePointService;

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

}
