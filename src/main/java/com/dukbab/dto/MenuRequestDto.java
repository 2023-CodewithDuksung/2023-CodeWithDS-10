package com.dukbab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuRequestDto {

    private String nickname;

    private List<MenuDTO> menuDTOList;

}
