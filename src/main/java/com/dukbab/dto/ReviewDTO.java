package com.dukbab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    // 리뷰창에 보이는 것
    private double rating;
    private String nickname;
    private String title;
    private String content;
    private String pictureUrl;

}
