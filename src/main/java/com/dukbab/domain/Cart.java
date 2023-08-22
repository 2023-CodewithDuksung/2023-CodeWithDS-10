package com.dukbab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Cart")
@NoArgsConstructor
@Getter
@Setter
public class Cart {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cartId")
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    @JsonIgnore
    private Member member;


    private int totalPrice; // 총 가격

    private int totalCnt;   // 총 수량

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계
    private List<CartItem> cartItems = new ArrayList<>();

  
}

