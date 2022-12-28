package simple.spring.junit5;

import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class AssumeBasic {
    @Test
    void assumeTrueTest() {
        assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("LOCAL")));
        assertTrue(false);
    }

    @Test
    @Disabled
    void assumingThatTest() {
        assumingThat("LOCAL".equalsIgnoreCase(System.getenv("LOCAL")), () -> {
            assertTrue(false);
        });
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void enableOnOs() {
        assertTrue(false);
    }
}
