package com.dukbab.service;

import com.dukbab.configuration.SecurityUtil;
import com.dukbab.domain.Member;
import com.dukbab.domain.Menu;
import com.dukbab.domain.Review;
import com.dukbab.dto.ReviewDTO;
import com.dukbab.repository.MemberRepository;
import com.dukbab.repository.MenuRepository;
import com.dukbab.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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


        Member loggedInMember = memberRepository.findById(Math.toIntExact(currentMemberId))
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        review.setNickname(loggedInMember.getNickname());

        // 리뷰 저장
        Review savedReview = reviewRepository.save(review);


        return savedReview;
    }




    // 리뷰 조회
    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

}
