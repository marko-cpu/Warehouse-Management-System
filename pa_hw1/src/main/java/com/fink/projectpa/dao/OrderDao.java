/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;


import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.exception.ProjectpaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author marko
 */
public class OrderDao {
      private static final Logger LOGGER = LoggerFactory.getLogger(OrderDao.class);
    
      private static final OrderDao instance = new OrderDao();

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return instance;
    }
    

     public void create(Connection con, Order order) throws SQLException, ProjectpaException {
       
        PreparedStatement ps = null;
        ResultSet rs = null;
         
        try {
           
             LOGGER.info("Creating order: {}", order);
           
          // Check for null values
            if (order.getCustomer() == null || order.getEmployee() == null || order.getShipper() == null) {
                LOGGER.error("Invalid order: customer, employee, or shipper is null.");
                throw new IllegalArgumentException("customer, employee, or shipper is null.");
            }
            
            ps = con.prepareStatement("INSERT INTO `Order`(customer_id, employee_id, shipper_id) VALUES(?,?,?)");
            
            ps.setInt(1, order.getCustomer().getCustomer_id());
            ps.setInt(2, order.getEmployee().getEmployee_id());
            ps.setInt(3, order.getShipper().getShipperId());
            ps.executeUpdate();
        
            
             LOGGER.info("Order created successfully.");
           
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }  
         
    }
    
    public void update(Connection con, Order order) throws SQLException {
          PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE `Order` SET  customer_id=?, employee_id=?, shipper_id=? WHERE order_id=?");
         
            ps.setInt(1, order.getCustomer().getCustomer_id());
            ps.setInt(2, order.getEmployee().getEmployee_id());
            ps.setInt(3, order.getShipper().getShipperId());
            ps.setInt(4, order.getOrder_id());
            
            ps.executeUpdate();


        } finally {
            ResourcesManager.closeResources(null, ps);
        }
       
    
    }
    
    public void delete(Connection con, Order order) throws SQLException {
    
         PreparedStatement ps = null;
        try {     
         
            ps = con.prepareStatement("DELETE FROM `Order` WHERE order_id=?");
            ps.setInt(1, order.getOrder_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    
    public Order find(Connection con, int orderId) throws SQLException {
    
      PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
       
        try {
            ps = con.prepareStatement("SELECT * FROM `Order` where order_id=?");
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            if (rs.next()) {
                
               Customer customer = CustomerDao.getInstance().find(con ,rs.getInt("customer_id"));
               Employee employee = EmployeeDao.getInstance().find(con ,rs.getInt("employee_id"));
               Shipper shipper = ShipperDao.getInstance().find(con ,rs.getInt("shipper_id"));
 
               order = new Order(orderId, rs.getTimestamp("order_date"), customer, employee, shipper);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return order;
    }
    
    public List<Order> findAll(Connection con) throws SQLException {
    
      
       PreparedStatement ps = null;
       ResultSet rs = null;
       Order order = null;
       List<Order> orderList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `Order`");
            rs = ps.executeQuery();
            while (rs.next()) {
                 Customer customer = CustomerDao.getInstance().find(con ,rs.getInt("customer_id"));
                 Employee employee = EmployeeDao.getInstance().find(con ,rs.getInt("employee_id"));
                 Shipper shipper = ShipperDao.getInstance().find(con ,rs.getInt("shipper_id"));
          
                order = new Order(rs.getInt("order_id"), rs.getTimestamp("order_date"), customer, employee, shipper);
               orderList.add(order);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderList;
    
    }
    
}
