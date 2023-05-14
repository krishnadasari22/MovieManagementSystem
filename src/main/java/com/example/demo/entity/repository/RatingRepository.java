package com.example.demo.entity.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, String> {
	   List<Rating> findByNumVotesGreaterThan(int numVotes, Pageable pageable);
	}
