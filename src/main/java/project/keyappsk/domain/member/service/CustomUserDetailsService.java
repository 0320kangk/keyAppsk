package project.keyappsk.domain.member.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.keyappsk.domain.member.dto.CustomUserDetails;
import project.keyappsk.domain.member.dto.AddMemberFormDto;
import project.keyappsk.domain.member.dto.SessionMember;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.member.entity.enumerate.Role;
import project.keyappsk.domain.member.repository.MemberRepository;
import project.keyappsk.global.consts.SessionConst;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> byEmailId = memberRepository.findByEmail(email);
        if (byEmailId.isPresent()){
            log.info("아이디 찾기 성공");
            Member member = byEmailId.get();
            List<GrantedAuthority> authorities = new ArrayList();
//            return User.builder()
//                    .username(member.getEmail())
//                    .password(member.getPassword())
//                    .roles(member.getRole().toString())
//                    .authorities(authorities)
//                    .build();
            httpSession.setAttribute(SessionConst.SessionMember, new SessionMember(member));

            return new CustomUserDetails(member);
        }
        return null;
    }

    @Transactional
    public void save(AddMemberFormDto addMemberFormDto){
        Member member = Member.builder()
                .email(addMemberFormDto.getEmail())
                .name(addMemberFormDto.getName())
                .role(Role.GUEST)
                .password(addMemberFormDto.getPassword())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        memberRepository.save(member);
    }

}
