package com.dukbab.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewDTO {
    private double rating;
    private String nickname;
    private String title;
    private String content;
    private String pictureUrl;
    private Date createdDate;

}
