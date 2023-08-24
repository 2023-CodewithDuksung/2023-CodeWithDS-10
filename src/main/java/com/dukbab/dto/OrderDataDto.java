package com.dukbab.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
@Builder
public class OrderDataDto {
    // 마이페이지 order 내용 보낼 때 쓰이는 Dto
    private int orderId;
    private int totalPrice;
    private int waitingNumber;
    private Date createdDate;
    private String paymentMethod;
    private List<OrderItemDto> orderItemDtoList;

}
