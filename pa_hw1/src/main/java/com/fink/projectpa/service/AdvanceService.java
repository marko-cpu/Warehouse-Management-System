/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.ResourcesManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marko
 */
public class AdvanceService {
    
    
        
    private static final AdvanceService instance = new AdvanceService();

    private AdvanceService() {
    }
    
    
      public static AdvanceService getInstance() {
        return instance;
    }
      
    
    /* 1. Izlistati svakog Customer-a i sve njihove Order-e jednog ispod drugog u formatu
            CustomerName OrderId
            Customer-e sortirati po abecednom redu. */
      
     /*
      SELECT c.customer_name AS CustomerName, o.order_id AS OrderId
        FROM Customer c
        JOIN `Order` o ON c.customer_id = o.customer_id
        ORDER BY c.customer_name ASC, o.order_id ASC;
       */ 
    
     public List<Map<String, Object>> listCustomersAndOrders() {
         
        List<Map<String, Object>> result = new ArrayList<>();
        
        try (Connection con = ResourcesManager.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT c.customer_name AS CustomerName, o.order_id AS OrderId " +
                             "FROM Customer c JOIN `Order` o ON c.customer_id = o.customer_id " +
                             "ORDER BY c.customer_name ASC, o.order_id ASC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("CustomerName", rs.getString("CustomerName"));
                row.put("OrderId", rs.getInt("OrderId"));
                result.add(row);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
     
     /* 2. Izlistati sve Product-e za čije dobavljanje je nadležan Supplier sa datim SupplierId
     
     SELECT p.product_name AS ProductName, s.supplier_name AS SupplierName
        FROM Product p
        JOIN Supplier s ON p.supplier_id = s.supplier_id
        WHERE s.supplier_id = ?;
     
     */
     
   public List<Map<String, Object>> listProductsForSupplier(int supplierId) {
    List<Map<String, Object>> result = new ArrayList<>();

    try (Connection con = ResourcesManager.getConnection();
         PreparedStatement ps = con.prepareStatement(
                 "SELECT p.product_name AS ProductName, s.supplier_name AS SupplierName " +
                         "FROM Product p JOIN Supplier s ON p.supplier_id = s.supplier_id " +
                         "WHERE s.supplier_id = ?");
    ) {
        ps.setInt(1, supplierId);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("ProductName", rs.getString("ProductName"));
                row.put("SupplierName", rs.getString("SupplierName"));
                result.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
      
      /* 3. Izlistati sve Product-e koje je dostavio Shipper sa datim ShipperId
      
      SELECT p.product_id, p.product_name
        FROM Warehouse.Product p
        JOIN Warehouse.Supplier s ON p.supplier_id = s.supplier_id
        JOIN Warehouse.OrderDetail od ON p.product_id = od.product_id
        JOIN Warehouse.Order o ON od.order_id = o.order_id
        WHERE o.shipper_id = ?;

      */
      
      
    public List<Map<String, Object>> listProductsByShipper(int shipperId) {
        List<Map<String, Object>> result = new ArrayList<>();

    try (Connection con = ResourcesManager.getConnection();
         PreparedStatement ps = con.prepareStatement(
                 "SELECT p.product_id, p.product_name " +
                         "FROM Warehouse.Product p " +
                         "JOIN Warehouse.Supplier s ON p.supplier_id = s.supplier_id " +
                         "JOIN Warehouse.OrderDetail od ON p.product_id = od.product_id " +
                         "JOIN Warehouse.Order o ON od.order_id = o.order_id " +
                         "WHERE o.shipper_id = ?");
    ) {
        ps.setInt(1, shipperId);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("ProductId", rs.getInt("product_id"));
                row.put("ProductName", rs.getString("product_name"));
                result.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
      
      
        
        /*4. Izračunati ukupnu cenu svih narudžbina
    
       SELECT SUM(od.quantity * p.price_per_unit) AS total_order_value
        FROM Warehouse.OrderDetail od
        JOIN Warehouse.Product p ON od.product_id = p.product_id;
    
       */
      public double calculateTotalOrderValue() {
        double totalOrderValue = 0;

        try (Connection con = ResourcesManager.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT SUM(od.quantity * p.price_per_unit) AS total_order_value " +
                             "FROM Warehouse.OrderDetail od " +
                             "JOIN Warehouse.Product p ON od.product_id = p.product_id");
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                totalOrderValue = rs.getDouble("total_order_value");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalOrderValue;
    }
        
        
        
        
       /* 5. Izračunati cenu narudžbina (Order-a) koje je naručio Customer sa datim CustomerId
        
      SELECT o.order_id, SUM(od.quantity * p.price_per_unit) AS total_order_price
        FROM `Order` o
        JOIN OrderDetail od ON o.order_id = od.order_id
        JOIN Product p ON od.product_id = p.product_id
        WHERE o.customer_id = ?
        GROUP BY o.order_id;
    
      */
    public List<Map<String, Object>> calculateOrderTotalForCustomer(int customerId) {
        List<Map<String, Object>> result = new ArrayList<>();

    try (Connection con = ResourcesManager.getConnection();
         PreparedStatement ps = con.prepareStatement(
                 "SELECT o.order_id, SUM(od.quantity * p.price_per_unit) AS total_order_price " +
                         "FROM `Order` o " +
                         "JOIN OrderDetail od ON o.order_id = od.order_id " +
                         "JOIN Product p ON od.product_id = p.product_id " +
                         "WHERE o.customer_id = ? " +
                         "GROUP BY o.order_id");
    ) {
        ps.setInt(1, customerId);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("OrderId", rs.getInt("order_id"));
                row.put("TotalOrderPrice", rs.getDouble("total_order_price"));
                result.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
        
        
        /* 6. Izračunati cenu narudžbina (Order-a) koje je dostavio Shipper sa datim ShipperId
         
      SELECT o.order_id, SUM(od.quantity * p.price_per_unit) AS total_order_price
        FROM `Order` o
        JOIN OrderDetail od ON o.order_id = od.order_id
        JOIN Product p ON od.product_id = p.product_id
        WHERE o.shipper_id = ?
        GROUP BY o.order_id;

      
      */
    public List<Map<String, Object>> calculateOrderTotalForShipper(int shipperId) {
        List<Map<String, Object>> result = new ArrayList<>();

    try (Connection con = ResourcesManager.getConnection();
         PreparedStatement ps = con.prepareStatement(
                 "SELECT o.order_id, SUM(od.quantity * p.price_per_unit) AS total_order_price " +
                         "FROM `Order` o " +
                         "JOIN OrderDetail od ON o.order_id = od.order_id " +
                         "JOIN Product p ON od.product_id = p.product_id " +
                         "WHERE o.shipper_id = ? " +
                         "GROUP BY o.order_id");
    ) {
        ps.setInt(1, shipperId);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("OrderId", rs.getInt("order_id"));
                row.put("TotalOrderPrice", rs.getDouble("total_order_price"));
                result.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
         
         

        
        /* 7. Izračunati cenu narudžbina koje je dobavio Supplier sa datim SupplierId
        
      SELECT o.order_id, SUM(od.quantity * p.price_per_unit) AS total_order_price
        FROM `Order` o
        JOIN OrderDetail od ON o.order_id = od.order_id
        JOIN Product p ON od.product_id = p.product_id
        JOIN Supplier s ON p.supplier_id = s.supplier_id
        WHERE s.supplier_id = ?
        GROUP BY o.order_id;

      */
    public List<Map<String, Object>> calculateOrderTotalForSupplier(int supplierId) {
            List<Map<String, Object>> result = new ArrayList<>();

    try (Connection con = ResourcesManager.getConnection();
         PreparedStatement ps = con.prepareStatement(
                 "SELECT o.order_id, SUM(od.quantity * p.price_per_unit) AS total_order_price " +
                         "FROM `Order` o " +
                         "JOIN OrderDetail od ON o.order_id = od.order_id " +
                         "JOIN Product p ON od.product_id = p.product_id " +
                         "JOIN Supplier s ON p.supplier_id = s.supplier_id " +
                         "WHERE s.supplier_id = ? " +
                         "GROUP BY o.order_id");
    ) {
        ps.setInt(1, supplierId);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("OrderId", rs.getInt("order_id"));
                row.put("TotalOrderPrice", rs.getDouble("total_order_price"));
                result.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
        
        
        /* 8. Pronaći onog zaposlenog kod koga je naručena najveća vrednost robe
          
      SELECT e.employee_id, e.last_name, e.first_name, MAX(order_values.total_order_value) AS max_order_value
        FROM Warehouse.Employee e
        JOIN Warehouse.`Order` o ON e.employee_id = o.employee_id
        JOIN (
        SELECT od.order_id, SUM(od.quantity * p.price_per_unit) AS total_order_value
        FROM Warehouse.OrderDetail od
        JOIN Warehouse.Product p ON od.product_id = p.product_id
        GROUP BY od.order_id) 
        order_values ON o.order_id = order_values.order_id
        GROUP BY e.employee_id, e.last_name, e.first_name
        ORDER BY max_order_value DESC
        LIMIT 1;
     
      */
   public List<Map<String, Object>> findEmployeeWithMaxOrderValue() {
    List<Map<String, Object>> result = new ArrayList<>();

    try (Connection con = ResourcesManager.getConnection();
         PreparedStatement ps = con.prepareStatement(
                 "SELECT e.employee_id, e.last_name, e.first_name, MAX(total_order_value) AS max_order_value " +
                         "FROM Warehouse.Employee e " +
                         "JOIN Warehouse.`Order` o ON e.employee_id = o.employee_id " +
                         "JOIN (" +
                         "   SELECT od.order_id, SUM(od.quantity * p.price_per_unit) AS total_order_value " +
                         "   FROM Warehouse.OrderDetail od " +
                         "   JOIN Warehouse.Product p ON od.product_id = p.product_id " +
                         "   GROUP BY od.order_id" +
                         ") order_values ON o.order_id = order_values.order_id " +
                         "GROUP BY e.employee_id, e.last_name, e.first_name " +
                         "ORDER BY max_order_value DESC " +
                         "LIMIT 1");
    ) {
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("EmployeeId", rs.getInt("employee_id"));
                row.put("LastName", rs.getString("last_name"));
                row.put("FirstName", rs.getString("first_name"));
                row.put("MaxOrderValue", rs.getDouble("max_order_value"));
                result.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
        
        
        /* 9. Pronaći 2 Product-a koji su najviše puta naručivani
          
      SELECT p.product_id, p.product_name, COUNT(od.order_id) AS order_count
        FROM Warehouse.Product p
        JOIN Warehouse.OrderDetail od ON p.product_id = od.product_id
        GROUP BY p.product_id, p.product_name
        ORDER BY order_count DESC
        LIMIT 2;
     
      */
    public List<Map<String, Object>> findTopTwoProducts() {
        List<Map<String, Object>> result = new ArrayList<>();

        try (Connection con = ResourcesManager.getConnection();
         PreparedStatement ps = con.prepareStatement(
                 "SELECT p.product_id, p.product_name, COUNT(od.order_id) AS order_count " +
                         "FROM Warehouse.Product p " +
                         "JOIN Warehouse.OrderDetail od ON p.product_id = od.product_id " +
                         "GROUP BY p.product_id, p.product_name " +
                         "ORDER BY order_count DESC " +
                         "LIMIT 2");
    ) {
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("ProductId", rs.getInt("product_id"));
                row.put("ProductName", rs.getString("product_name"));
                row.put("OrderCount", rs.getInt("order_count"));
                result.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
        
        
        /* 10. Pronaći 4 najbolja Customer-a sa stanovišta cene naručene robe
          
        SELECT c.customer_id, c.customer_name, SUM(od.quantity * p.price_per_unit) AS total_order_value
        FROM Warehouse.Customer c
        JOIN Warehouse.Order o ON c.customer_id = o.customer_id
        JOIN Warehouse.OrderDetail od ON o.order_id = od.order_id
        JOIN Warehouse.Product p ON od.product_id = p.product_id
        GROUP BY c.customer_id, c.customer_name
        ORDER BY total_order_value DESC
        LIMIT 4;

            */      
    public List<Map<String, Object>> findTopFourCustomers() {
     List<Map<String, Object>> result = new ArrayList<>();

    try (Connection con = ResourcesManager.getConnection();
         PreparedStatement ps = con.prepareStatement(
                 "SELECT c.customer_id, c.customer_name, SUM(od.quantity * p.price_per_unit) AS total_order_value " +
                         "FROM Warehouse.Customer c " +
                         "JOIN Warehouse.Order o ON c.customer_id = o.customer_id " +
                         "JOIN Warehouse.OrderDetail od ON o.order_id = od.order_id " +
                         "JOIN Warehouse.Product p ON od.product_id = p.product_id " +
                         "GROUP BY c.customer_id, c.customer_name " +
                         "ORDER BY total_order_value DESC " +
                         "LIMIT 4");
    ) {
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("CustomerId", rs.getInt("customer_id"));
                row.put("CustomerName", rs.getString("customer_name"));
                row.put("TotalOrderValue", rs.getDouble("total_order_value"));
                result.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
        
        
        /* 11. Pronaći Supplier-a koji je dostavio najviše robe koja je prodata 
                 //(kao kriterijum koristiti ukupnu cenu svih prodatih proizvoda)
           
            SELECT s.supplier_id, s.supplier_name, SUM(od.quantity * p.price_per_unit) AS total_sold_value
            FROM Warehouse.Supplier s
            JOIN Warehouse.Product p ON s.supplier_id = p.supplier_id
            JOIN Warehouse.OrderDetail od ON p.product_id = od.product_id
            JOIN Warehouse.Order o ON od.order_id = o.order_id
            GROUP BY s.supplier_id, s.supplier_name
            ORDER BY total_sold_value DESC
            LIMIT 1;

         */
    public List<Map<String, Object>> findTopSupplier() {
        List<Map<String, Object>> result = new ArrayList<>();

    try (Connection con = ResourcesManager.getConnection();
         PreparedStatement ps = con.prepareStatement(
                 "SELECT s.supplier_id, s.supplier_name, SUM(od.quantity * p.price_per_unit) AS total_sold_value " +
                         "FROM Warehouse.Supplier s " +
                         "JOIN Warehouse.Product p ON s.supplier_id = p.supplier_id " +
                         "JOIN Warehouse.OrderDetail od ON p.product_id = od.product_id " +
                         "JOIN Warehouse.Order o ON od.order_id = o.order_id " +
                         "GROUP BY s.supplier_id, s.supplier_name " +
                         "ORDER BY total_sold_value DESC " +
                         "LIMIT 1");
    ) {
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("SupplierId", rs.getInt("supplier_id"));
                row.put("SupplierName", rs.getString("supplier_name"));
                row.put("TotalSoldValue", rs.getDouble("total_sold_value"));
                result.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
        
        

    
}

