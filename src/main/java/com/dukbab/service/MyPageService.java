package com.dukbab.service;

import com.dukbab.configuration.SecurityUtil;
import com.dukbab.domain.Member;
import com.dukbab.domain.Order;
import com.dukbab.domain.OrderItem;
import com.dukbab.dto.MyPageDto;
import com.dukbab.dto.OrderDataDto;
import com.dukbab.dto.OrderItemDto;
import com.dukbab.repository.MemberRepository;
import com.dukbab.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MyPageService {

    private final MemberRepository memberRepository;

    @Transactional
    public MyPageDto getMyPageData(){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));

        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        List<OrderDataDto> orderDataDtoList = new ArrayList<>();
        for(Order order: member.getOrders()){
            for(OrderItem orderItem: order.getOrderItems()){
                OrderItemDto orderItemDto = OrderItemDto.builder()
                        .menuName(orderItem.getMenu().getName())
                        .orderItemCnt(orderItem.getOrderItemCnt())
                        .build();
                orderItemDtoList.add(orderItemDto);
            }
            OrderDataDto orderDataDto = OrderDataDto.builder()
                    .orderId(order.getId())
                    .totalPrice(order.getTotalPrice())
                    .waitingNumber(order.getWaitingNumber())
                    .createdDate(order.getCreatedDate())
                    .orderItemDtoList(orderItemDtoList)
                    .build();
            orderDataDtoList.add(orderDataDto);
        }

        return MyPageDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .password(member.getPassword())
                .orderList(orderDataDtoList)
                .build();
    }

}
