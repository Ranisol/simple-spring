package simple.spring.context.scope;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest(classes = {ExSessionBean.class, ExSessionBeanTest.ExSessionController.class})
public class ExSessionBeanTest {
    @Autowired ExSessionController esc;

    @Test
    public void sessionBeanTest() throws Exception {
        MockHttpSession sessionA = new MockHttpSession();
        MockHttpSession sessionB = new MockHttpSession();
        MockMvc mvc = MockMvcBuilders.standaloneSetup(esc).build();
        String userIdOfA = mvc.perform(
                MockMvcRequestBuilders
                        .get("/scope/session")
                        .session(sessionA)
        ).andReturn().getResponse().getContentAsString();
       String userIdOfB = mvc.perform(
               MockMvcRequestBuilders
                       .get("/scope/session")
                       .session(sessionB)
       ).andReturn().getResponse().getContentAsString();
       String userIdOfBReRequest = mvc.perform(
                MockMvcRequestBuilders
                        .get("/scope/session")
                        .session(sessionB)
        ).andReturn().getResponse().getContentAsString();

        Assertions.assertNotEquals(userIdOfA, userIdOfB);
        Assertions.assertEquals(userIdOfB, userIdOfBReRequest);
    }


    @RequestMapping("/scope")
    @RestController
    public static class ExSessionController {
        @Autowired ExSessionBean esb;
        @GetMapping("/session")
        public String testController() {
            if(esb.getUserid() == null) {
                esb.setUserid();
            }
            return esb.getUserid();
        }
    }
}
