/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;


import com.fink.projectpa.dao.OrderDao;
import com.fink.projectpa.dao.OrderDetailsDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.OrderDetail;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.exception.ProjectpaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marko
 */
public class OrderDetailsService {
    
      
    
    private static final OrderDetailsService instance = new OrderDetailsService();

    private OrderDetailsService() {
    }

    public static OrderDetailsService getInstance() {
        return instance;
    }
    
    
    
     public void createOrderDetail(Order order, Product product, int quantity) throws ProjectpaException, SQLException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            con.setAutoCommit(false);
            
            OrderDetail orderDetail = new OrderDetail(order, product, quantity);

            OrderDetailsDao.getInstance().create(con, orderDetail);

            con.commit();
            
             System.out.println("Order " + order.getOrder_id() + " Product " + product.getProductName() + " Quantity " + orderDetail.getQuantity());
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to add new orderDetail ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
 
    }
     
      public void updateOrderDetail(Order order, Product product, int quantity, int orderDetailId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            
           OrderDetail orderDetail = new OrderDetail(order, product, quantity, orderDetailId);

           OrderDetailsDao.getInstance().update(con, orderDetail);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to update orderDetail ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
      
      
        public OrderDetail findOrderDetail(int orderDetailId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OrderDetailsDao.getInstance().find(con, orderDetailId);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find orderDetail with id " + orderDetailId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
        
          public List<OrderDetail> findAllOrdersDetail() throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            
            return OrderDetailsDao.getInstance().findAll(con);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find all ordersDetail ", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
          
          
       public void deleteOrderDetailById(int orderDetailId) throws ProjectpaException {
         
          Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OrderDetail orderDetail = OrderDetailsDao.getInstance().find(con, orderDetailId);
            if (orderDetail != null) {
                OrderDetailsDao.getInstance().delete(con, orderDetail);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to delete orderDetail with id " + orderDetailId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
     }
    
}
