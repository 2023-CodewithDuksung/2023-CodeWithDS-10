package com.dukbab.dto;

import com.dukbab.domain.Member;
import com.dukbab.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
    // Request를 받을 때 쓰이는 dto
    private String email;
    private String password;
    private String nickname;

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .role(Role.USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){ // 아이디와 비밀번호가 일치하는지 검증하는 로직
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
