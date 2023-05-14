package com.example.demo.repository;

import com.example.demo.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
    List<Rating> findByNumVotesGreaterThan(int numVotes, Pageable pageable);
}
