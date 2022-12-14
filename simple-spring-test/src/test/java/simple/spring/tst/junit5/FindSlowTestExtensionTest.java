package simple.spring.tst.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

@ExtendWith(simple.spring.tst.junit5.FindSlowTestExtension.class)
public class FindSlowTestExtensionTest {

    // FindSlowTestExtension에 인자가 필요한 경우
    @RegisterExtension
    static simple.spring.tst.junit5.FindSlowTestExtension findSlowTestExtension = new simple.spring.tst.junit5.FindSlowTestExtension(1000L);

    @Test
    void testA() throws InterruptedException {
        Thread.sleep(1005L);
    }

    @Test
    void testB() throws InterruptedException {
        Thread.sleep(900);
    }

    @Test
    @SlowTest
    void testC() throws InterruptedException {
        Thread.sleep(1005L);
    }
}
