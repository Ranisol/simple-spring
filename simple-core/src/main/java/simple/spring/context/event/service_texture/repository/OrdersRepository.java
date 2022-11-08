package simple.spring.context.event.service_texture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simple.spring.context.event.service_texture.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
