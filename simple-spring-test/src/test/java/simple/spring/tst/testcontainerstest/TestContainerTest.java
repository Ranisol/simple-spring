package simple.spring.tst.testcontainerstest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import simple.spring.tst.texture.*;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
@ContextConfiguration(initializers = {TestContainerTest.ContainerPropertyInitializer.class})
public class TestContainerTest {
    @SpyBean
    MemberServiceImpl memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    Environment environment;

    @Autowired
    StudyRepository studyRepository;


    @Container
    static GenericContainer container = new GenericContainer("postgres")
            .withExposedPorts(5432)
            .withEnv("POSTGRES_DB", "studytest");
    //static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();

    @BeforeEach
    void beforeEach() {
        System.out.println(environment.getProperty("container.port"));
        System.out.println(environment.getProperty("container.host"));
        System.out.println(environment.getProperty("spring.datasource.url"));
    }

    // @TestContainers, @Container 통해 자동화
//    @BeforeAll
//    static void beforeAll() {
//        postgreSQLContainer.start();
//        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
//        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
//        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
//    }
//
//    @AfterAll
//    static void tearDown() {
//        postgreSQLContainer.stop();
//    }

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
                    "container.port=" + container.getMappedPort(5432)
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
