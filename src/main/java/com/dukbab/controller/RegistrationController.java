package com.dukbab.controller;

import com.dukbab.domain.*;
import com.dukbab.repository.*;
import com.dukbab.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

// 테스트 코드
@Slf4j
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final MemberService memberService;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    // 테스트 코드
    @GetMapping("/insert")
    public Member insert(){
        Member member = new Member("nickname", "20210841~duksung.ak.kr", "39042", "sdljf", "fdg", new Date());
        memberService.save(member);
        return member;
    }


    @GetMapping("/store/insert")
    public Store storeInsert(){
        Store store = new Store(isOpen.OPEN, "13:00", "sdfljogr", 2);
        storeRepository.save(store);
        return store;
    }

    @GetMapping("/menu/insert")
    public Menu menuInsert() {
        Menu menu = new Menu(storeRepository.findById(1).get(), menuStatus.SELL, "탕수육", "imageurl", 3000, "단백질", "돼지고기", 15, 4.8, "맛있는 탕수육입니다.", 1);
        menuRepository.save(menu);
        menu.getStore().getMenus().add(menu);
        return menu;
    }

    @GetMapping("/order/insert")
    public Order orderInsert() {
        Order order = new Order(memberRepository.findById(2).get(), "카드", 66000, new Date(), 20, 754);
        orderRepository.save(order);
        order.getMember().getOrders().add(order);
        return order;
    }

    @GetMapping("/orderItem/insert")
    public OrderItem orderItemInsert(){
        OrderItem orderItem = new OrderItem(menuRepository.findById(8).get(), orderRepository.findById(2).get(), menuRepository.findById(8).get().getStore(), 3, 6000);
        orderItemRepository.save(orderItem);
        orderItem.getMenu().getOrderItems().add(orderItem);
        orderItem.getOrder().getOrderitems().add(orderItem);
        orderItem.getStore().getOrderItems().add(orderItem);
        return orderItem;
    }



}
