package com.business;

import com.DAO.CustomersDAO;
import com.objects.Customers;
import com.objects.ForeignCustomers;
import com.objects.VietnameseCustomers;
import java.time.LocalDate;
import java.util.*;

public class CustomersManagement{
    private static Scanner scanner = new Scanner(System.in);
    private List<Customers> customersList;
    private List<VietnameseCustomers> vietnameseCustomersList;
    private List<ForeignCustomers> foreignCustomersList;
    private CustomerDAOImplement customerDAOImplement;

    /**
     * Constructor
     * Initialize 3 lists of customers by CustomerDAOImplement class
     */
    public CustomersManagement() {
        customerDAOImplement = new CustomerDAOImplement();
        this.customersList = customerDAOImplement.readCustomerList();
        this.vietnameseCustomersList = customerDAOImplement.readVietnameseCustomerList();
        this.foreignCustomersList = customerDAOImplement.readForeignCustomerList();
    }

    /**
     * Getters and Setters
     * */
    public List<Customers> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(List<Customers> customersList) {
        this.customersList = customersList;
    }

    public List<VietnameseCustomers> getVietnameseCustomersList() {
        return vietnameseCustomersList;
    }

    public void setVietnameseCustomersList(List<VietnameseCustomers> vietnameseCustomersList) {
        this.vietnameseCustomersList = vietnameseCustomersList;
    }

    public List<ForeignCustomers> getForeignCustomersList() {
        return foreignCustomersList;
    }

    public void setForeignCustomersList(List<ForeignCustomers> foreignCustomersList) {
        this.foreignCustomersList = foreignCustomersList;
    }

    /**
     * MAIN PROGRAM EXECUTION
     */
    public void eInvoiceManagementProgram(){
        String option;
        boolean exit = false;

        showMenu();
        while (true){
            option = scanner.nextLine();
            switch ( option ){
                case "0":
                    System.out.println("Program exited.");
                    exit = true;
                    break;
                case "1":
                    addCustomer(1);
                    break;
                case "2":
                    addCustomer(2);
                    break;
                case "3":
                    showListOfCustomers();
                    break;
                case "4":
                    getTotalQuantityOFEachCustomerType();
                    break;
                case "5":
                    getAveragePaymentOfForeigners();
                    break;
                case "6":
                    showListOfCustomersByYearAndMonth();
                    break;
                case "7":
                    showListOfOverQuotaCustomer();
                    break;
                case "8":
                    showCustomerListByDateRange();
                    break;
                default:
                    System.out.println("\nYour option is invalid. Please re-select your option.");
                    break;
            }
            if (exit == true){
                break;
            }
            showMenu();
        }
    }

    /**
     * Add customer to the related list and TXT file by provided customer type
     * @param customerType
     */
    // add customer to list
    public void addCustomer(int customerType){
        if (customerType == 1) {
            // add vietnamese customers
            VietnameseCustomers vietnameseCustomers = enterVietnameseCustomerInformation();
            vietnameseCustomersList.add(vietnameseCustomers);
            customersList.add(vietnameseCustomers);
            customerDAOImplement.addVietnameseCustomerToFile(vietnameseCustomersList);
            customerDAOImplement.addCustomerToFile(customersList);
        } else {
            // add foreign customers
            ForeignCustomers foreignCustomers = enterForeignCustomerInformation();
            foreignCustomersList.add(foreignCustomers);
            customersList.add(foreignCustomers);
            customerDAOImplement.addForeignCustomerToFile(foreignCustomersList);
            customerDAOImplement.addCustomerToFile(customersList);
        }
    }

