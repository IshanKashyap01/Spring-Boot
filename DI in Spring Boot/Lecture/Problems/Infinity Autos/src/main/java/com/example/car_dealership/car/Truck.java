package com.example.car_dealership.car;

import com.example.car_dealership.tyre.Tyre;

public class Truck implements Car
{
    private String owner;
    private Tyre tyres;

    public Truck(Tyre tyre)
    {
        this.tyres = tyre;
    }

    @Override
    public void setOwnerName(String name)
    {
        this.owner = name;
    }

    @Override
    public String getOwnerName()
    {
        return this.owner;
    }

    @Override
    public String getInfo()
    {
        return this.owner + " owns a truck" + " with " + this.tyres.getInfo();
    }
}
