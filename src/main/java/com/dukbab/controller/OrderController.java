package com.dukbab.controller;

import com.dukbab.dto.OrderRequestDto;
import com.dukbab.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        int waitingNumber = orderService.createOrder(orderRequestDto);
        if(waitingNumber == 10000) ResponseEntity.ok("품절된 메뉴거나 주문 불가능한 메뉴가 있어 주문 접수가 되지 않았습니다.");
        return ResponseEntity.ok(String.valueOf(waitingNumber));
    }

}
