package com.objects;

import java.io.Serializable;
import java.time.LocalDate;

public class Customers implements Serializable {

    /**
     * General Customer information
     * */
    private int customerID;
    private String customerName;
    private float unitPrice;
    private LocalDate invoiceDate;
    private float usedQuantity;

    /**
     * Default constructor
     * */
    public Customers() {
    }


    /**
     * Constructor
     * @param customerID
     * @param customerName
     * @param unitPrice
     * @param invoiceDate
     * @param usedQuantity
     */
    public Customers(int customerID, String customerName, float unitPrice, LocalDate invoiceDate, float usedQuantity) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.unitPrice = unitPrice;
        this.invoiceDate = invoiceDate;
        this.usedQuantity = usedQuantity;
    }

    /**
     * Getters and Setters
     * */
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public float getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(float usedQuantity) {
        this.usedQuantity = usedQuantity;
    }


    /**
     * Method calculate the total payment for customer invoice
     * @return 0
     */
    public float totalPayment(){
        return 0;
    }
}