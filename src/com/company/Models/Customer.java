package com.company.Models;

/**
 * Created by Donovan on 8-3-2017.
 */
public class Customer
{
    private double[] dimensions;
    public int clusterId = 0;

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

    public static double euclideanDistance(double[] customers, double[] centroids)
    {
        double distance = 0;
        for (int i = 0; i < centroids.length; i++)
        {
            distance += Math.pow(customers[i] - centroids[i], 2);
        }
        return Math.sqrt(distance);
    }

    public void setCluster(int cluster)
    {
        this.clusterId = cluster;
    }
}
