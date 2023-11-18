/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;


import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.Supplier;
import com.fink.projectpa.exception.ProjectpaException;
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
public class ProductDao {
    
    
    private static final ProductDao instance = new ProductDao();
    

    private ProductDao() {
    }

    public static ProductDao getInstance() {
        return instance;
    }

     public void create(Connection con, Product product) throws SQLException, ProjectpaException{
         
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = con.prepareStatement("INSERT INTO Product(product_name, supplier_id, product_category, price_per_unit) VALUES(?,?,?,?)");
            ps.setString(1, product.getProductName());
            /*Supplier supplier = SupplierDao.getInstance().findName(con, product.getSupplier().getSupplierName());
            if (supplier == null) {
                throw new projectpa("Supplier " + product.getSupplier() + " doesn't exist in database.");
            }*/
            ps.setInt(2, 1);
            ps.setString(3, product.getProductCategory()); 
            ps.setInt(4, product.getPricePerUnit());
            
            ps.executeUpdate();
           
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        
    }
    
    public void update(Connection con, Product product) throws SQLException {
         PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE Product SET product_name=?, product_category=?, price_per_unit=? WHERE product_id=?");
            ps.setString(1, product.getProductName());
           // ps.setInt(2, product.getSupplier().getSupplierId());
            ps.setString(2, product.getProductCategory());
            ps.setInt(3, product.getPricePerUnit());
            ps.setInt(4, product.getProductId());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    
    }
    
    public void delete(Connection con, Product product) throws SQLException {
        PreparedStatement ps = null;
        try {

            //delete purchases of the product
           // PurchaseDao.getInstance().delete(product, con);

            //delete product
            ps = con.prepareStatement("DELETE FROM Product WHERE product_id=?");
            ps.setInt(1, product.getProductId());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    
    }
    
    public Product findName(Connection con, String productName) throws SQLException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = con.prepareStatement("SELECT * FROM Product WHERE product_name=?");
            ps.setString(1, productName);
            rs = ps.executeQuery();
            if (rs.next()) {
                Supplier supplier = SupplierDao.getInstance().find(con, rs.getInt("supplier_id"));
                product = new Product(rs.getInt("product_id"), rs.getString("product_name"), supplier, rs.getString("product_category"), rs.getInt("price_per_unit"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    
    }
    
     public Product findId(Connection con, int productId) throws SQLException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = con.prepareStatement("SELECT * FROM Product WHERE product_id=?");
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Supplier supplier = SupplierDao.getInstance().find(con, rs.getInt("supplier_id"));
                product = new Product(rs.getInt("product_id"), rs.getString("product_name"), supplier, rs.getString("product_category"), rs.getInt("price_per_unit"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    
    }
    
    public List<Product> findAll(Connection con) throws SQLException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productsList = new ArrayList<>();
        try {
            //ps = con.prepareStatement("SELECT * FROM Product ");
            ps = con.prepareStatement("SELECT * FROM Product");
            rs = ps.executeQuery(); 
            while (rs.next()) {
                Supplier supplier = SupplierDao.getInstance().find(con, rs.getInt("supplier_id"));
                Product product = new Product(rs.getInt("product_id"),rs.getString("product_name"),supplier, rs.getString("product_category"), rs.getInt("price_per_unit"));
                productsList.add(product);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return productsList;
    
    }
    
}
