package com.dukbab.service;

import com.dukbab.configuration.SecurityUtil;
import com.dukbab.domain.*;
import com.dukbab.dto.OrderItemRequestDto;
import com.dukbab.dto.OrderRequestDto;
import com.dukbab.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    private final MenuRepository menuRepository;
    private final MenuService menuService;
    private int waitingNumber = 0; // 대기 번호

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행, 매일 초기화해 줌.
    public void resetWaitingNumber(){
        waitingNumber = 0;
    }

    public int getWaitingNumber(){
        return waitingNumber;
    }

    public void setWaitingNumber(int waitingNumber){ // 주문할 때마다 waitingNumber를 하나씩 늘릴 것.
        this.waitingNumber = waitingNumber;
    }

    @Transactional
    public int createOrder(OrderRequestDto orderRequestDto){
        setWaitingNumber(waitingNumber + 1);
        Member member= memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
        // OrderRequestDto에서 Order랑 OrderItem을 만들어서 데이터베이스에 저장해줘야 함. 그리고 대기번호를 return 하기
        Order order = Order.builder()
                .member(member)
                .createdDate(new Date())
                .waitingNumber(getWaitingNumber())
                .paymentMethod(orderRequestDto.getPaymentMethod())
                .totalPrice(orderRequestDto.getTotalPrice())
                .build();
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequestDto requestDto: orderRequestDto.getOrderItemRequestDtoList()){
            int soldOutStatus = isSoldOut(menuService.findMenuByName(requestDto.getName()), requestDto.getOrderItemCnt());
            if (soldOutStatus == 3) { // 주문 접수가 되지 않을 것이고, 메뉴가 품절 상태로 바뀌지 않음.
                return 10000; // 전체 주문 중 하나의 상품이라도 주문 접수가 안 된다면 주문 전체가 안 되도록 해야 함.
            }else{ // 주문 접수는 될 것
                OrderItem orderItem = OrderItem.builder()
                        .order(order)
                        .orderItemCnt(requestDto.getOrderItemCnt())
                        .menu(menuService.findMenuByName(requestDto.getName()))
                        .store(menuService.findMenuByName(requestDto.getName()).getStore())
                        .build();
                orderItems.add(orderItem);
                menuService.findMenuByName(requestDto.getName()).setCnt(menuService.findMenuByName(requestDto.getName()).getCnt() + requestDto.getOrderItemCnt()); // 메뉴에 주문 수 누적 합계
                if(soldOutStatus == 2){ // 메뉴가 품절 상태로 바뀜.
                menuService.findMenuByName(requestDto.getName()).setMenuStatus(menuStatus.SOLD_OUT);
                }
            }
        }

        orderRepository.save(order);
        for(OrderItem orderItem: orderItems){
            orderItemRepository.save(orderItem);
        }

        return getWaitingNumber();
    }


    @Transactional
    public int isSoldOut(Menu menu, int orderItemCnt){ // menu와 requestDto의 orderCount를 더해줘야 함. 그 합이 한계와 같다면 주문 받고 품절 상태 처리, 넘어선다면 주문 받지 않고 품절 상태 처리
        int limit = 15; // 다른 식당의 메뉴들은 우선 일괄적으로 15개가 한계라고 하자. 나중에 관리자 권한 만들면 다르게 바꾸면 됨. 관리자가 설정하게 하면 됨.
        if(menu.getStore().getStoreId() == 1){ // 아침 밥상 가게만 제한이 100개고 다른 곳은 메뉴마다 제한이 다름.
            limit = 100;
        }
        if((menu.getCnt() + orderItemCnt) < limit){
            return 1;  // 주문 접수될 것이고, 메뉴도 품절 상태로 바뀌지 않을 것임.
        } else if ((menu.getCnt() + orderItemCnt) == limit) {
            return 2; // 주문 접수는 될 것이고, 메뉴가 품절 상태로 바뀜.
        }else{
            return 3; // 주문 접수가 되지 않을 것이고, 메뉴가 품절 상태로 바뀌지 않음.
        }
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행, 매일 초기화해 줌.
    public void resetMenuCount(){
        for(Menu menu: menuRepository.findAll()){
            menu.setCnt(0);
        }
    }



}
