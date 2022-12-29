package simple.spring.tst.texture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberService{
    void notify(Object newstudy);
    void validate(Long memberId);
    Optional<Member> findById(Long memberId);
}
