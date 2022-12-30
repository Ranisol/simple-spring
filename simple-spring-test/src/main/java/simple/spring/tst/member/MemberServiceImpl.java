package simple.spring.tst.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.spring.tst.domain.Member;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public void notify(Object newstudy) {

    }

    @Override
    public void validate(Long memberId) {

    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
