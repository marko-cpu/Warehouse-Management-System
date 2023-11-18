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
    
     public void listCustomersAndOrders() {
        try (Connection con = ResourcesManager.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT c.customer_name AS CustomerName, o.order_id AS OrderId " +
                             "FROM Customer c JOIN `Order` o ON c.customer_id = o.customer_id " +
                             "ORDER BY c.customer_name ASC, o.order_id ASC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String customerName = rs.getString("CustomerName");
                int orderId = rs.getInt("OrderId");

                System.out.println(customerName + " " + orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     /* 2. Izlistati sve Product-e za čije dobavljanje je nadležan Supplier sa datim SupplierId
     
     SELECT p.product_name AS ProductName, s.supplier_name AS SupplierName
        FROM Product p
        JOIN Supplier s ON p.supplier_id = s.supplier_id
        WHERE s.supplier_id = ?;
     
     */
     
      public void listProductsForSupplier(int supplierId) {
        try (Connection con = ResourcesManager.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT p.product_name AS ProductName, s.supplier_name AS SupplierName " +
                             "FROM Product p JOIN Supplier s ON p.supplier_id = s.supplier_id " +
                             "WHERE s.supplier_id = ?");
        ) {
            ps.setInt(1, supplierId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String productName = rs.getString("ProductName");
                    String supplierName = rs.getString("SupplierName");

                    System.out.println(productName + " for Supplier: " + supplierName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
      /* 3. Izlistati sve Product-e koje je dostavio Shipper sa datim ShipperId
      
      SELECT p.product_id, p.product_name
        FROM Warehouse.Product p
        JOIN Warehouse.Supplier s ON p.supplier_id = s.supplier_id
        JOIN Warehouse.OrderDetail od ON p.product_id = od.product_id
        JOIN Warehouse.Order o ON od.order_id = o.order_id
        WHERE o.shipper_id = ?;

      */
      
      
       public void listProductsByShipper(int shipperId) {
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
                    int productId = rs.getInt("product_id");
                    String productName = rs.getString("product_name");

                    System.out.println("Product ID: " + productId + ", Product Name: " + productName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
      public void calculateOrderTotalForCustomer(int customerId) {
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
                    int orderId = rs.getInt("order_id");
                    double totalOrderPrice = rs.getDouble("total_order_price");

                    System.out.println("Order ID: " + orderId + ", Total Order Price: " + totalOrderPrice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
        
        /* 6. Izračunati cenu narudžbina (Order-a) koje je dostavio Shipper sa datim ShipperId
         
      SELECT o.order_id, SUM(od.quantity * p.price_per_unit) AS total_order_price
        FROM `Order` o
        JOIN OrderDetail od ON o.order_id = od.order_id
        JOIN Product p ON od.product_id = p.product_id
        WHERE o.shipper_id = ?
        GROUP BY o.order_id;

      
      */
      public void calculateOrderTotalForShipper(int shipperId) {
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
                    int orderId = rs.getInt("order_id");
                    double totalOrderPrice = rs.getDouble("total_order_price");

                    System.out.println("Order ID: " + orderId + ", Total Order Price: " + totalOrderPrice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
      public void calculateOrderTotalForSupplier(int supplierId) {
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
                    int orderId = rs.getInt("order_id");
                    double totalOrderPrice = rs.getDouble("total_order_price");

                    System.out.println("Order ID: " + orderId + ", Total Order Price: " + totalOrderPrice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
      public void findEmployeeWithMaxOrderValue() {
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
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                double maxOrderValue = rs.getDouble("max_order_value");

                System.out.println("Employee ID: " + employeeId +
                        ", Last Name: " + lastName +
                        ", First Name: " + firstName +
                        ", Max Order Value: " + maxOrderValue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
        
        /* 9. Pronaći 2 Product-a koji su najviše puta naručivani
          
      SELECT p.product_id, p.product_name, COUNT(od.order_id) AS order_count
        FROM Warehouse.Product p
        JOIN Warehouse.OrderDetail od ON p.product_id = od.product_id
        GROUP BY p.product_id, p.product_name
        ORDER BY order_count DESC
        LIMIT 2;
     
      */
      public void findTopTwoProducts() {
        try (Connection con = ResourcesManager.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT p.product_id, p.product_name, COUNT(od.order_id) AS order_count " +
                             "FROM Warehouse.Product p " +
                             "JOIN Warehouse.OrderDetail od ON p.product_id = od.product_id " +
                             "GROUP BY p.product_id, p.product_name " +
                             "ORDER BY order_count DESC " +
                             "LIMIT 2");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                int orderCount = rs.getInt("order_count");

                System.out.println("Product ID: " + productId +
                        ", Product Name: " + productName +
                        ", Order Count: " + orderCount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
      public void findTopFourCustomers() {
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
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String customerName = rs.getString("customer_name");
                double totalOrderValue = rs.getDouble("total_order_value");

                System.out.println("Customer ID: " + customerId +
                        ", Customer Name: " + customerName +
                        ", Total Order Value: " + totalOrderValue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        public void findTopSupplier() {
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
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int supplierId = rs.getInt("supplier_id");
                String supplierName = rs.getString("supplier_name");
                double totalSoldValue = rs.getDouble("total_sold_value");

                System.out.println("Supplier ID: " + supplierId +
                        ", Supplier Name: " + supplierName +
                        ", Total Sold Value: " + totalSoldValue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
        
        
 /*   public static void main(String[] args) {
        AdvanceService service = new AdvanceService();
        service.listCustomersAndOrders(); // 1
        service.listProductsForSupplier(1); // Zamijeni sa odgovarajućim SupplierId // 2
        service.listProductsByShipper(1); // Zamijeni sa odgovarajućim ShipperId // 3
        service.calculateTotalOrderValue(); // 4
        service.calculateOrderTotalForCustomer(1); // 5
        service.calculateOrderTotalForShipper(1); // 6
        service.calculateOrderTotalForSupplier(1); // 7
        service.findEmployeeWithMaxOrderValue(); // 8
        service.findTopTwoProducts(); // 9
        service.findTopFourCustomers(); // 10
        service.findTopSupplier(); // 11
        
    }*/
    
}
