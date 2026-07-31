package com.example.cover_mate.insurance;

public class HealthInsurance implements Insurance
{
    private boolean isSmoker;
    private boolean isDrinker;
    private boolean hasPreviousConditions;
    private double premium;

    public HealthInsurance()
    {
        this.premium = 10_000;
    }

    @Override
    public double getInsurancePremium() 
    {
        return this.premium;
    }

    @Override
    public void setInsuranceDetails(boolean isSmoker, boolean isDrinker, boolean hasPreviousConditions)
    {
        this.isSmoker = isSmoker;
        this.isDrinker = isDrinker;
        this.hasPreviousConditions = hasPreviousConditions;
        calculatePremium();
    }

    private void calculatePremium()
    {
        double extra = 0;
        if(this.isSmoker || this.isDrinker)
        {
            extra += 1.5 * this.premium;
        }
        if(this.hasPreviousConditions)
        {
            extra += 2 * this.premium;
        }
        this.premium += extra;
    }

    @Override
    public String getInsuranceName()
    {
        return "Health Insurance";
    }

    public void setSmoker(boolean isSmoker)
    {
        this.isSmoker = isSmoker;
    }

    public void setDrinker(boolean isDrinker)
    {
        this.isDrinker = isDrinker;
    }

    public void setHasPreviousConditions(boolean hasPreviousConditions)
    {
        this.hasPreviousConditions = hasPreviousConditions;
    }
}