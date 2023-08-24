package com.dukbab.controller;

import com.dukbab.domain.Member;
import com.dukbab.domain.Menu;
import com.dukbab.domain.Review;
import com.dukbab.dto.MenuDTO;
import com.dukbab.dto.ReviewDTO;
import com.dukbab.service.MemberService;
import com.dukbab.service.MenuService;
import com.dukbab.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MenuService menuService;


    // 새 리뷰 생성 요청 처리
    @PostMapping("/reviews/{menuId}/new")
    public Review  createReview(@PathVariable int menuId, @RequestBody ReviewDTO reviewDTO) {
        Optional<Menu> menu = menuService.getMenuById(menuId);

        // 새 리뷰 생성 및 속성 설정
        Review review = new Review();
         review.setNickname(reviewDTO.getNickname());   // 리뷰 작성자 닉네임
         review.setRating(reviewDTO.getRating());       // 리뷰 평점
         review.setTitle(reviewDTO.getTitle());         // 리뷰 제목 설정
         review.setContent(reviewDTO.getContent());     // 리뷰 내용 설정
         review.setPictureUrl(reviewDTO.getPictureUrl());   // 리뷰에 첨부된 사진 URL 설정
         review.setCreatedDate(new Date());              // 리뷰 작성일 설정

        Date currentDate = new Date(); // 현재 날짜와 시간 가져오기
        review.setCreatedDate(currentDate);
        // 생성된 리뷰 저장 및 반환
        return reviewService.saveReview(review);
    }

    // 메뉴 아이디에 해당하는 모든 리뷰 가져오기
    @GetMapping("/reviews/{menuId}")
    public List<ReviewDTO> getAllReviews(@PathVariable int menuId) {
        return reviewService.getReviewDTOs(menuId);
    }



}
