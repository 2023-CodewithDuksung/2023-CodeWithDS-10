package com.dukbab.service;

import com.dukbab.configuration.SecurityUtil;
import com.dukbab.domain.Member;
import com.dukbab.domain.Menu;
import com.dukbab.domain.Review;
import com.dukbab.dto.MenuDTO;
import com.dukbab.dto.ReviewDTO;
import com.dukbab.repository.MemberRepository;
import com.dukbab.repository.MenuRepository;
import com.dukbab.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private MemberRepository memberRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(MemberRepository memberRepository, ReviewRepository reviewRepository){
        this.memberRepository = memberRepository;
        this.reviewRepository = reviewRepository;

    }


    @Transactional
    // 리뷰 저장
    public Review saveReview(Review review){

        Long currentMemberId = (long) SecurityUtil.getCurrentMemberId();

        // 현재 로그인한 사용자의 정보 가져오기
        Member loggedInMember = memberRepository.findById(Math.toIntExact(currentMemberId))
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        review.setNickname(loggedInMember.getNickname());

        // 리뷰 저장
        Review savedReview = reviewRepository.save(review);

        // 리뷰가 저장되었다면 modifiedDate 업데이트
        savedReview.setModifiedDate(new Date()); // 현재 날짜와 시간으로 업데이트

        return savedReview;
    }




    //모두  리뷰 조회
    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }
    // 메뉴 아이디에 해당하는 리뷰 목록 조회 (DTO 형식)
    public List<ReviewDTO> getReviewDTOs(int menuId) {
        Optional<Review> reviews = reviewRepository.findById(menuId);

        return reviews.stream()
                .map(review -> {
                    ReviewDTO reviewDTO = new ReviewDTO();
                    reviewDTO.setNickname(review.getNickname());
                    reviewDTO.setTitle(review.getTitle());
                    reviewDTO.setContent(review.getContent());
                    reviewDTO.setRating(review.getRating());

                    return reviewDTO;
                })
                .collect(Collectors.toList());
    }

}
