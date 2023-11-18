/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;



import com.fink.projectpa.data.Supplier;
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
public class SupplierDao {
    
      private static final SupplierDao instance = new SupplierDao();

    private SupplierDao() {
    }

    public static SupplierDao getInstance() {
        return instance;
    }
    
    
    
      
     public void create(Connection con, Supplier supplier) throws SQLException {
         
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
             
            ps = con.prepareStatement("INSERT INTO Supplier(supplier_name, contact_person, address, city, post_code, country, phone) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setInt(5, supplier.getPostCode());
            ps.setString(6, supplier.getCountry());
            ps.setString(7, supplier.getPhone());

            ps.executeUpdate();

        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    
    
    
    }
    
    public void update(Connection con, Supplier supplier) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE Supplier SET supplier_name=?, contact_person=?, address=?, city=?, post_code=?, country=?, phone=? WHERE supplier_id=?");
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setInt(5, supplier.getPostCode());
            ps.setString(6, supplier.getCountry());
            ps.setString(7, supplier.getPhone());
            ps.setInt(8, supplier.getSupplierId());
            ps.executeUpdate();

           

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    
    }
    
    public void delete(Connection con, Supplier supplier) throws SQLException {
    
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("DELETE FROM Supplier WHERE supplier_id=?");
            ps.setInt(1, supplier.getSupplierId());
            ps.executeUpdate();


        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public Supplier find(Connection con, int supplierId) throws SQLException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        Supplier supplier = null;
        try {
            ps = con.prepareStatement("SELECT * FROM Supplier where supplier_id=?");
            ps.setInt(1, supplierId);
            rs = ps.executeQuery();
            if (rs.next()) {
 
                supplier = new Supplier(rs.getInt("supplier_id"),rs.getString("supplier_name"), rs.getString("contact_person"), rs.getString("address"),
                        rs.getString("city"),rs.getInt("post_code"),rs.getString("country"),rs.getString("phone"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplier;
    }
    
    
    
      public Supplier findName(Connection con, String supplierName) throws SQLException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        Supplier supplier = null;
        try {
            ps = con.prepareStatement("SELECT * FROM Supplier where supplier_name=?");
            ps.setString(1, supplierName);
            rs = ps.executeQuery();
            if (rs.next()) {
 
                supplier = new Supplier(rs.getInt("supplier_id"),rs.getString("supplier_name"), rs.getString("contact_person"), rs.getString("address"),
                        rs.getString("city"),rs.getInt("post_code"),rs.getString("country"),rs.getString("phone"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplier;
    }
      
      
      
    
    public List<Supplier> findAll(Connection con) throws SQLException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Supplier> supplierList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM Supplier");
            rs = ps.executeQuery();
            while (rs.next()) {
               Supplier supplier;
                supplier = new Supplier(rs.getInt("supplier_id"),rs.getString("supplier_name"), rs.getString("contact_person"), rs.getString("address"),
                        rs.getString("city"),rs.getInt("post_code"),rs.getString("country"),rs.getString("phone"));
               supplierList.add(supplier);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplierList;
    }
    
    }
    

