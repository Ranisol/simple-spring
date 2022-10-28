package source.spring.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

@SpringBootApplication
@EnableBatchProcessing
public class ExampleBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleBatchApplication.class, args);
    }

}
