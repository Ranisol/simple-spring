package simple.spring.webmvc.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/scope")
public class ScopeController {
    @Autowired
    SessionScopeUserData sessionScopeUserData;
    @GetMapping("/session")
    public String sessionScope(HttpSession session) {
        if(sessionScopeUserData.getUserId() == null) {
            sessionScopeUserData.setUserId();
        }
        return sessionScopeUserData.getUserId();
    }


}
