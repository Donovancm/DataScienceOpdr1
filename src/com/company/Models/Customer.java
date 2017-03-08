package com.company.Models;

/**
 * Created by Donovan on 8-3-2017.
 */
public class Customer
{
    private double[] dimensions;

    public Customer(int offers)
    {
        this.dimensions = new double[offers];
    }

    public Customer(double[] dimensions)
    {
        this.dimensions = dimensions;
    }

    public void addDimension(int wineOffer, int wineBought)
    {
        dimensions[wineOffer] = wineBought;
    }

    public double[] getDimensions()
    {
        return dimensions;
    }
}
