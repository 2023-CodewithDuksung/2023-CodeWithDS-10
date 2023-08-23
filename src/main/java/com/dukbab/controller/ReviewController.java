package com.dukbab.controller;

import com.dukbab.domain.Member;
import com.dukbab.domain.Menu;
import com.dukbab.domain.Review;
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


    @PostMapping("/reviews/{menuId}/new")
    public Review  createReview(@PathVariable int menuId, @RequestBody ReviewDTO reviewDTO) {
        Optional<Menu> menu = menuService.getMenuById(menuId);
        Review review = new Review();
        review.setNickname(reviewDTO.getNickname());
        review.setRating(reviewDTO.getRating());
        review.setTitle(reviewDTO.getTitle());
        review.setContent(reviewDTO.getContent());
        review.setPictureUrl(reviewDTO.getPictureUrl());

        return reviewService.saveReview(review);
    }

    @GetMapping("/reviews/{menuId}")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }


}
