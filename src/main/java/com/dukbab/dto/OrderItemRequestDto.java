package com.dukbab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequestDto {
    // 주문 postMapping 발생 시 데이터 받아오는 Dto 안에 들어가는 OrderItem 정보를 저장하는 Dto
    private String name;
//    private int menuPrice;
    private int orderItemCnt;
//    private int totalPrice;  // menuPrice * orderItemCnt

}
