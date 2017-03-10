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
    private int TOTAL_CLUSTERS;
    private List<Customer> customers;
    private List<Cluster> clusters;

    public KMeans(int clusters)
    {
        this.TOTAL_CLUSTERS = clusters;
        this.customers = new ArrayList<>();
        this.clusters = new ArrayList<>();
    }

    public void start()
    {
        CsvReader reader = new CsvReader();
        // Read in CSV file and put it in List of Customers
        customers = reader.csvReader();
        // Now we need to make centroids out of the List of Customers
        allocateCentroids(customers);
    }

    // Step 1: Select xx points as centroid and assign them to a cluster
    public void allocateCentroids(List<Customer> customers)
    {
        // New random to make random Clusters
        Random random = new Random();
        int r = random.nextInt(customers.size());

        // We need multiple clusters, with each its own centroid
        for (int i = 0; i < TOTAL_CLUSTERS; i++)
        {
            // Making a new Cluster
            Cluster cluster = new Cluster(i);
            // Make new centroid (which is a Customer). Let the centroid get the wineData of the Customer
            Customer centroid = cluster.createCentroids(customers.get(r).getDimensions());
            // Add the centroid to the cluster
            cluster.setCentroid(centroid);
            // Add the new made cluster to the list of clusters so we can get it later
            clusters.add(cluster);
        }
        System.out.println(clusters);
    }

    // Step 2: Assign all points to the closest centroid
    public void calculate()
    {
        assignCluster();
    }

    // Step 2: Assign all points (customers) to the closest centroid
    private void assignCluster()
    {
        // Take all customers of the list
        for (Customer customer : customers)
        {
            //
            for (int i = 0; i < TOTAL_CLUSTERS; i++)
            {
                // get the clusters
                Cluster cluster = clusters.get(i);
                double distance = Customer.euclideanDistance(customer.getDimensions(), cluster.getCentroid().getDimensions());
            }
            //customer.setCluster(clusterId);
            //clusters.get(clusterId).addCustomer(customer);
        }

        /*for (Cluster cluster : clusters)
        {
            allocateCentroids(customers);
            break;
        }*/
    }

    // Step 3: Recompute centroid of each cluster
}
