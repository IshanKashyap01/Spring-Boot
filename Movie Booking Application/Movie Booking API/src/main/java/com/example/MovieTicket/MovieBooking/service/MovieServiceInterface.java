package com.example.MovieTicket.MovieBooking.service;

import java.util.List;

import com.example.MovieTicket.MovieBooking.Model.Movie;

public interface MovieServiceInterface 
{
    List<Movie> getAll();

    void add(Movie movie);

    Movie get(String id);

    void delete(String id);

    void update(String id, Movie updatedMovie);
}
