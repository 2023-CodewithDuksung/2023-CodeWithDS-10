package com.dukbab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    // 주문 postMapping 발생 시 데이터 받아오는 Dto

    private List<OrderItemRequestDto> orderItemRequestDtoList;
    private String paymentMethod;
    private int totalPrice;

}
