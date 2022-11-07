package simple.spring.context.event.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimplePointService {
    public void sendSimpleOrder(SimpleOrder simpleOrder) {
        log.info("Start send Simple order " + simpleOrder + " to point System");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Complete sent point");
    }
}
