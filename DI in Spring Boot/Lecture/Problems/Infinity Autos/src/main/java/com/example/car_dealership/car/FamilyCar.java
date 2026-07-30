package com.example.car_dealership.car;

import com.example.car_dealership.tyre.Tyre;

public class FamilyCar implements Car
{
    private String owner;
    private Tyre tyres;

    
    public FamilyCar() {}
    
    public FamilyCar(Tyre tyre)
    {
        this.tyres = tyre;
    }
    
    public void setTyres(Tyre tyres) 
    {
        this.tyres = tyres;
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
        return this.owner + " owns a family car" + " with " + this.tyres.getInfo();
    }
}
