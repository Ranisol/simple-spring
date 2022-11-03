package simple.spring.context.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@ResponseBody
@Scope(value = "")
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}


