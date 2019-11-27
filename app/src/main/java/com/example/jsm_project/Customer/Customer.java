package com.example.jsm_project.Customer;

public class Customer {
    public String customerName, customerAddress, customerContact, customerCity, customerCountry;
    public int id;

    public Customer(int id, String customerName, String customerAddress, String customerContact, String customerCity, String customerCountry) {
        this.id = id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContact = customerContact;
        this.customerCity = customerCity;
        this.customerCountry = customerCountry;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }
}
