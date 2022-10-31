package source.context.scope;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import source.context.TestApplication;

@SpringBootTest(classes = {ExPrototypeBean.class, ExSingletonBean.class})
public class ExPrototypeBeanTest {
    @Autowired
    private ApplicationContext ac;
    @Test
    public void prototypeBeanTest() {
        ExSingletonBean esb = ac.getBean(ExSingletonBean.class);
        ExSingletonBean esb2 = ac.getBean(ExSingletonBean.class);
        Assertions.assertEquals(esb, esb2);
        ExPrototypeBean epb = ac.getBean(ExPrototypeBean.class);
        ExPrototypeBean epb2 = ac.getBean(ExPrototypeBean.class);
        ac.getAutowireCapableBeanFactory().destroyBean(epb);
        Assertions.assertNotEquals(epb, epb2);
    }
}
