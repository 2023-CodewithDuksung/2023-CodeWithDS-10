package com.dukbab.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItem_id")
    private int id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="menuId")
//    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId")
    private Order order;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="storeId")
//    private Store store;

    private int orderItemCnt; // 주문 메뉴 수량

    private int totalPrice; // 총 금액

}
