package project.keyappsk.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.DispatcherServlet;
import project.keyappsk.domain.member.dto.SessionMember;
import project.keyappsk.global.consts.SessionConst;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class thymeleaf_extra_security_test {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "junho",  roles = "GUEST")
    void 포스트작성() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("junho", "0320kangk");
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
//                        .session(mockHttpSession))
                         .andDo(print());
    }
}
