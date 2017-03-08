package com.company.Controllers;

import com.company.Models.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Donovan on 8-3-2017.
 */
public class CsvReader
{
    public ArrayList<Customer> csvReader()
    {
        //String dataFile = getClass().getResource("WineDataTransposed.csv").getFile();
        String dataFile = "C:\\Users\\Donovan\\Desktop\\Dev6aq\\DataScienceOpdr1\\src\\com\\company\\Resources\\WineData.csv";
        BufferedReader buffedReader = null;
        String line = "";
        String csvSplitter = ",";
        ArrayList<Customer> customers = new ArrayList<>();
        int count = 0;

        try
        {
            buffedReader = new BufferedReader(new FileReader(dataFile));
            while ((line = buffedReader.readLine()) != null)
            {
                String[] data = line.split(csvSplitter);

                //Transpose data so we have 100 Customers
                //Each Customer has 32 sorts of wine offers (dimensions)
                for (int i = 0; i < data.length; i++)
                {
                    if (i >= customers.size()) {
                        System.out.println(customers.size());
                        customers.add(new Customer(32));
                    }

                    customers.get(i).addDimension(count, Integer.parseInt(data[i]));
                }
                count++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (buffedReader != null)
            {
                try
                {
                    buffedReader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return customers;
    }
}
