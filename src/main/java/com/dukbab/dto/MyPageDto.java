package com.dukbab.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
@Builder
public class MyPageDto {
    // 마이페이지 내용 보일 때 쓰이는 Dto
    // 결제 수단 집어 넣어야 함.

    private String email;
    private String nickname;
    private String password;
    private List<OrderDataDto> orderList;

}
