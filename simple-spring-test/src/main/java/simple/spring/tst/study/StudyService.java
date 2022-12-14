package simple.spring.tst.study;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.spring.tst.domain.Member;
import simple.spring.tst.domain.Study;
import simple.spring.tst.member.MemberService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudyService {
    private final MemberService memberService;
    private final StudyRepository studyRepository;

    public Study get(Long id) {
        Optional<Study> study = studyRepository.findById(id);
        return study.orElseThrow(() -> new IllegalArgumentException("Study not found"));
    }

    public Study createNewStudy(String name) {
        Study study = new Study();
        study.setName(name);
        studyRepository.save(study);
        return study;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id " + memberId)));
        memberService.notify(study);
        memberService.notify(member.get());
        return studyRepository.save(study);
    }

    public void deleteStudy(Study study) {
        studyRepository.delete(study);
    }

}
