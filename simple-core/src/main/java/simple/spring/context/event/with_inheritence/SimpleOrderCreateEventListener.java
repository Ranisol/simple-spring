package simple.spring.context.event.with_inheritence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import simple.spring.context.event.service_texture.SimpleMailService;
import simple.spring.context.event.service_texture.SimplePointService;
import simple.spring.context.event.service_texture.SimpleRealtimeStaticsService;

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
        simpleRealtimeStaticsService.processSimpleOrder(event.getOrder());
        simplePointService.processSimpleOrder(event.getOrder());
    }
}
