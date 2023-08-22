package com.dukbab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.Store;

import javax.persistence.*;

@Entity
@Table(name="Menu")
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;     // 메뉴 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="storeId")
    private Store store;      // 가게 id

    private String name;    // 메뉴 이름

    private String imageUrl;    // 메뉴 이미지 URL

    private int price;  // 메뉴 가격

    private String originIng;   // 원산지 성분

    private String allergicIng; // 알러지 성분

    private int cnt;    // 메뉴의 주문수

    private







}
