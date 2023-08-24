package com.dukbab.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private int id;

    @Column(length = 100)
    private String nickname; // 닉네임

    @Column(unique = true, nullable = false, length = 100)
    private String email; // 이메일

    @Column(length = 100, nullable = false)
    private String password; // 비밀번호

    @Column(length = 100)
    private String phone; // 전화번호

    @Enumerated(EnumType.STRING)
    private Role role; // 역할(사용자, 가게주인)

    @Column(length = 300)
    private String currentAddress; // 현위치

    private Date createdDate; // 생성 날짜 - 이거 빼도 되지 않을까요??


    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY) // 양방향 관계
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "member") // 카트 하나 보유
    private Cart cart;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) // 양방향 관계
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    // 테스트 코드
    public Member(String nickname, String email, String password, String phone, String currentAddress, Date createdDate){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.currentAddress = currentAddress;
        this.createdDate = createdDate;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setPassword(String password){
        this.password = password;
    }

//    @Builder
//    public Member(int id, String nickname, String email, String password,  Role role){
//        this.id = id;
//        this.nickname = nickname;
//        this.email = email;
//        this.password = password;
//        this.role= role;
//    }


}
