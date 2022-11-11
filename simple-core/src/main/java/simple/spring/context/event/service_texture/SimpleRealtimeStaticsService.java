package simple.spring.context.event.service_texture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleRealtimeStaticsService {
    public void processSimpleOrder(SimpleOrder simpleOrder) {
        log.info("RealStaticsService: Start process Simple order " + simpleOrder + " to Realtime Statics System");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("RealStaticsService: Complete process");
    }

    public void processSimpleOrderFail(SimpleOrder simpleOrder) {
        log.info("RealStaticsService: Start process Simple order " + simpleOrder + " to Realtime Statics System");
        throw new RuntimeException("RealStaticsService: Fail to process");
    }
}
