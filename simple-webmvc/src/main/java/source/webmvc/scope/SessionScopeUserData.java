package source.webmvc.scope;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Component
@SessionScope
public class SessionScopeUserData {
    private String userId;

    // set user uuid in session
    public void setUserId() {
        this.userId = UUID.randomUUID().toString();
    }

    public String getUserId() {
        return userId;
    }

}
