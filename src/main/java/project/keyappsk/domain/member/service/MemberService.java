package project.keyappsk.domain.member.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import project.keyappsk.domain.member.dto.LoginMemberDto;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.member.entity.enumerate.Role;
import project.keyappsk.domain.member.entity.enumerate.SignType;
import project.keyappsk.domain.member.repository.MemberRepository;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void save(LoginMemberDto loginMemberDto) {
        Member member = Member.builder()
                .email(loginMemberDto.getEmail())
                .name(loginMemberDto.getName())
                .role(Role.GUEST)
                .signType(SignType.ORIGIN)
                .password(passwordEncoder.encode(loginMemberDto.getPassword()))
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        memberRepository.save(member);
    }

}
