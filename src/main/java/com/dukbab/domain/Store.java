package com.dukbab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

@Entity
@Table(name="Store")
@NoArgsConstructor
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId")
    private int storeId;    // store id

    private String name;    // 가게 이름

    @Enumerated(EnumType.STRING)
    private isOpen isOpen;  // 운영 여부

    private String operationHours;  // 운영 시간

    private String authenticationKey;   // 인증키

    private int congestion;     // 혼잡도

    private LocalTime localTime;    // 현재 시각



    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계
    private List<Menu> menus = new ArrayList<>();


    // 생성자에서 localTime 설정
    public Store(int storeId, String name, isOpen isOpen, String operationHours, int congestion) {
        this.storeId = storeId;
        this.name = name;
        this.isOpen = isOpen;
        this.operationHours = operationHours;
        this.congestion = congestion;
        this.localTime = LocalTime.now();
    }

    // 테스트 코드
    public Store(isOpen isOpen, String operationHours, String authenticationKey, int congestion){
        this.isOpen = isOpen;
        this.operationHours = operationHours;
        this.authenticationKey = authenticationKey;
        this.congestion = congestion;
    }

}


