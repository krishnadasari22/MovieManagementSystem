package com.example.demo.entity.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {
	   List<Movie> findByOrderByRuntimeMinutesDesc(Pageable pageable);
	   List<Movie> findByGenresAndOrderByRuntimeMinutesDesc(String genre, Pageable pageable);
	   List<Movie> findByGenresAndOrderByAverageRatingDesc(String genre, Pageable pageable);
	   List<Movie> findByAverageRatingGreaterThanOrderByAverageRatingDesc(float rating, Pageable pageable);
	}
