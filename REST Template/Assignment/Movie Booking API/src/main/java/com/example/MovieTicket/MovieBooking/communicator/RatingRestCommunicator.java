package com.example.MovieTicket.MovieBooking.communicator;

import java.util.Map;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.MovieTicket.MovieBooking.Exceptions.IdAlreadyExist;
import com.example.MovieTicket.MovieBooking.Exceptions.IdNotFound;
import com.example.MovieTicket.MovieBooking.service.MovieService;

@Service
public class RatingRestCommunicator
{
    private final String url;
    private final RestTemplate template;
    private final MovieService service;

    public RatingRestCommunicator(RestTemplateBuilder builder, MovieService service)
    {
        this.template = builder.build();
        this.url = "http://localhost:8082/ratings";
        this.service = service;
    }

    public long getRating(String id)
    {
        return template.getForObject(url + "/" + id, Long.class);
    }

    public void addRating(Map<String, Long> ratingsMap)
    {
        template.postForLocation(url, ratingsMap);
    }

    public void updateRating(Map<String, Long> ratingsMap)
    {
        template.put(url, ratingsMap);
    }

    public void deleteRating(String id)
    {
        template.delete(url + "/" + id);
    }
}