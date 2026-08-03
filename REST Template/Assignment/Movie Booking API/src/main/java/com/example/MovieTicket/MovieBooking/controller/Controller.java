package com.example.MovieTicket.MovieBooking.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.MovieTicket.MovieBooking.Exceptions.InputValidationException;
import com.example.MovieTicket.MovieBooking.Model.Movie;
import com.example.MovieTicket.MovieBooking.service.MovieServiceInterface;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class Controller
{
    private final MovieServiceInterface movieService;

    public Controller(MovieServiceInterface movieService)
    {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies()
    {
        return movieService.getAll();
    }

    @PostMapping("/movie")
    public void addMovie(@Valid @RequestBody Movie movie, BindingResult errorHandler)
    {
        if(errorHandler.hasErrors())
        {
            throw new InputValidationException("Movie details invalid");
        }
        movieService.add(movie);
    }

    @GetMapping("/movie/{id}")
    public Movie getMovie(@PathVariable String id)
    {
        return movieService.get(id);
    }

    @DeleteMapping("/movie/{id}")
    public void deleteMovie(@PathVariable String id)
    {
        movieService.deleteMovie(id);
    }

    @PutMapping("update/{id}")
    public void updateMovie(@Valid @RequestBody Movie movie, BindingResult errorHandler, @PathVariable String id)
    {
        if(errorHandler.hasErrors())
        {
            throw new InputValidationException("Movie details invalid");
        }
        movieService.update(id, movie);
    }
}