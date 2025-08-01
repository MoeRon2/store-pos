package com.nimetfidan.pos.model;

public class Sale {
	private int id;
    private String timestamp;
    private double totalAmount;
    private String paymentType;

    public Sale(int id, String timestamp, double totalAmount, String paymentType) {
    	this.id = id;
        this.timestamp = timestamp;
        this.totalAmount = totalAmount;
        this.paymentType = paymentType;
    }
    public int getId() { return id; }
    public String getTimestamp() { return timestamp; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentType() { return paymentType; }

    @Override
    public String toString() {
        return "Sale #" + id + " - " + totalAmount + " - " + timestamp;
    }
}
