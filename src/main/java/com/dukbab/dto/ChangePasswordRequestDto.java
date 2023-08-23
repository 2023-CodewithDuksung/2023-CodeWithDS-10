package com.dukbab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequestDto {
    // 비밀번호를 수정할 때 쓰이는 dto
    private String email;
    private String exPassword;
    private String newPassword;
}
