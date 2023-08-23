package com.dukbab.service;

import antlr.Token;
import com.dukbab.configuration.SecurityUtil;
import com.dukbab.domain.Member;
import com.dukbab.dto.EmailCheckDto;
import com.dukbab.dto.MemberRequestDto;
import com.dukbab.dto.MemberResponseDto;
import com.dukbab.dto.TokenDto;
import com.dukbab.jwt.TokenProvider;
import com.dukbab.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public MemberResponseDto signup(MemberRequestDto requestDto){  // 회원가입하는 메소드
        if(memberRepository.existsByEmail(requestDto.getEmail())){
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        Member member = requestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    public TokenDto login(MemberRequestDto requestDto){
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
        return tokenProvider.generateTokenDto(authentication);
    }

    public boolean emailCheck(EmailCheckDto emailCheckDto){
        return memberRepository.existsByEmail(emailCheckDto.getEmail());
    }

    public void logout(HttpServletRequest request){
        // accessToken redisTemplate 블랙리스트 추가
        String jwt = request.getHeader("Authorization").substring(7);
        if(jwt != null){
            tokenProvider.addToBlacklist(jwt);
        }
    }

    public void delete(HttpServletRequest request){
        // 로그인 하고 있는 사용자의 정보 삭제
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
        memberRepository.delete(member);

        // 헤더의 토큰 삭제
        String jwt = request.getHeader("Authorization").substring(7);
        if(jwt != null){
            tokenProvider.addToBlacklist(jwt);
        }
    }




}
