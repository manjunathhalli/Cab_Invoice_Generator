package com.bridgelab;

import java.util.HashMap;
import java.util.Map;

public class InvoiceGenerator {

    Map<Integer,double[]> riderRepository = new HashMap<>();
    Integer id = 0;
    int numberOfRides = 0;

    public double calculateFareForCustomer(String customerType, double distance, int time) {
        if(customerType == "Regular") {
            return calculateFareRegular(distance,time);
        }

        if(customerType == "Premium") {
            return calculateFarePremium(distance,time);
        }
        return -1;
    }

    public double calculateFareRegular(double distance, int time ) {
        final double COST_PER_KM = 10;
        final int COST_PER_MIN = 1;
        final double MIN_RIDE_COST = 5;
        return calculateFare(COST_PER_KM ,COST_PER_MIN,COST_PER_MIN, distance,time);

    }

    public double calculateFarePremium(double distance,int time) {
        final double COST_PER_KM = 15;
        final int COST_PER_MIN = 2;
        final double MIN_RIDE_COST = 20;
        return calculateFare(COST_PER_KM, COST_PER_MIN, MIN_RIDE_COST, distance, time);
    }

    public double calculateFare(double COST_PER_KM, Integer COST_PER_MIN, double MIN_RIDE_COST, double distance, int time) {

        double fare = distance * COST_PER_KM + time * COST_PER_MIN;
        //System.out.println(fare);
        if(fare < MIN_RIDE_COST) {
            return MIN_RIDE_COST;
        }
        return fare;
    }

    public double calculateFareRegular(double[] distanceArray, int[] timeArray) {
        double avg = 0;
        for (int i = 0; i < distanceArray.length; i++) {
            avg += calculateFareRegular(distanceArray[i], timeArray[i]);
            numberOfRides++;

        }
        //System.out.println("Total number of Rides:"+numberOfRides);
        //System.out.println("Total fare of rides:"+avg);
        double avg1 = avg / distanceArray.length;
        //System.out.println("Average fare for rides:" +avg1);
        return avg1;
    }

    public double[] calculateFareReturnArray(double[] distanceArray, int[] timeArray) {
        id++;
        double totalFare = 0;
        for(int i=0;i< distanceArray.length; i++) {
            totalFare = totalFare + calculateFareRegular(distanceArray[i], timeArray[i]);
        }
        double avg = totalFare / distanceArray.length;
        double[] elements = {distanceArray.length,totalFare,avg};
        riderRepository.put(id,elements);
        return elements;
    }

    public double[] getRepository(int userId) {
        return riderRepository.get(userId);
    }
}