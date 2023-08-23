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
        return ResponseEntity.ok(String.valueOf(orderService.createOrder(orderRequestDto)));
    }

}
