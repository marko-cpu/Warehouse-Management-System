/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

import java.io.Serializable;

/**
 *
 * @author marko
 */
public class Customer implements Serializable {
    
    private int customer_id;
    private String customerName;
    private String contactPerson;
    private String address;
    private String city;
    private String  postCode;
    private String country;

    public Customer() {
    }

    public Customer(int customer_id, String customerName, String contactPerson, String address, String city, String postCode, String country) {
        this.customer_id = customer_id;
        this.customerName = customerName;
        this.contactPerson = contactPerson;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }


    public Customer(String customerName, String contactPerson, String address, String city, String postCode, String country) {
        this.customerName = customerName;
        this.contactPerson = contactPerson;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }


    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    
    
    

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{");
        sb.append("customerName=").append(customerName);
        sb.append(", contactPerson=").append(contactPerson);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", postCode=").append(postCode);
        sb.append(", country=").append(country);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
   
    
}
