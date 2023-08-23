package com.dukbab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItem_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menuId")
    @JsonIgnore
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="storeId")
    @JsonIgnore
    private Store store;

    private int orderItemCnt; // 주문 메뉴 수량

    private int totalPrice; // 총 금액

    // 테스트 코드
    public OrderItem(Menu menu, Order order, Store store, int orderItemCnt, int totalPrice){
        this.menu = menu;
        this.order = order;
        this.store = store;
        this.orderItemCnt = orderItemCnt;
        this.totalPrice = totalPrice;
    }

}
