package com.example.MovieTicket.MovieBooking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.MovieTicket.MovieBooking.Exceptions.IdAlreadyExist;
import com.example.MovieTicket.MovieBooking.Exceptions.IdNotFound;
import com.example.MovieTicket.MovieBooking.Model.Movie;

@Service
public class MovieService implements MovieServiceInterface
{
    private List<Movie> movies;
    private Map<String, Movie> map;

    public MovieService()
    {
        this.movies = new ArrayList<>();
        this.map = new HashMap<>();
    }

    @Override
    public List<Movie> getAll()
    {
        return this.movies;
    }

    @Override
    public void add(Movie movie)
    {
        if(this.map.containsKey(movie.getId()))
        {
            throw new IdAlreadyExist("Movie with id " + movie.getId() + " already exists");
        }
        this.movies.add(movie);
        this.map.put(movie.getId(), movie);
    }

    @Override
    public Movie get(String id)
    {
        if(!map.containsKey(id))
        {
            throw new IdNotFound("Movie with id " + id + " not found");
        }
        return this.map.get(id);
    }

    @Override
    public void deleteMovie(String id)
    {
        if(!map.containsKey(id))
        {
            throw new IdNotFound("Movie with id " + id + " not found");
        }
        Movie toBeDeleted = this.map.get(id);
        this.map.remove(id);
        this.movies.remove(toBeDeleted);
    }

    @Override
    public void update(String id, Movie updatedMovie)
    {
        if(!map.containsKey(id))
        {
            throw new IdNotFound("Movie with id " + id + " not found");
        }
        int index = movies.indexOf(map.get(id));
        movies.set(index, updatedMovie);
        map.put(id, updatedMovie);
    }

    public boolean contains(String id)
    {
        return map.containsKey(id);
    }
}
