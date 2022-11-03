package simple.spring.context.scope;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Component
@SessionScope
public class ExSessionBean {
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid() {
        this.userid = UUID.randomUUID().toString();
    }
}
