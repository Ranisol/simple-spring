package simple.spring.context.event.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleMailService {
    public void sendMail(String email) {
        log.info("Start Sending mail: " + "to " + email);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Complete mail sent");
    }
}
