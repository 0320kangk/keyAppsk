package project.keyappsk.domain.member.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import project.keyappsk.domain.member.dto.CustomUserDetails;
import project.keyappsk.domain.member.dto.SessionMember;
import project.keyappsk.global.consts.SessionConst;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession httpSession;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            log.info("비밀번호가 다릅니다.");
            throw new BadCredentialsException("password is not matched");
        }
        httpSession.setAttribute(SessionConst.SessionMember, new SessionMember(user.getMember()));
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));

    }
}