    /**
     * Enter all Vietnamese customer information
     * @return VietnameseCustomers
     */
    public VietnameseCustomers enterVietnameseCustomerInformation(){
        System.out.println("\n------------------------------------------------\n");
        System.out.println("ENTER VIETNAMESE CUSTOMER INFORMATION\n");
        // Customer ID - identity
        int customerID = (customersList.size() > 0) ? (customersList.size() + 1) : 1;
        System.out.println("Customer ID: " + customerID);
        // Customer Name
        String customerName = enterCustomerName();
        // Customer Type
        int customerType = selectCustomerType();
        // Customer Quota
        float quota = enterQuota();
        // Customer Used Quantity
        float usedQuantity = enterUsedQuantity();
        // Unit price
        float unitPrice = enterUnitPrice();
        // Invoice Date
        LocalDate invoiceDate = enterInvoiceDate();
        System.out.println("\n------------------------------------------------\n");
        // Add a vietnamese customer
        return new VietnameseCustomers(customerID, customerName, unitPrice, invoiceDate, usedQuantity, customerType, quota);
    }

    /**
     * Enter all Foreign customer information
     * @return VietnameseCustomers
     */
    public ForeignCustomers enterForeignCustomerInformation(){
        System.out.println("\n------------------------------------------------\n");
        System.out.println("ENTER FOREIGN CUSTOMER INFORMATION\n");
        // Customer ID - identity
        int customerID = (customersList.size() > 0) ? (customersList.size() + 1) : 1;
        System.out.println("Customer ID: " + customerID);
        // Customer Name
        String customerName = enterCustomerName();
        // Nationality
        String customerNationality = enterCustomerNationality();
        // Customer Used Quantity
        float usedQuantity = enterUsedQuantity();
        // Unit price
        float unitPrice = enterUnitPrice();
        // Invoice Date
        LocalDate invoiceDate = enterInvoiceDate();
        System.out.println("\n------------------------------------------------\n");

        // Add a vietnamese customer
        return new ForeignCustomers(customerID, customerName, unitPrice, invoiceDate, usedQuantity, customerNationality);
    }


