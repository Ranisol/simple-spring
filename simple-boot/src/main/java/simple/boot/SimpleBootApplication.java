package simple.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "simple.boot.gracefullyshutdown.before2dot3")
public class SimpleBootApplication {
    public static void main(String[] args) {
        /** pid 파일 생성 */
        SpringApplication app = new SpringApplication(SimpleBootApplication.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
        //SpringApplication.run(SimpleBootApplication.class, args);
    }
}
