package com.dukbab.controller;

import com.dukbab.domain.Member;
import com.dukbab.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final MemberService memberService;

    // 테스트 코드
    @GetMapping("/insert")
    public Member insert(){
        Member member = new Member("nickname", "20210841~duksung.ak.kr", "39042", "sdljf", "fdg", new Date());
        memberService.save(member);
        return member;
    }


}
