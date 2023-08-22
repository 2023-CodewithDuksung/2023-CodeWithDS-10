package com.dukbab.domain;

import ch.qos.logback.core.status.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Menu")
@Getter
@Setter
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;     // 메뉴 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;      // 가게 id

    @Enumerated(EnumType.STRING)
    private menuStatus menuStatus;    // 상품 판매 상태

    private String name;    // 메뉴 이름

    private String imageUrl;    // 메뉴 이미지 URL

    private int price;  // 메뉴 가격

    private String originIng;   // 원산지 성분

    private String allergicIng; // 알러지 성분

    private int time;   // 소요 시간

    private double avgRating;  // 메뉴 평점

    private String content; // 메뉴 소개

    private int cnt;    // 메뉴의 주문수

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계
    private List<Review> reviews = new ArrayList<>();







}
