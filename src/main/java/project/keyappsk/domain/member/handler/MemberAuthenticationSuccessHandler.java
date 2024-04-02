package project.keyappsk.domain.member.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import project.keyappsk.global.consts.RedirectURL;

import java.io.IOException;

@Component
@Slf4j
public class MemberAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        setDefaultTargetUrl(request.getParameter(RedirectURL.RedirectURL));
        log.info("redirectURL: {}",request.getParameter(RedirectURL.RedirectURL));
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
