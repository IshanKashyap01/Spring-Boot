package com.example.cover_mate;

import com.example.cover_mate.insurance.Insurance;

public class Customer 
{
    private String name;
    private byte age;
    private Insurance insurance;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public byte getAge()
    {
        return age;
    }

    public void setAge(byte age)
    {
        this.age = age;
    }

    public Insurance getInsurance()
    {
        return insurance;
    }

    public void setInsurance(Insurance insurance)
    {
        this.insurance = insurance;
    }
}
