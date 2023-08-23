package com.dukbab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailCheckDto {
    // 이메일을 체크할 때 쓰이는 Dto
    private String email;
}
