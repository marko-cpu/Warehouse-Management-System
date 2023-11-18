/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;


import com.fink.projectpa.data.Shipper;
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
public class ShipperDao {
    
     private static final ShipperDao instance = new ShipperDao();

    private ShipperDao() {
    }

    public static ShipperDao getInstance() {
        return instance;
    }
    
    
      
     public void create(Connection con, Shipper shipper) throws SQLException {
         
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            
             
            ps = con.prepareStatement("INSERT INTO Shipper(shipper_name, phone) VALUES(?,?)");
            ps.setString(1, shipper.getShipperName());
            ps.setString(2, shipper.getPhone());

            ps.executeUpdate();

        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    
    
    
    }
    
    public void update(Connection con, Shipper shipper) throws SQLException {
         PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE Shipper SET shipper_name=?, phone=? WHERE shipper_id=?");
            ps.setString(1, shipper.getShipperName());
            ps.setString(2, shipper.getPhone());
            ps.setDouble(3, shipper.getShipperId());
      
            ps.executeUpdate();

          
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    
    }
    
    public void delete(Connection con, Shipper shipper) throws SQLException {
          PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("DELETE FROM `Shipper` WHERE shipper_id=?");
            ps.setInt(1, shipper.getShipperId());
            ps.executeUpdate();


        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    
    }
    
    public Shipper find(Connection con, int shipperId) throws SQLException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        Shipper shipper = null;
        try {
            ps = con.prepareStatement("SELECT * FROM Shipper where shipper_id=?");
            ps.setInt(1, shipperId);
            rs = ps.executeQuery();
            if (rs.next()) {
 
                shipper = new Shipper(rs.getInt("shipper_id"),rs.getString("shipper_name"), rs.getString("phone"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return shipper;
    
    }
    
    public List<Shipper> findAll(Connection con) throws SQLException {
    
         PreparedStatement ps = null;
        ResultSet rs = null;
        List<Shipper> shipperList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM Shipper");
            rs = ps.executeQuery();
            while (rs.next()) {
               Shipper shipper;
                shipper = new Shipper(rs.getInt("shipper_id"),rs.getString("shipper_name"), rs.getString("phone"));
               shipperList.add(shipper);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return shipperList;
    }
    
    }
    

