package com.objects;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class VietnameseCustomers extends Customers implements Serializable {
    private int customerType;
    private float quota;

    public VietnameseCustomers(int customerID, String customerName, float unitPrice, LocalDate invoiceDate, float usedQuantity, int customerType, float quota) {
        super(customerID, customerName, unitPrice, invoiceDate, usedQuantity);
        this.customerType = customerType;
        this.quota = quota;
    }

    public int getCustomerType() {
        return customerType;
    }

    public void setCustomerType(int customerType) {
        this.customerType = customerType;
    }

    public float getQuota() {
        return quota;
    }

    public void setQuota(float quota) {
        this.quota = quota;
    }

    @Override
    public float totalPayment() {
        float totalPayment;

        if (getUsedQuantity() <= getQuota()) {
            totalPayment = getUsedQuantity() * getUnitPrice();
        } else {
            totalPayment =
                    ((getUsedQuantity() * getUnitPrice())
                            +
                            (getUsedQuantity() - getQuota()) * getUnitPrice() * (float) 2.5);
        }
        return totalPayment;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n----------------------\n");
        builder.append("VIETNAMESE CUSTOMER INVOICE INFORMATION\n");
        builder.append("----------------------\n");
        builder.append("Customer ID: " + getCustomerID() + "\n");
        builder.append("Customer Name: " + getCustomerName() + "\n");
        builder.append("Customer Type: " + getCustomerType() + "\n");
        builder.append("Unit Price: " + getUnitPrice() + "\n");
        builder.append("Invoice Date: " + getInvoiceDate() + "\n");
        builder.append("Used Quantity: " + getUsedQuantity() + "\n");
        builder.append("Quota: " + getQuota() + "\n");
        builder.append("Total Payment: " + totalPayment() + "\n");
        builder.append("----------------------\n");
        return  builder.toString();
    }
}
