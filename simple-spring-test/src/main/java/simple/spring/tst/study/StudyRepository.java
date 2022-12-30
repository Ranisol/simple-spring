package simple.spring.tst.study;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.spring.tst.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {
}

