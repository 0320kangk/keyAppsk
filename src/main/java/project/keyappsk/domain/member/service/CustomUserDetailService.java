package project.keyappsk.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.keyappsk.domain.member.dto.CustomUserDetails;
import project.keyappsk.domain.member.dto.LoginMemberDto;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.member.entity.enumerate.Role;
import project.keyappsk.domain.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> byEmailId = memberRepository.findByEmail(email);
        if (byEmailId.isPresent()){
            log.info("아이디 찾기 성공");
            Member members = byEmailId.get();
            List<GrantedAuthority> authorities = new ArrayList();
//            return User.builder()
//                    .username(members.getEmail())
//                    .password(members.getPassword())
//                    .roles(members.getRole().toString())
//                    .authorities(authorities)
//                    .build();

            return new CustomUserDetails(members);
        }
        return null;
    }
    @Transactional
    public void save(LoginMemberDto loginMemberDto){
        Member member = Member.builder()
                .email(loginMemberDto.getEmail())
                .name(loginMemberDto.getName())
                .role(Role.GUEST)
                .password(loginMemberDto.getPassword())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        memberRepository.save(member);
    }

}
