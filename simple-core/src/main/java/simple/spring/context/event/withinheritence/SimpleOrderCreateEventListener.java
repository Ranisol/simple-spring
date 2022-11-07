package simple.spring.context.event.withinheritence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import simple.spring.context.event.service.SimpleMailService;
import simple.spring.context.event.service.SimplePointService;
import simple.spring.context.event.service.SimpleRealtimeStaticsService;

@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleOrderCreateEventListener implements ApplicationListener<SimpleOrderCreateEvent> {
    private final SimpleMailService simpleMailService;
    private final SimpleRealtimeStaticsService simpleRealtimeStaticsService;
    private final SimplePointService simplePointService;

    @Override
    public void onApplicationEvent(SimpleOrderCreateEvent event) {
        log.info("Event received: " + event);
        simpleMailService.sendMail(event.getOrder().getUserEmail());
        simpleRealtimeStaticsService.sendSimpleOrder(event.getOrder());
        simplePointService.sendSimpleOrder(event.getOrder());
    }
}
