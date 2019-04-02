package com.objects;

import java.io.Serializable;
import java.time.LocalDate;

public class ForeignCustomers extends Customers implements Serializable {
    private String Nationality;

    public ForeignCustomers(int customerID, String customerName, float unitPrice, LocalDate invoiceDate, float usedQuantity, String nationality) {
        super(customerID, customerName, unitPrice, invoiceDate, usedQuantity);
        Nationality = nationality;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    @Override
    public float totalPayment() {
        float totalPayment;
        totalPayment = getUsedQuantity() * getUnitPrice();
        return totalPayment;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n----------------------\n");
        builder.append("FOREIGN CUSTOMER INVOICE INFORMATION\n");
        builder.append("----------------------\n");
        builder.append("Customer ID: " + getCustomerID() + "\n");
        builder.append("Customer Name: " + getCustomerName() + "\n");
        builder.append("Customer Nationality: " + getNationality() + "\n");
        builder.append("Unit Price: " + getUnitPrice() + "\n");
        builder.append("Invoice Date: " + getInvoiceDate() + "\n");
        builder.append("Used Quantity: " + getUsedQuantity() + "\n");
        builder.append("Total Payment: " + totalPayment() + "\n");
        builder.append("----------------------\n");
        return  builder.toString();
    }
}
