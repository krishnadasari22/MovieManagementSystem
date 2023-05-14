package com.example.demo.repository;

import com.example.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findByOrderByRuntimeMinutesDesc(Pageable pageable);
    List<Movie> findByGenresAndOrderByRuntimeMinutesDesc(String genre, Pageable pageable);
    List<Movie> findByGenresAndOrderByAverageRatingDesc(String genre, Pageable pageable);
    List<Movie> findByAverageRatingGreaterThanOrderByAverageRatingDesc(float rating, Pageable pageable);
}

