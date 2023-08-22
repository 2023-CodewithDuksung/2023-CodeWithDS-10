package com.dukbab.controller;

import com.dukbab.dto.ChangePasswordRequestDto;
import com.dukbab.dto.MemberRequestDto;
import com.dukbab.dto.MemberResponseDto;
import com.dukbab.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/members")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo(){
        MemberResponseDto myInfoBySecurity = memberService.getMyInfoBySecurity();
        System.out.println(myInfoBySecurity.getNickname());
        return ResponseEntity.ok(myInfoBySecurity);
    }

    @PutMapping("/nicknameUpdate")
    public ResponseEntity<MemberResponseDto> setMemberNickname(@RequestBody MemberRequestDto request){
        return ResponseEntity.ok(memberService.changeMemberNickname(request.getEmail(), request.getNickname()));
    }

    @PutMapping("/passwordUpdate")
    public ResponseEntity<MemberResponseDto> setMemberPassword(@RequestBody ChangePasswordRequestDto request){
        return ResponseEntity.ok(memberService.changeMemberPassword(request.getExPassword(), request.getNewPassword()));
    }

}
