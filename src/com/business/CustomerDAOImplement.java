package com.business;

import com.DAO.CustomersDAO;
import com.objects.Customers;
import com.objects.ForeignCustomers;
import com.objects.VietnameseCustomers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CustomerDAOImplement implements CustomersDAO {
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String VIETNAMESE_CUSTOMER_FILE = "vi_customers.txt";
    private static final String FOREIGN_CUSTOMER_FILE = "fo_customers.txt";

    /**
     * Add a customer to TXT file
     * @param customersList
     */
    @Override
    public void addCustomerToFile(List<Customers> customersList){
        FileOutputStream fos;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(new File(CUSTOMER_FILE));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(customersList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(oos);
        }
    }

    /**
     * Add a Vietnamese customer to TXT file
     * @param vietnameseCustomersList
     */
    // add Vietnamese customer to file
    @Override
    public void addVietnameseCustomerToFile(List<VietnameseCustomers> vietnameseCustomersList){
        FileOutputStream fos;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(new File(VIETNAMESE_CUSTOMER_FILE));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(vietnameseCustomersList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(oos);
        }
    }

    /**
     * Add a foreign customer to TXT file
     * @param customersList
     */
    // add Vietnamese customer to file
    @Override
    public void addForeignCustomerToFile(List<ForeignCustomers> customersList){
        FileOutputStream fos;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(new File(FOREIGN_CUSTOMER_FILE));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(customersList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(oos);
        }
    }

    /**
     * Read CUSTOMER list from TXT file
     * @return List<Customers>
     */
    @Override
    public List<Customers> readCustomerList() {
        List<Customers> customersList = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(new File(CUSTOMER_FILE));
            ois = new ObjectInputStream(fis);
            customersList = (List<Customers>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeStream(fis);
            closeStream(ois);
        }
        return customersList;
    }


    /**
     * Read VIETNAMESE CUSTOMER list from TXT file
     * @return List<VietnameseCustomers>
     */
    @Override
    public List<VietnameseCustomers> readVietnameseCustomerList() {
        List<VietnameseCustomers> vietnameseCustomersList = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(new File(VIETNAMESE_CUSTOMER_FILE));
            ois = new ObjectInputStream(fis);
            vietnameseCustomersList = (List<VietnameseCustomers>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeStream(fis);
            closeStream(ois);
        }
        return vietnameseCustomersList;
    }


    /**
     * Read FOREIGN CUSTOMER list from TXT file
     * @return List<ForeignCustomers>
     */
    @Override
    public List<ForeignCustomers> readForeignCustomerList() {
        List<ForeignCustomers> foreignCustomersList = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(new File(FOREIGN_CUSTOMER_FILE));
            ois = new ObjectInputStream(fis);
            foreignCustomersList = (List<ForeignCustomers>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeStream(fis);
            closeStream(ois);
        }
        return foreignCustomersList;
    }

    /**
     * Close input stream
     * @param is: input stream
     */
    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Close output stream
     * @param os: output stream
     */
    private void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}