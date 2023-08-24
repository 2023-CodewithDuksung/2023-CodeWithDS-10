package com.dukbab.service;

import com.dukbab.configuration.SecurityUtil;
import com.dukbab.domain.Member;
import com.dukbab.domain.Order;
import com.dukbab.domain.OrderItem;
import com.dukbab.dto.OrderItemRequestDto;
import com.dukbab.dto.OrderRequestDto;
import com.dukbab.repository.MemberRepository;
import com.dukbab.repository.MenuRepository;
import com.dukbab.repository.OrderItemRepository;
import com.dukbab.repository.OrderRepository;
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
    private final MenuService menuService;
    private int waitingNumber = 0; // 대기 번호

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
        log.info(member.toString());
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
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .orderItemCnt(requestDto.getOrderItemCnt())
                    .menu(menuService.findMenuByName(requestDto.getName()))
                    .store(menuService.findMenuByName(requestDto.getName()).getStore())
                    .build();
            orderItems.add(orderItem);
            log.info(orderItem.toString());
        }

        orderRepository.save(order);
        for(OrderItem orderItem: orderItems){
            orderItemRepository.save(orderItem);
        }

        return getWaitingNumber();
    }

}
