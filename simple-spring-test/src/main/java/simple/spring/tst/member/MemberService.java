package simple.spring.tst.member;

import simple.spring.tst.domain.Member;

import java.util.Optional;

public interface MemberService{
    void notify(Object newstudy);
    void validate(Long memberId);
    Optional<Member> findById(Long memberId);
}
