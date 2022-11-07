package simple.boot.gracefullyshutdown.before2dot3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gracefully")
public class Before2dot3Controller{
    @GetMapping("/shutdown")
    public String shutdown() throws InterruptedException {
        Thread.sleep(10000);
        return "shutdown";
    }
}