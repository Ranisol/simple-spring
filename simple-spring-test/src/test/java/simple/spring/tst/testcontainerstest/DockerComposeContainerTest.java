package simple.spring.tst.testcontainerstest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import simple.spring.tst.domain.Member;
import simple.spring.tst.domain.Study;
import simple.spring.tst.member.MemberRepository;
import simple.spring.tst.member.MemberServiceImpl;
import simple.spring.tst.study.StudyRepository;
import simple.spring.tst.study.StudyService;

import javax.transaction.Transactional;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
@ContextConfiguration(initializers = {DockerComposeContainerTest.ContainerPropertyInitializer.class})
public class DockerComposeContainerTest {
    @SpyBean
    MemberServiceImpl memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StudyRepository studyRepository;

    @Value("${container.port}") int port;


    @Container
    static DockerComposeContainer composeContainer =
            new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
                    .withExposedService("study-db", 5432);

    @BeforeEach
    void beforeEach() {
        System.out.println(port);
    }

    @Test
    @Transactional
    void createStudyService() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = memberRepository.save(new Member("test@email.com"));
        Study study = new Study(10, "java");

        // When
        studyService.createNewStudy(member.getId(), study);

        // Then
        verify(memberService, times(1)).notify(study);
        verify(memberService, times(2)).notify(any());
        verify(memberService, never()).validate(any());

        InOrder order = Mockito.inOrder(memberService);
        order.verify(memberService).notify(study);
        order.verify(memberService).notify(member);

        //verifyNoMoreInteractions(memberService);
    }

    @Test
    @Transactional
    void createStudyServiceBDDStyle() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
        Member member = memberRepository.save(new Member("test@email.com"));
        Study study = new Study(10, "java");

        // When
        studyService.createNewStudy(member.getId(), study);

        // Then
        assertEquals(member, study.getOwner());
        then(memberService).should(times(1)).notify(study);
        then(memberService).should(times(2)).notify(any());
        //then(memberService).shouldHaveNoMoreInteractions();
    }

    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "container.port=" + composeContainer.getServicePort("study-db", 5432)
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
