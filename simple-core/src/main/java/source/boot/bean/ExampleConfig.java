package source.boot.bean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleConfig {
    @Bean(initMethod = "init")
    public MyBean myBean() {
        return new MyBean();
    }
    static class MyBean {
        public void init() {
            System.out.println("MyBean is being initialized");
        }
    }
}
