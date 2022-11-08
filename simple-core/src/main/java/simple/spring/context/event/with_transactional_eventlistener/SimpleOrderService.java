package simple.spring.context.event.with_transactional_eventlistener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.spring.context.event.service_texture.SimpleOrder;
import simple.spring.context.event.service_texture.entity.Orders;
import simple.spring.context.event.service_texture.repository.OrdersRepository;

@Service("simpleOrderServiceWithTransactionalEventListener")
@RequiredArgsConstructor
@Slf4j
public class SimpleOrderService {
    private final ApplicationEventPublisher eventPublisher;
    private final OrdersRepository ordersRepository;
    @Transactional
    public void createOrder() {
        log.info("Creating order");
        SimpleOrder order = new SimpleOrder("userEmail", 10L, 300L);
        saveOrderBySimpleOrder(order);
        SimpleOrderCreateEvent event = new SimpleOrderCreateEvent(order);
        publishCreateOrderEvent(event);
    }

    @Transactional
    public void createOrderWithRealStaticsFail() {
        log.info("Creating order");
        SimpleOrder order = new SimpleOrder("userEmail", 10L, 300L);
        saveOrderBySimpleOrder(order);
        SimpleOrderCreateStaticsFailEvent event = new SimpleOrderCreateStaticsFailEvent(order);
        publishCreateOrderWithRealStaticsFailEvent(event);
    }

    @Transactional
    public void createOrderWithPointFail() {
        log.info("Creating order");
        SimpleOrder order = new SimpleOrder("userEmail", 10L, 300L);
        saveOrderBySimpleOrder(order);
        SimpleOrderCreatePointFailEvent event = new SimpleOrderCreatePointFailEvent(order);
        publishCreteOrderWithPointFailEvent(event);
    }

    private void publishCreateOrderEvent(SimpleOrderCreateEvent event) {
        eventPublisher.publishEvent(event);
    }

    private void publishCreateOrderWithRealStaticsFailEvent(SimpleOrderCreateStaticsFailEvent event) {
        eventPublisher.publishEvent(event);
    }

    private void publishCreteOrderWithPointFailEvent(SimpleOrderCreatePointFailEvent event) {
        eventPublisher.publishEvent(event);
    }

    private void saveOrderBySimpleOrder(SimpleOrder order) {
        Orders orders = new Orders(order.getUserEmail(), order.getItemId(), order.getQuantity());
        ordersRepository.save(orders);
    }
}
