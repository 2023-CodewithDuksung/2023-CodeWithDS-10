package com.dukbab.controller;

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

}
