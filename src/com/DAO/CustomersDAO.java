package com.DAO;

import com.objects.Customers;
import com.objects.ForeignCustomers;
import com.objects.VietnameseCustomers;

import java.util.List;

public interface CustomersDAO {
    public void addCustomerToFile(List<Customers> customersList);
    public void addVietnameseCustomerToFile(List<VietnameseCustomers> customersList);
    public void addForeignCustomerToFile(List<ForeignCustomers> customersList);
    public List<Customers> readCustomerList();
    public List<VietnameseCustomers> readVietnameseCustomerList();
    public List<ForeignCustomers> readForeignCustomerList();
}