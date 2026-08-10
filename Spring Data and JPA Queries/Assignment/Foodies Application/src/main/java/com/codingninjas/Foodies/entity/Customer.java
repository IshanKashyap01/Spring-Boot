package com.codingninjas.Foodies.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity @Table(name = "customer")
public class Customer
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference("customer-rating")
    private List<Rating> ratings;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable
    (
        name = "customer_restaurant",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    List<Restaurant> visitedRestaurants;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Rating> getRatings()
    {
        return ratings;
    }

    public void setRatings(List<Rating> ratings)
    {
        this.ratings = ratings;
    }

    public List<Restaurant> getVisitedRestaurants()
    {
        return visitedRestaurants;
    }

    public void setVisitedRestaurants(List<Restaurant> visitedRestaurants)
    {
        this.visitedRestaurants = visitedRestaurants;
    }
}