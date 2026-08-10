package com.codingninjas.Foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.codingninjas.Foodies.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>
{
    Restaurant findByName(String name);

    @Query
    (
        nativeQuery = true,
        value = 
        """
        select avg(r.rating) from rating r join restaurant rt
        on r.restaurant_id = rt.id
        where rt.name = ?1
        """
    )
    double findAverageRatings(String restaurantName);
}