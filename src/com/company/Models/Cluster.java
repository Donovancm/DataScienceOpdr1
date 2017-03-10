package com.company.Models;

import java.util.List;
import java.util.ArrayList;
import java.util.jar.Pack200;

/**
 * Created by Donovan on 9-3-2017.
 */
public class Cluster {

    public List<Customer> customers;
    public Customer centroid;
    public int id;

    public Cluster(int id)
    {
        this.id = id;
        this.customers = new ArrayList();
        this.centroid = null;
    }

    public Customer createCentroids(double[] dimensions)
    {
        Customer centroid = new Customer(dimensions);

        return centroid;
    }

    public Customer getCentroid()
    {
        return centroid;
    }

    public void setCentroid(Customer centroid)
    {
        this.centroid = centroid;
    }

    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
}
