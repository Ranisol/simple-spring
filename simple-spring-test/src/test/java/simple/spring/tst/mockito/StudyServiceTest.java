package simple.spring.tst.mockito;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import simple.spring.tst.texture.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {
    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("test@email.com");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        Study study = new Study(10, "java");
        studyService.createNewStudy(1L, study);

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

        Member member = new Member();
        member.setId(1L);
        member.setEmail("test@email.com");

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
