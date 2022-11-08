package simple.spring.context.event.service_texture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simple.spring.context.event.service_texture.entity.UserPoint;

@Repository
public interface UserPointRepository extends JpaRepository<UserPoint, Long> {
}
