package simple.spring.tst.member;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.spring.tst.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
