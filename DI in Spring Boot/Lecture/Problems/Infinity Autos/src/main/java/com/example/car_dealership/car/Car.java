package com.example.car_dealership.car;

public interface Car 
{
    /**
     * Sets the name of the owner
     */
    void setOwnerName(String name);
    /**
     * returns the name of the owner
     */
    String getOwnerName();
    /**
     * Returns the name of the vehicle and its owner's name
     */
    String getInfo();
}
