/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;


import com.fink.projectpa.dao.OrderDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.exception.ProjectpaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marko
 */
public class OrderService {
    
    
    
    private static final OrderService instance = new OrderService();

    private OrderService() {
    }

    public static OrderService getInstance() {
        return instance;
    }
    
    public void createOrder(Customer customer, Employee employee, Shipper shipper) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);
            
            Order order = new Order(customer, employee, shipper);

            OrderDao.getInstance().create(con, order);

            con.commit();
            
             System.out.println("Customer " + customer.getCustomerName() + " Order employee " + employee.getUsername() + " Shipper" + shipper.getShipperName());
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to add new order ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
 
    }
    
    
    
     public void updateOrder(Customer customer, Employee employee, Shipper shipper, int orderId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            
            Order order = new Order(customer, employee, shipper, orderId);

            OrderDao.getInstance().update(con, order);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to update order ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
     
      public void deleteOrderById(int orderId) throws ProjectpaException {
         
          Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Order order = OrderDao.getInstance().find(con, orderId);
            if (order != null) {
                OrderDao.getInstance().delete(con, order);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to delete order with id " + orderId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
     }
      
        public Order findOrder(int orderId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OrderDao.getInstance().find(con, orderId);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find order with id " + orderId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
      
         public List<Order> findAllOrders() throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            
            return OrderDao.getInstance().findAll(con);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find orders ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
