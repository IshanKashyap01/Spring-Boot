package com.codingninjas.Foodies.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Rating;
import com.codingninjas.Foodies.entity.Restaurant;
import com.codingninjas.Foodies.repository.*;
import jakarta.transaction.Transactional;

@Service
public class MainService
{
    private final CustomerRepository customerRepository;
    private final RatingRepository ratingRepository;
    private final RestaurantRepository restaurantRepository;

    public MainService(CustomerRepository customerRepository, RatingRepository ratingRepository, RestaurantRepository restaurantRepository)
    {
        this.customerRepository = customerRepository;
        this.ratingRepository = ratingRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant addRestaurant(Restaurant restaurant)
    {
        return restaurantRepository.save(restaurant);
    }

    public Customer addCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    @Transactional
    public Rating addRating(Rating rating, int customerId, String restaurantName)
    {
        Customer customer = customerRepository.findById(customerId).get();
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        // customer visited the restaurant
        customer.getVisitedRestaurants().add(restaurant);
        rating.setCustomer(customer);
        rating.setRestaurant(restaurant);
        return ratingRepository.save(rating);
    }

    public List<Rating> getAllRatings()
    {
        return ratingRepository.findAll();
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public List<Customer> findByVisitedRestaurants(String restaurantName)
    {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        return customerRepository.findByVisitedRestaurants(restaurant);
    }

    public List<Customer> getCustomersWithGreaterRatingForRestaurant(String restaurantName, double rating)
    {
        return customerRepository.getCustomersWithGreaterRatingForRestaurant(restaurantName, rating);
    }

    public double getAverageRatings(String restaurantName)
    {
        return restaurantRepository.findAverageRatings(restaurantName);
    }
}