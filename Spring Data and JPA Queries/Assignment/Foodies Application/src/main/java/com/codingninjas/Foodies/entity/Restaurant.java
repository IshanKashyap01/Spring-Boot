package com.codingninjas.Foodies.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity @Table(name = "restaurant")
public class Restaurant
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonManagedReference("restaurant-rating")
    private List<Rating> ratings;

    @Column
    private String name;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public List<Rating> getRatings()
    {
        return ratings;
    }
    
    public void setRatings(List<Rating> ratings)
    {
        this.ratings = ratings;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}