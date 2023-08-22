package com.dukbab.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Review")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="storeId")
//    private Store store;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="menuId")
//    private Menu menu;

    private double rating; // 별점

    @Column(length = 300)
    private String content; // 내용

    @Column(columnDefinition = "TEXT")
    private String pictureUrl; // 사진 Url

    private Date createdDate; // 생성 날짜

    private Date modifiedDate; // 수정 날짜

}
