package com.company.Controllers;

import com.company.Models.Cluster;
import com.company.Models.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Donovan on 9-3-2017.
 */
public class KMeans
{
    private final int TOTAL_CLUSTERS;
    private List<Customer> customers;
    private List<Cluster> clusters;
    //private double

    public KMeans(int clusters)
    {
        this.TOTAL_CLUSTERS = clusters;
        this.customers = new ArrayList<>();
        this.clusters = new ArrayList<>();
    }

    public void start()
    {
        CsvReader reader = new CsvReader();
        customers = reader.csvReader();
        allocateCentroids(customers);
    }

    public void allocateCentroids(List<Customer> customers)
    {
        Random random = new Random();
        int r = random.nextInt(customers.size());

        for (int i = 0; i < TOTAL_CLUSTERS; i++)
        {
            Cluster cluster = new Cluster(i);
            Customer centroid = cluster.createCentroids(customers.get(r).getDimensions());
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        plotClusters();
    }

    private List getCentroids()
    {
        List<Customer> centroids = new ArrayList<>(TOTAL_CLUSTERS);

        for (Cluster cluster : clusters)
        {
            Customer centroid = cluster.getCentroid();
            Customer customer = new Customer(centroid.getDimensions());
            centroids.add(customer);
            System.out.println(customer);
        }
        return centroids;
    }

    private void plotClusters()
    {
        for (int i = 0; i < TOTAL_CLUSTERS; i++)
        {
            Cluster cluster = clusters.get(i);
            cluster.plotCluster();
        }
    }

    public void calculate()
    {
        boolean done = false;

        while (!done)
        {
            assignCluster();

            List<Customer> oldCentroids = getCentroids();
            calculateCentroids();
            List<Customer> newCentroids = getCentroids();

            double distance = 0.0;
            for (int i = 0; i < oldCentroids.size(); i++)
            {
                distance += Customer.distance(oldCentroids.get(i).getDimensions(), newCentroids.get(i).getDimensions());
            }
            plotClusters();

            if (distance == 0)
            {
                done = true;
            }
        }
    }

    private void assignCluster()
    {
        double max = Double.MAX_VALUE;
        double min = max;
        int clusterId = 0;
        double distance = 0.0;

        for (Customer customer : customers)
        {
            min = max;
            for (int i = 0; i < TOTAL_CLUSTERS; i++)
            {
                Cluster cluster = clusters.get(i);
                distance = Customer.distance(customer.getDimensions(), cluster.getCentroid().getDimensions());

                if (distance < min)
                {
                    min = distance;
                    clusterId = i;
                }
            }
            customer.setCluster(clusterId);
            clusters.get(clusterId).addCustomer(customer);
            //System.out.println(customer);
        }

        for (Cluster cluster : clusters)
        {
            if (cluster.getCustomer().size() == 0)
            {
                removeClusters();
                allocateCentroids(customers);
                assignCluster();
                break;
            }
        }
    }

    private void removeClusters()
    {
        clusters.removeAll(clusters);
    }

    private void calculateCentroids()
    {
        for (Cluster cluster : clusters)
        {
            double[] dimensions = new double[customers.get(0).getDimensions().length];
            List<Customer> customers = cluster.getCustomer();

            for (int i = 0; i < dimensions.length; i++)
            {
                double sum = 0;
                for (Customer customer : customers)
                {
                    sum += customer.getDimensions()[i];
                }
                dimensions[i] = sum / customers.size();
            }
            Customer centroid = new Customer(dimensions);
            cluster.setCentroid(centroid);
        }
    }
}
