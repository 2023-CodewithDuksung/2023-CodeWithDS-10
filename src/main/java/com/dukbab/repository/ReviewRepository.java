package com.dukbab.repository;

import com.dukbab.domain.Menu;
import com.dukbab.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByMenu(Menu menu);

}
