package com.dukbab.domain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.awt.*;
@Entity
@Table(name="CartItem")
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="cartItemId")
    private int cartItem;   // 장바구니 상품
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cartId")
    private Cart cart;      // 장바구니 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menuId")
    private Menu menu;      // 메뉴 id

    private int menuCnt;    // 각 메뉴 수량



}