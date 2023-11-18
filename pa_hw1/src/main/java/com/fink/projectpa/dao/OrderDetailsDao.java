/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;


import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.OrderDetail;
import com.fink.projectpa.data.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author marko
 */
public class OrderDetailsDao {
     private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetailsDao.class);
     
     
      private static final OrderDetailsDao instance = new OrderDetailsDao();

    private OrderDetailsDao() {
    }

    public static OrderDetailsDao getInstance() {
        return instance;
    }
      
     public void create(Connection con, OrderDetail orderDetails) throws SQLException {
         
          PreparedStatement ps = null;
        ResultSet rs = null;
         
        try {
           
             LOGGER.info("Creating orderDetail: {}", orderDetails);
           
          // Check for null values
            if (orderDetails.getOrder() == null || orderDetails.getProduct() == null) {
                LOGGER.error("Invalid order: order or product is null.");
                throw new IllegalArgumentException("order or product is null.");
            }
            
            ps = con.prepareStatement("INSERT INTO `OrderDetail`(order_id, product_id, quantity) VALUES(?,?,?)");
            
            ps.setInt(1, orderDetails.getOrder().getOrder_id());
            ps.setInt(2, orderDetails.getProduct().getProductId());
            ps.setInt(3, orderDetails.getQuantity());
            ps.executeUpdate();
        
            
             LOGGER.info("OrderDetail created successfully.");
           
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }  
    
    
    }
    
    public void update(Connection con, OrderDetail orderDetails) throws SQLException {
      PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE `OrderDetail` SET  order_id=?, product_id=?, quantity=? WHERE order_detail_id=?");
         
            ps.setInt(1, orderDetails.getOrder().getOrder_id());
            ps.setInt(2, orderDetails.getProduct().getProductId());
            ps.setInt(3, orderDetails.getQuantity());
            ps.setInt(4, orderDetails.getOrderDetail_id());
            
            ps.executeUpdate();


        } finally {
            ResourcesManager.closeResources(null, ps);
        }
       
    }
    
    public void delete(Connection con, OrderDetail orderDetail) throws SQLException {
         PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM `OrderDetail` WHERE order_detail_id=?");
            ps.setInt(1, orderDetail.getOrderDetail_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
        
    
    }
    
    public OrderDetail find(Connection con, int orderDetailsId) throws SQLException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDetail orderDetail = null;
       
        try {
            ps = con.prepareStatement("SELECT * FROM `OrderDetail` where order_detail_id=?");
            ps.setInt(1, orderDetailsId);
            rs = ps.executeQuery();
            if (rs.next()) {
                
               Order order = OrderDao.getInstance().find(con ,rs.getInt("order_id"));
               Product product = ProductDao.getInstance().findId(con ,rs.getInt("product_id"));
              
 
               orderDetail = new OrderDetail(order,product,rs.getInt("quantity"),rs.getInt("order_detail_id"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderDetail;
    
    }
    
    public List<OrderDetail> findAll(Connection con) throws SQLException {
    
         
       PreparedStatement ps = null;
       ResultSet rs = null;
      OrderDetail orderDetail = null;
       
       List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `OrderDetail`");
            rs = ps.executeQuery();
            while (rs.next()) {
                  
               Order order = OrderDao.getInstance().find(con ,rs.getInt("order_id"));
               Product product = ProductDao.getInstance().findId(con ,rs.getInt("product_id"));
          
               orderDetail = new OrderDetail(order,product,rs.getInt("quantity"),rs.getInt("order_detail_id"));
               orderDetailList.add(orderDetail);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderDetailList;
    
    }
    }
    

