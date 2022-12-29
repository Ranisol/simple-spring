package simple.spring.tst.junit5;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestInstanceTest {
    int value = 1;

    @BeforeAll
    void beforeAll() {
        value = 20;
        System.out.println("beforeAll");
    }
    @Test
    @Order(1)
    void test() {
        System.out.println(this);
        System.out.println(value);
    }

    @Test
    @Order(2)
    void testB() {
        System.out.println(this);
        System.out.println(value);
    }
}
