package simple.boot.gracefullyshutdown.after2dot3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 스프링 2.3 부터 제공하는 GracefullyShutdown 기능을 테스트하기 위한 컨트롤러
 * - kill -9 [PID] 하면 무조건 죽음
 * - kill -15 [PID] 하면 GracefullyShutdown 됨
 * https://stackoverflow.com/questions/55796667/linux-kill-command-9-vs-15
 * */
@RestController
@RequestMapping("/gracefully")
public class From2dot3Controller{
    @GetMapping("/shutdown")
    public String shutdown() throws InterruptedException {
        Thread.sleep(10000);
        return "shutdown";
    }
}
