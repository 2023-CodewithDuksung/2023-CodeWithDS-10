package com.dukbab.controller;

import com.dukbab.dto.ChangePasswordRequestDto;
import com.dukbab.dto.MemberRequestDto;
import com.dukbab.dto.MemberResponseDto;
import com.dukbab.dto.MyPageDto;
import com.dukbab.service.MemberService;
import com.dukbab.service.MenuService;
import com.dukbab.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MemberController {
    private final MemberService memberService;

    private final MyPageService myPageService;




//    @GetMapping("")
//    public ResponseEntity<MemberResponseDto> getMyMemberInfo(){
//        MemberResponseDto myInfoBySecurity = memberService.getMyInfoBySecurity();
//        System.out.println(myInfoBySecurity.getNickname());
//        return ResponseEntity.ok(myInfoBySecurity);
//    }

    @GetMapping("")
    public ResponseEntity<MyPageDto> getMypageData() {
        return ResponseEntity.ok(myPageService.getMyPageData());
    }

    @PutMapping("/nicknameUpdate")
    public ResponseEntity<MemberResponseDto> setMemberNickname(@RequestBody MemberRequestDto request) {
        return ResponseEntity.ok(memberService.changeMemberNickname(request.getEmail(), request.getNickname()));
    }

    @PutMapping("/passwordUpdate")
    public ResponseEntity<MemberResponseDto> setMemberPassword(@RequestBody ChangePasswordRequestDto request) {
        return ResponseEntity.ok(memberService.changeMemberPassword(request.getExPassword(), request.getNewPassword()));
    }



}
