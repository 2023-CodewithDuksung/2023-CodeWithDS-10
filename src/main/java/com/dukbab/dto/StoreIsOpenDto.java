package com.dukbab.dto;

import com.dukbab.domain.isOpen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreIsOpenDto {

    private isOpen store_1;
    private isOpen store_2;
    private isOpen store_3;
    private isOpen store_4;
    private isOpen store_5;
    private isOpen store_6;
    private isOpen store_7;


}
