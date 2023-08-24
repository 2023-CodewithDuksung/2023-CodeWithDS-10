package com.dukbab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
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
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="storeId")
    @JsonIgnore
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menuId")
    @JsonIgnore
    private Menu menu;

    private String nickname;    // 리뷰 작성자 닉네임

    private double rating; // 별점

    private double avg_rating;

    private String title;

    @Column(length = 300)
    private String content; // 내용

    @Column(columnDefinition = "TEXT")
    private String pictureUrl; // 사진 Url

    private Date createdDate; // 생성 날짜

    private Date modifiedDate; // 수정 날짜

    @Builder
    public Review(int id, Member member,Store store, Menu menu, String nickname, double rating, String title, String content,
                  String pictureUrl,Date createdDate, Date modifiedDate, double avg_rating
                  ) {
        this.id = id;
        this.member = member;
        this.store = store;
        this.menu = menu;
        this.nickname = nickname;
        this.rating = rating;
        this.title = title;
        this.content = content;
        this.pictureUrl = pictureUrl;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.avg_rating = avg_rating;
    }


}
