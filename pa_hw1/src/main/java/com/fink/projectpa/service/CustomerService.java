/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.CustomerDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Customer;
import com.fink.projectpa.exception.ProjectpaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marko
 */
public class CustomerService {
    
    private static final CustomerService instance = new CustomerService();

    private CustomerService() {
    }
    
    
      public static CustomerService getInstance() {
        return instance;
    }
      
      
      
    public void createNewCustomer(Customer customer) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

           
            con.setAutoCommit(false);

            CustomerDao.getInstance().create(con, customer);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to add new customer " + customer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
      
      
      
       public Customer findCustomer(int customerId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return CustomerDao.getInstance().find(con, customerId);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find customer with username " + customerId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
       
        public List<Customer> findAllCutomers() throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            
            return CustomerDao.getInstance().findAll(con);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find all customers", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
        
    public void deleteCustomerById(int customerId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Customer customer = CustomerDao.getInstance().find(con, customerId);
            if (customer != null) {
                CustomerDao.getInstance().delete(con, customer);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to delete customer with id " + customerId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateCustomer(Customer customer) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            CustomerDao.getInstance().update(con, customer);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to update customer " + customer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

}
