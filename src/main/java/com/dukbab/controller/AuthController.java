package com.dukbab.controller;

import com.dukbab.dto.EmailCheckDto;
import com.dukbab.dto.MemberRequestDto;
import com.dukbab.dto.MemberResponseDto;
import com.dukbab.dto.TokenDto;
import com.dukbab.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto requestDto){
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto){
        return ResponseEntity.ok(authService.login(requestDto));
    }

    @PostMapping("/signup/email-check")
    public ResponseEntity<?> emailCheck(@RequestBody EmailCheckDto emailCheckDto){
        if(authService.emailCheck(emailCheckDto)){
            return ResponseEntity.ok("이미 가입되어 있는 유저입니다");
        }
        return ResponseEntity.ok("이메일이 확인되었습니다.");
    }

}
