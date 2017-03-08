package com.company.Controllers;

import java.util.Scanner;

public class Main {

    private static int TOTAL_CLUSTERS;
    private static int TOTAL_ITERATIONS;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many clusters do you want: ");
        TOTAL_CLUSTERS = scanner.nextInt();
        System.out.println("How many iterations do you want: ");
        TOTAL_ITERATIONS = scanner.nextInt();
        System.out.println("You have chosen for " + TOTAL_CLUSTERS + " clusters and " + TOTAL_ITERATIONS + " iterations");

        /*while (TOTAL_ITERATIONS > 0)
        {
            TOTAL_ITERATIONS--;
        }*/
    }
}
