package com.dukbab.repository;

import com.dukbab.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(String email); // email로 Member를 찾음
    boolean existsByEmail(String email); // email이 존재하는가 판별

}
