package simple.spring.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan("simple.spring.batch.job.flow")
public class ExampleBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleBatchApplication.class, args);
    }

}