    /**
     * Enter customer name by scanner
     * @return customerName
     */
    public String enterCustomerName(){
        System.out.println("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        return customerName;
    }

    /**
     * Enter unit price by scanner
     * @return unitPrice
     */
    public float enterUnitPrice() {
        // Unit price
        System.out.println("Enter Unit Price: ");
        while (true) {
            try {
                float unitPrice = Float.parseFloat(scanner.nextLine());
                return unitPrice;
            } catch (Exception ex) {
                System.out.println("\nUnit price is invalid. Please re-enter unit price.\n");
            }
        }
    }

    /**
     * Enter quantity by scanner
     * @return quantity
     */
    public float enterUsedQuantity(){
        // Customer Used Quantity
        System.out.println("Enter Customer Used Quantity: ");
        while (true) {
            try {
                float usedQuantity = Float.parseFloat(scanner.nextLine());
                return usedQuantity;
            } catch (Exception e) {
                System.out.println("\nCustomer used quantity is invalid. Please re-enter used quantity number.\n");
            }
        }
    }

    /**
     * Enter customer nationality by scanner
     * @return customerNationality
     */
    public String enterCustomerNationality(){
        System.out.println("Enter Customer Nationality: ");
        String customerNationality = scanner.nextLine();
        return customerNationality;
    }

    /**
     * Enter quota by scanner
     * @return quota
     */
    public float enterQuota(){
        System.out.println("Enter Customer Quota: ");
        while (true) {
            try {
                float quota = Float.parseFloat(scanner.nextLine());
                return quota;
            } catch (Exception e) {
                System.out.println("\nQuota is invalid. Please re-enter quota.\n");
            }
        }
    }

    /**
     * Select customer type by scanner
     * @return customerType
     */
    public int selectCustomerType(){
        System.out.println("Select Customer Type: \n");
        System.out.println("1. Expenses \n2. Business\n3. Manufacture\n");
        while (true) {
            try {
                int customerType = Integer.parseInt(scanner.nextLine());
                if (customerType != 1 && customerType != 2 && customerType != 3) {
                    throw new Exception();
                } else {
                    return customerType;
                }
            } catch (Exception e) {
                System.out.println("\nInvalid customer type. Please re-select customer type.\n");
            }
        }
    }


    /**
     * Append invoice Day/Month/Year from 3 sub-methods into invoiceDate
     * @return LocalDate invoiceDate
     */
    public LocalDate enterInvoiceDate(){
        System.out.println("Enter Invoice Date:\n");
        while (true){
            try {
                System.out.println("Invoice day: ");
                int day = enterDay();

                System.out.println("Invoice month: ");
                int month = enterMonth();

                System.out.println("Invoice year: ");
                int year = enterYear();

                if (LocalDate.of(year, month, day).isBefore(LocalDate.now()) || LocalDate.of(year, month, day).isEqual(LocalDate.now())) {
                    return LocalDate.of(year, month, day);
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("\nEntered invoice date is not valid. Please re-enter the date before or equal today");
            }
        }
    }

    /**
     * Append invoice Day/Month/Year from 3 sub-methods into a Date
     * @return LocalDate Date
     */
    public LocalDate enterDate(){
        while (true){
            try {
                System.out.println("Day: ");
                int day = enterDay();

                System.out.println("Month: ");
                int month = enterMonth();

                System.out.println("Year: ");
                int year = enterYear();

                if (LocalDate.of(year, month, day).isBefore(LocalDate.now()) || LocalDate.of(year, month, day).isEqual(LocalDate.now())) {
                    return LocalDate.of(year, month, day);
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("\nEntered date is not valid. Please re-enter the date before or equal today");
            }
        }
    }

    /**
     * Enter invoice Day by scanner
     * @return invoiceDay
     */
    public int enterDay(){
        while (true) {
            try {
                int day = Integer.parseInt(scanner.nextLine());
                if (day <= 0 || day > 31) {
                    throw new Exception();
                } else {
                    return day;
                }
            } catch (Exception e) {
                System.out.println("\nDay is invalid. Please re-enter day.\n");
            }
        }
    }

    /**
     * Enter invoice Month by scanner
     * @return invoiceMonth
     */
    public int enterMonth(){
        while (true) {
            try {
                int month = Integer.parseInt(scanner.nextLine());
                if (month <= 0 || month > 12) {
                    throw new Exception();
                } else {
                    return month;
                }
            } catch (Exception e) {
                System.out.println("\nMonth is invalid. Please re-enter month.\n");
            }
        }
    }

    /**
     * Enter invoice Year by scanner
     * @return invoiceMonth
     */
    public int enterYear(){
        while (true) {
            try {
                int year = Integer.parseInt(scanner.nextLine());
                if (year <= 0 || year <= 1900 || year > LocalDate.now().getYear()) {
                    throw new Exception();
                } else {
                    return year;
                }
            } catch (Exception e) {
                System.out.println("\nYear is invalid. Please re-enter year.\n");
            }
        }
    }

    /**
     * Retrieve total payment depend on the entered option
     */
    public void getTotalQuantityOFEachCustomerType(){
        float totalQuantityOfVietnamese = 0;
        float totalQuantityOfForeigners = 0;
        String result;
        System.out.println("Enter option to show the total quantity:");
        System.out.println("\n1. Vietnamese customers\n2. Foreign customers\n3. All customers");
        while (true) {
            try {
                int option = Integer.parseInt(scanner.nextLine());

                Iterator<VietnameseCustomers> vietnameseCustomersIterator = vietnameseCustomersList.iterator();

                while (vietnameseCustomersIterator.hasNext()) {
                    VietnameseCustomers vietnameseCustomer = vietnameseCustomersIterator.next();
                    totalQuantityOfVietnamese += vietnameseCustomer.getUsedQuantity();
                }

                Iterator<ForeignCustomers> foreignCustomersIterator = foreignCustomersList.iterator();
                while (foreignCustomersIterator.hasNext()) {
                    ForeignCustomers foreignCustomer = foreignCustomersIterator.next();
                    totalQuantityOfForeigners += foreignCustomer.getUsedQuantity();
                }

                if (option == 1) {
                    result = ("\n>> Total quantity of Vietnamese customers: " + totalQuantityOfVietnamese);
                    frameResult(result);
                    break;
                } else if (option == 2) {
                    result = ("\n>> Total quantity of Foreign customers: " + totalQuantityOfForeigners);
                    frameResult(result);
                    break;
                } else if (option == 3) {
                    result = ("\n>> Total quantity of Vietnamese customers: " + totalQuantityOfVietnamese + "\n" +
                            (">> Total quantity of Foreign customers: " + totalQuantityOfForeigners));
                    frameResult(result);
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("\nInvalid option. Please re-enter option to show.\n");
            }

        }
    }

    /**
     * Calculate the average payment of foreign customers
     */
    public void getAveragePaymentOfForeigners(){
        float totalPaymentOfForeigners = 0;
        float averagePayment = 0;
        String result;
        try {
            Iterator<ForeignCustomers> foreignCustomersIterator = foreignCustomersList.iterator();
            while (foreignCustomersIterator.hasNext()){
                ForeignCustomers customer = foreignCustomersIterator.next();
                totalPaymentOfForeigners += customer.totalPayment();
                if (foreignCustomersList.size() != 0) {
                    averagePayment = totalPaymentOfForeigners/foreignCustomersList.size();
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nPlease add foreign customer before exporting average payment.\n");
        }
        result = ("\nAverage total payment of foreigners: " + averagePayment);
        frameResult(result);
    }


    /**
     * Show customer list depend on the entered options
     * 1 - All customers
     * 2 - Vietnamese customers
     * 3 - Foreign customers
     */
    public void showListOfCustomers(){
        System.out.println("Select list to show:");
        System.out.println("\n1. All customers\n2. Vietnamese customers\n3. Foreign customers");
        String resultHeader;
        while (true){
            try {
                int typeList = Integer.parseInt(scanner.nextLine());
                if (typeList == 1) {
                    resultHeader = "\nLIST OF ALL CUSTOMERS";
                    frameHeader(resultHeader);
                    for (Customers customer : customersList) {
                        String result = customer.toString();
                        frameResult(result);
                    }
                    break;
                } else if (typeList == 2){
                    resultHeader = "\nLIST OF ALL VIETNAMESE CUSTOMERS\n";
                    frameHeader(resultHeader);
                    for (VietnameseCustomers vietnameseCustomers : vietnameseCustomersList){
                        String result = vietnameseCustomers.toString();
                        frameResult(result);
                    }
                    break;
                } else if (typeList == 3){
                    resultHeader = "\nLIST OF ALL FOREIGN CUSTOMERS";
                    frameHeader(resultHeader);
                    for (ForeignCustomers foreignCustomers : foreignCustomersList){
                        String result = foreignCustomers.toString();
                        frameResult(result);
                    }
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("\nInvalid type list. Please re-select type list.\n");
            }
        }
    }

    /**
     * Show customer list of the specific year and month
     */
    public void showListOfCustomersByYearAndMonth() {
        for (Customers customer : getCustomersByYearAndMonth()) {
            String result = customer.toString();
            frameResult(result);
        }
    }

    /**
     * Show customer list of the customers that have over-quota used
     */
    public void showListOfOverQuotaCustomer(){
        List<VietnameseCustomers> vietnameseCustomersOverQuotaList = new ArrayList<>();
        String result;
        String resultHeader;
        while (true){
            try {
                Iterator<VietnameseCustomers> vietnameseCustomersIterator = vietnameseCustomersList.iterator();
                while (vietnameseCustomersIterator.hasNext()){
                    VietnameseCustomers vietnameseCustomers = vietnameseCustomersIterator.next();
                    if (vietnameseCustomers.getUsedQuantity() > vietnameseCustomers.getQuota()) {
                        vietnameseCustomersOverQuotaList.add(vietnameseCustomers);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                resultHeader = "\nLIST OF VIETNAMESE CUSTOMERS THAT HAVE OVER QUOTA QUANTITY:";
                frameHeader(resultHeader);
                for (VietnameseCustomers vietnameseCustomers : vietnameseCustomersOverQuotaList) {
                    result = vietnameseCustomers.toString();
                    frameResult(result);
                }
                break;
            }

        }
    }

    /**
     * Retrieve customer list base on the entered date range
     * */
    public void showCustomerListByDateRange(){
        LocalDate fromDate;
        LocalDate endDate;
        String resultHeader;
        String result;
        System.out.println("Enter From Date for searching: ");
        fromDate = enterDate();
        System.out.println("Enter End Date for searching: ");
        endDate = enterDate();
        List<Customers> customersListByDateRange = new ArrayList<>();
        while (true) {
            try {
                Iterator<Customers> customersIterator = customersList.iterator();
                while (customersIterator.hasNext()) {
                    Customers customers = customersIterator.next();
                    boolean checkInvoiceDateInRange = checkDateBetweenRange(customers.getInvoiceDate(), fromDate, endDate);
                    if (checkInvoiceDateInRange) {
                        customersListByDateRange.add(customers);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                resultHeader = "\nLIST OF CUSTOMER INVOICES FROM " + fromDate + " - " + endDate;
                frameHeader(resultHeader);
                for (Customers customers : customersListByDateRange) {
                    result = customers.toString();
                    frameResult(result);
                }
                break;
            }
        }
    }

    public boolean checkDateBetweenRange(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate) {
        return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <=0;
    }

    /**
     * Retrieve customer list of the entered year and month
     * @return List<Customers>
     */
    public List<Customers> getCustomersByYearAndMonth(){
        System.out.println("Enter year and month to retrieve customer invoices: ");
        List<Customers> customerListByYearAndMonth = new ArrayList<>();
        System.out.println("Invoice Year: ");
        int invoiceYear = enterYear();
        System.out.println("Invoice month: ");
        int invoiceMonth = enterMonth();
        while (true) {
            try {
                Iterator<Customers> customersIterator = customersList.iterator();
                while (customersIterator.hasNext()) {
                    Customers customers = customersIterator.next();
                    if (customers.getInvoiceDate().getYear() == invoiceYear && customers.getInvoiceDate().getMonth().getValue() == invoiceMonth) {
                        customerListByYearAndMonth.add(customers);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                String resultHeader = "\nLIST OF CUSTOMER INVOICES IN " + invoiceYear + " - " + invoiceMonth;
                frameHeader(resultHeader);
                return customerListByYearAndMonth;
            }
        }
    }

    /**
     * String for customizing return result for readability
     * @param string
     */
    public void frameResult(String string){
        StringBuilder builder = new StringBuilder();
        builder.append("\n********************************************");
        builder.append(string);
        System.out.println(builder.toString());
        System.out.println("********************************************");
    }

    /**
     * String for customizing header of function for readability
     * @param string
     */
    public void frameHeader(String string){
        StringBuilder builder = new StringBuilder();
        builder.append("\n********************************************");
        builder.append(string);
        System.out.println(builder.toString());
    }

    /**
     * Menu of the main program
     */
    public void showMenu(){
        System.out.println("\n---------------------E-INVOICE MANAGEMENT SYSTEM---------------------\n");
        System.out.println("\t\t\t|\t0. Exit.\n");
        System.out.println("\t\t\t|\t1. Add Vietnamese customer invoice.\n");
        System.out.println("\t\t\t|\t2. Add Foreign customer invoice.\n");
        System.out.println("\t\t\t|\t3. Show customer invoice list.\n");
        System.out.println("\t\t\t|\t4. Total used quantity (KW).\n");
        System.out.println("\t\t\t|\t5. Average payment of foreign customers.\n");
        System.out.println("\t\t\t|\t6. Show customer invoices of month.\n");
        System.out.println("\t\t\t|\t7. Show list of over-quota used quantity.\n");
        System.out.println("\t\t\t|\t8. Show customer invoices between date.\n");
        System.out.println("-----------------------------------------------------------------------\n");
        System.out.println("\t\t\t|\tPLEASE SELECT YOUR OPTION: ");
    }
}