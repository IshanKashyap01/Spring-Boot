package com.codingninjas.Foodies.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.codingninjas.Foodies.entity.*;
import com.codingninjas.Foodies.service.MainService;

@RestController
public class MainController
{
    private final MainService service;

    public MainController(MainService mainService)
    {
        this.service = mainService;
    }

    @PostMapping("/Restaurant/add")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant)
    {
        return service.addRestaurant(restaurant);
    }
    
    @PostMapping("/Customer/add")
    public Customer addCustomer(@RequestBody Customer customer)
    {
        return service.addCustomer(customer);
    }

    @PostMapping("/Rating/{customerId}/add/{restaurantName}")
    public Rating addRating(@RequestBody Rating rating, @PathVariable int customerId, @PathVariable String restaurantName)
    {
        return service.addRating(rating, customerId, restaurantName);
    }

    @GetMapping("/ratings")
    public List<Rating> getAllRatings() 
    {
        return service.getAllRatings();
    }
    
    @GetMapping("/customers")
    public List<Customer> getAllCustomers()
    {
        return service.getAllCustomers();
    }

    @GetMapping("/customers/restaurant/{restaurantName}")
    public List<Customer> findByVisitedRestaurants(@PathVariable String restaurantName)
    {
        return service.findByVisitedRestaurants(restaurantName);
    }

    @GetMapping("/customers/restaurant/{restaurantName}/{rating}")
    public List<Customer> getCustomersWithGreaterRatingForRestaurant(@PathVariable String restaurantName, @PathVariable double rating)
    {
        return service.getCustomersWithGreaterRatingForRestaurant(restaurantName, rating);
    }

    @GetMapping("/restaurant/{restaurantName}/average")
    public double getAverageRatings(@PathVariable String restaurantName)
    {
        return service.getAverageRatings(restaurantName);
    }
}