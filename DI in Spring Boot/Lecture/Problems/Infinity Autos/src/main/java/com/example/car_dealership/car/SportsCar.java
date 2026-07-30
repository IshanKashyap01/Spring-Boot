package com.example.car_dealership.car;

import com.example.car_dealership.tyre.Tyre;

public class SportsCar implements Car
{
    private String owner;
    private Tyre tyres;

    public SportsCar(Tyre tyre)
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
        return this.owner + " owns a sports car" + " with " + this.tyres.getInfo();
    }
}
