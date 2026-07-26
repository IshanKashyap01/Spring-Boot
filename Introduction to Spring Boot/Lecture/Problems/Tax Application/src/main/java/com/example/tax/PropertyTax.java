package com.example.tax;

public class PropertyTax implements Tax
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

    public PropertyTax()
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
        this.taxAmount = 0.05 * this.taxableAmount;
    }

    @Override
    public double getTaxAmount()
    {
        return this.taxAmount;
    }

    @Override
    public String getTaxType()
    {
        return "property";
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
