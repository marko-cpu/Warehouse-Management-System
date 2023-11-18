/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marko
 */
public class CustomerDao {
    
     private static final CustomerDao instance = new CustomerDao();

    private CustomerDao() {
    }

    public static CustomerDao getInstance() {
        return instance;
    }
    
    
    
    public void create(Connection con, Customer customer) throws SQLException {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            
             
            ps = con.prepareStatement("INSERT INTO Customer(customer_name, contact_person, address, city, post_code, country) VALUES(?,?,?,?,?,?)");
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getContactPerson());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());
            ps.executeUpdate();

        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    
    
    
    }
    
    public void update(Connection con, Customer customer) throws SQLException {
        
         PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE Customer SET  contact_person=?, address=?, city=?, post_code=?, country=? WHERE customer_name=?");
         
            ps.setString(1, customer.getContactPerson());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getCity());
            ps.setString(4, customer.getPostCode());
            ps.setString(5, customer.getCountry());
            ps.setString(6, customer.getCustomerName());

            
            ps.executeUpdate();


        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    
    }
    
    public void delete(Connection con, Customer customer) throws SQLException {
        
          PreparedStatement ps = null;
        try {
              
           //OrderDetailsDao.getInstance().delete(con, customer);
            //delete order
           //OrderDao.getInstance().delete(con, customer);

            //delete customer
            ps = con.prepareStatement("DELETE FROM `Customer` WHERE customer_id=?");
            ps.setInt(1, customer.getCustomer_id());
            ps.executeUpdate();

          

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    
    }
    
    public Customer find(Connection con, int customerId) throws SQLException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `Customer` where customer_id=?");
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            if (rs.next()) {
 
                customer = new Customer(rs.getInt("customer_id"),rs.getString("customer_name"), rs.getString("contact_person"), rs.getString("address"),
                        rs.getString("city"),rs.getString("post_code"),rs.getString("country"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customer;
    
    }
    
    public List<Customer> findAll(Connection con) throws SQLException {
    
       PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> productsList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM Customer");
            rs = ps.executeQuery();
            while (rs.next()) {
               Customer customer;
                customer = new Customer(rs.getString("customer_name"), rs.getString("contact_person"), rs.getString("address"),
                        rs.getString("city"),rs.getString("post_code"),rs.getString("country"));
               productsList.add(customer);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return productsList;
    }
}
