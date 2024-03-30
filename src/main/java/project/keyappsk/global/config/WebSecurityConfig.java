package project.keyappsk.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import project.keyappsk.domain.member.entity.enumerate.Role;
import project.keyappsk.domain.member.handler.MemberAuthenticationFailureHandler;
import project.keyappsk.domain.member.service.CustomOauth2UserService;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig  {
    private final CustomOauth2UserService customOauth2UserService;
    private final MemberAuthenticationFailureHandler memberAuthenticationFailureHandler;
    //공용 페이지
    private final String[] publicPage = new String[] {
            "/", "/member/add",
            "/member/login",
            "/member/info",
            "/content/**",
            "/fragment/**",
            "/img/**",
            "/lib/**",
            "/scss/**",
            "/css/**",
            "/js/**"
    };
    //일반 회원 페이지
    private final String[] guestPage = new String[] {
            "/accountInfo"
    };
    //가게 주인 페이지
    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //정적 리소스 검증 x
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( request -> request.requestMatchers(publicPage)
                        .permitAll()
                        .requestMatchers(guestPage)
                        .hasRole(Role.GUEST.name())//앞에 ROLE_ 이 붙음
                );

        defaultLoginSetting(http);
        oauth2LoginSetting(http);

        http.logout( logout ->{
            logout.logoutUrl("/member/logout")
                    .logoutSuccessHandler( ((request, response, authentication) -> {
                        log.info("로그아웃 성공");
                        response.sendRedirect("/");
                    }));
        });

        http.exceptionHandling( httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer.accessDeniedHandler( (req,res, exception) ->{
                    log.info(req.getRequestURI());
                    log.info("header names: {}", req.getHeaderNames().toString());
                    log.info("에러 내용: {}", exception.getMessage());
                }));
        return http.build();
    }

    private void oauth2LoginSetting(HttpSecurity http) throws Exception {
        http.oauth2Login(oauth -> {
            oauth.defaultSuccessUrl("/", true);
            oauth.userInfoEndpoint(userInfoEndpointConfig -> {
                userInfoEndpointConfig.userService(customOauth2UserService);
            });
        });
    }
    private void defaultLoginSetting(HttpSecurity http) throws Exception {
        http.formLogin(formLogin -> {
            formLogin.loginPage("/member/login")
                    .loginProcessingUrl("/member/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(((request, response, authentication) -> {
                        log.info("로그인 완료되었습니다.");
                    }))
                    .defaultSuccessUrl("/", true)
                    .failureHandler(memberAuthenticationFailureHandler);

        });
    }
}
