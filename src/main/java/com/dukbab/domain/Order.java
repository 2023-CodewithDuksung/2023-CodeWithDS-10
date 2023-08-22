package com.dukbab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="OrderTable")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member member;

    @Column(length = 100)
    private String paymentMethod; // 결제 수단

    private int totalPrice; // 통합 가격

    private Date createdDate; // 생성일

    private int cookingTime; // 조리 예상 시간

    private int waitingNumber; // 대기 번호

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 양방향 관계 주문이 삭제되면 주문 아이템들도 삭제되게.
    private List<OrderItem> orderitems = new ArrayList<>();

}
