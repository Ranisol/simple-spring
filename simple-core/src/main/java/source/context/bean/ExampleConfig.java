package source.context.bean;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ExampleConfig {


    @Bean(initMethod = "init")
    public MyBean myBean() {
        return new MyBean();
    }
    static class MyBean {
        @Autowired
        private ApplicationContext ac;

        public void init() {
            AutowireCapableBeanFactory acb = ac.getAutowireCapableBeanFactory();
            TestController tc = new TestController();
            // create and register beandefinition tc
            acb.createBean(TestController.class);
            log.info("MyBean init method");
        }
    }
}
