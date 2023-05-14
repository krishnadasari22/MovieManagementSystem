package com.example.demo.controller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Movie;
import com.example.demo.entity.repository.MovieRepository;
import com.example.demo.entity.repository.RatingRepository;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
   @Autowired
   private MovieRepository movieRepository;
   
   @Autowired
   private RatingRepository ratingRepository;
    private SessionDelegatorBaseImpl entityManager;

    @GetMapping("/longest-duration-movies")
   public List<Movie> getLongestDurationMovies() {
      Pageable pageable = (Pageable) PageRequest.of(0, 10);
      return movieRepository.findByOrderByRuntimeMinutesDesc(pageable);
   }
   
   @PostMapping("/new-movie")
   public String addNewMovie(@RequestBody Movie movie) {
      movieRepository.save(movie);
      return "success";
   }
   
   @GetMapping("/top-rated-movies")
   public List<Movie> getTopRatedMovies() {
      Pageable pageable = (Pageable) PageRequest.of(0, 10);
      return movieRepository.findByAverageRatingGreaterThanOrderByAverageRatingDesc(6.0f, pageable);
   }
   
   @GetMapping("/genre-movies-with-subtotals")
   public List<Object[]> getGenreMoviesWithSubtotals() {
	   String query = "SELECT genres, primary_title, SUM(num_votes) as totalVotes FROM movies GROUP BY genres, primary_title WITH ROLLUP";
	   return entityManager.createNativeQuery(query).getResultList();
	   }

	   @PostMapping("/update-runtime-minutes")
	   public String updateRuntimeMinutes() {
	   String query = "UPDATE movies SET runtime_minutes = CASE "
	   + "WHEN genres = 'Documentary' THEN runtime_minutes + 15 "
	   + "WHEN genres = 'Animation' THEN runtime_minutes + 30 "
	   + "ELSE runtime_minutes + 45 "
	   + "END";
	   entityManager.createNativeQuery(query).executeUpdate();
	   return "success";
	   }
	   }