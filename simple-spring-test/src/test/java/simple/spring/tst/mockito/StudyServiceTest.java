package simple.spring.tst.mockito;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import simple.spring.tst.domain.Member;
import simple.spring.tst.domain.Study;
import simple.spring.tst.member.MemberService;
import simple.spring.tst.study.StudyRepository;
import simple.spring.tst.study.StudyService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StudyServiceTest {
    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Autowired
    Environment environment;

    @BeforeEach
    void beforeEach() {
        System.out.println(environment.getProperty("spring.datasource.url"));
    }

    @Test
    void createStudyService() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member(1L, "test@email.com");
        Study study = new Study(10, "java");
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        // When
        studyService.createNewStudy(1L, study);

        // Then
        verify(memberService, times(1)).notify(study);
        verify(memberService, times(2)).notify(any());
        verify(memberService, never()).validate(any());

        InOrder order = Mockito.inOrder(memberService);
        order.verify(memberService).notify(study);
        order.verify(memberService).notify(member);

        verifyNoMoreInteractions(memberService);
    }

    @Test
    void createStudyServiceBDDStyle() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
        Member member = new Member(1L, "test@email.com");
        Study study = new Study(10, "java");
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.createNewStudy(1L, study);

        // Then
        assertEquals(member, study.getOwner());
        then(memberService).should(times(1)).notify(study);
        then(memberService).should(times(2)).notify(any());
        then(memberService).shouldHaveNoMoreInteractions();
    }
}
