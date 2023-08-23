package com.dukbab.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
@Builder
public class OrderItemDto {
    // 마이페이지 보일 때 쓰이는 Dto
    private String menuName;
    private int orderItemCnt;
}
