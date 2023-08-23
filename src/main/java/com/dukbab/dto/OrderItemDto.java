package com.dukbab.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
@Builder
public class OrderItemDto {
    private String menuName;
    private int orderItemCnt;
}
