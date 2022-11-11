package simple.spring.context.event.service_texture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.spring.context.event.service_texture.entity.UserPoint;
import simple.spring.context.event.service_texture.repository.UserPointRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimplePointService {
    private final UserPointRepository userPointRepository;

    @Transactional
    public void processSimpleOrder(SimpleOrder simpleOrder) {
        log.info("PointService: Start process Simple order " + simpleOrder + " to point System");
        UserPoint userPoint = new UserPoint(simpleOrder.getUserEmail(), getPointByPolicy(simpleOrder));
        userPointRepository.save(userPoint);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("PointService: Complete process point");
    }

    @Transactional
    public void processSimpleOrderFail(SimpleOrder simpleOrder) {
        log.info("PointService: Start process Simple order " + simpleOrder + " to point System");
        UserPoint userPoint = new UserPoint(simpleOrder.getUserEmail(), getPointByPolicy(simpleOrder));
        userPointRepository.save(userPoint);
        throw new RuntimeException("PointService: Fail to process point");
    }

    private Long getPointByPolicy(SimpleOrder simpleOrder) {
        return simpleOrder.getQuantity();
    }
}
