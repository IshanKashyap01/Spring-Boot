package com.example.cover_mate.insurance;

public class TermInsurance implements Insurance
{
    private boolean isMarried;
    private boolean hasChildren;
    private boolean isSalaried;
    private double premium;

    public TermInsurance()
    {
        this.premium = 10_000;
    }

    @Override
    public double getInsurancePremium()
    {
        return this.premium;
    }

    @Override
    public void setInsuranceDetails(boolean isMarried, boolean hasChildren, boolean isSalaried)
    {
        this.isMarried = isMarried;
        this.hasChildren = hasChildren;
        this.isSalaried = isSalaried;
        calculatePremium();
    }

    private void calculatePremium()
    {
        double extra = 0;
        if(this.isSalaried || this.isMarried)
        {
            extra += 1.5 * this.premium;
        }
        if(this.hasChildren)
        {
            extra += 2.0 * this.premium;
        }
        this.premium += extra;
    }

    @Override
    public String getInsuranceName()
    {
        return "Term Insurance";
    }

    public void setMarried(boolean isMarried)
    {
        this.isMarried = isMarried;
    }

    public void setHasChildren(boolean hasChildren)
    {
        this.hasChildren = hasChildren;
    }

    public void setSalaried(boolean isSalaried)
    {
        this.isSalaried = isSalaried;
    }
}