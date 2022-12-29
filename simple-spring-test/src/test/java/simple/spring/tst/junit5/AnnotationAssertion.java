package simple.spring.tst.junit5;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AnnotationAssertion {
    @Test
    @DisplayName("테스트하기")
    void create() {
        System.out.println("test A");
    }

    @Test
    void create_name_test() {
        // 세번째는 테스트 실패시 등장 - 문자열 or Supplier
        assertEquals("a", "b", "result");
        // 사용이유? 실패시에만 등장하기 때문에 문자열 연산을 실패시로만 미룰 수 있음
        assertEquals("a", "b", () -> "a" + "b");
    }

    @Test
    void assertAllTest() {
        assertAll(
                () -> assertEquals("a", "b"),
                () -> assertEquals(1, 2)
        );
    }

    @Test
    void assertThrowTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("");
        });
        String message = exception.getMessage();
    }

    @Test
    void assertTimeoutTest() {
//        assertTimeout(Duration.ofMillis(100), () -> {
//            Thread.sleep(10000);
//        });

        // 이미 정해진 시간 넘으면 실패시킴
        // 코드블럭은 별도의 스레드에서 실행하기 때문에 스레드 로컬을 사용하는 코드라면 주의
        assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
            Thread.sleep(1000);
        });
    }

    @Test
    @Disabled
    void disabledTest() {
        System.out.println("test B");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("after each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("before each");
    }
}
