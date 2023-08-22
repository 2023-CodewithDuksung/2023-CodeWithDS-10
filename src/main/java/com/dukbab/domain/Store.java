package com.dukbab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private isOpen isOpen;  // 운영 여부

    private String operationHours;  // 운영시간

    private String authenticationKey;   // 인증키

    private int congestion;     // 혼잡도

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계
    private List<Menu> menus = new ArrayList<>();

  
}

