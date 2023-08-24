package com.dukbab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.awt.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    private Cart cart;      // 장바구니 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menuId")
    @JsonIgnore
    private Menu menu;      // 메뉴 id

    private int menuCnt;    // 각 메뉴 수량

    public static CartItem createCartItem(Cart cart, Menu menu,int menuCnt){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setMenu(menu);
        cartItem.setMenuCnt(menuCnt);
        return cartItem;
    }


}
