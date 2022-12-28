package simple.spring.junit5;

import org.junit.jupiter.api.*;


public class TestTagging {
    @Test
    @Tag("fast")
    void testA() {

    }

    @Test
    @Tag("slow")
    void testB() {

    }

    @FastTest
    void testAM() {

    }

    @SlowTest
    void testBM() {

    }
}
