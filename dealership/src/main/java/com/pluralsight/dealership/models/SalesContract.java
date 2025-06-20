package com.pluralsight.dealership.models;

public class SalesContract extends Contract {
    protected String saleDate;
    private double salePrice;

    public SalesContract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSold, int id, int vehicleId, String saleDate, double salePrice) {
        super(contractDate, customerName, customerEmail, vehicleSold, id, vehicleId);
        this.saleDate = saleDate;
        this.salePrice = salePrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
}
