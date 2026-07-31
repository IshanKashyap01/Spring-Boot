package com.example.tax;

public class IncomeTax implements Tax
{
    /*
    1. Create the following attributes.
        a. taxableAmount (double)
        b. taxAmount (double)
        c. isTaxPayed (boolean)
    2. Make this class an implementation of Tax interface and override the interface methods.
    3. Using constructor initialize the isTaxPayed boolean false.
    */
    double taxableAmount;
    double taxAmount;
    boolean isTaxPayed;

    public IncomeTax()
    {
        this.isTaxPayed = false;
    }

    @Override
    public void setTaxableAmount(double amount)
    {
        this.taxableAmount = amount;
    }

    @Override
    public void calculateTaxAmount()
    {
        if(this.taxableAmount < 300_000)
        {
            return;
        }
        if(this.taxableAmount >= 300_000 && this.taxableAmount < 600_000)
        {
            this.taxAmount = 0.05 * this.taxableAmount;
        }
        else if(this.taxableAmount < 900_000)
        {
            this.taxAmount = 0.10 * this.taxableAmount;
        }
        else if(this.taxableAmount < 1_200_000)
        {
            this.taxAmount = 0.15 * this.taxableAmount;
        }
        else if(this.taxableAmount < 1_500_000)
        {
            this.taxAmount = 0.20 * this.taxableAmount;
        }
        else
        {
            this.taxAmount = 0.30 * this.taxableAmount;
        }
    }

    @Override
    public double getTaxAmount()
    {
        return this.taxAmount;
    }

    @Override
    public String getTaxType()
    {
        return "income";
    }

    @Override
    public boolean isTaxPayed()
    {
        return this.isTaxPayed;
    }

    @Override
    public void payTax()
    {
        if(!this.isTaxPayed)
        {
            this.isTaxPayed = true;
            System.out.print("You have selected " + this.getTaxType() + " and your tax amount is: ");
            System.out.println(this.getTaxAmount());
        }
    }   
}