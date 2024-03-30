package project.keyappsk.domain.member.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import project.keyappsk.domain.member.dto.AddMemberFormDto;
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
    public void save(AddMemberFormDto addMemberFormDto) {
        Member member = Member.builder()
                .email(addMemberFormDto.getEmail())
                .name(addMemberFormDto.getName())
                .role(Role.GUEST)
                .signType(SignType.ORIGIN)
                .password(passwordEncoder.encode(addMemberFormDto.getPassword()))
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        memberRepository.save(member);
    }

}
