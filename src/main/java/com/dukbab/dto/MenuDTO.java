package com.dukbab.dto;

import com.dukbab.domain.menuStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {
    private int menuId;
    private String name;
    private int price;
    private int time;

    private menuStatus menuStatus;
    private int store;
    private String OriginIng;
    private String allergicIng;
    private double rating;
    private String content;
    private int cnt;

    private String imageUrl;

}
