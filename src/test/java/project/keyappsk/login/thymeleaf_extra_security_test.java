package project.keyappsk.login;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.keyappsk.domain.member.dto.SessionMember;
import project.keyappsk.global.consts.SessionConst;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class thymeleaf_extra_security_test {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private HttpSession httpSession;
    @BeforeEach
    void session(){
        httpSession.setAttribute(SessionConst.SessionMember, new SessionMember("junho","0320kangk@"));
    }

    @Test
    @WithMockUser(username = "junho",  roles = "GUEST")
    void 포스트작성() throws Exception {
        httpSession.setAttribute(SessionConst.SessionMember, new SessionMember("junho","0320kangk@"));
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print());
    }
}
