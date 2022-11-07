package simple.spring.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(basePackages = {
        "simple.spring.context.event.service",
        "simple.spring.context.event.withasync"
})
@EnableAsync
@SpringBootApplication
public class SimpleCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleCoreApplication.class, args);
    }
}
