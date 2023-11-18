/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.dao.ShipperDao;
import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.exception.ProjectpaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marko
 */
public class ShipperService {
    
    private static final ShipperService instance = new ShipperService();

    private ShipperService() {
    }
    
    
      public static ShipperService getInstance() {
        return instance;
    }
      
      
      public void createNewShipper(Shipper shipper) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

           
            con.setAutoCommit(false);

            ShipperDao.getInstance().create(con, shipper);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to add new shipper " + shipper, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
      
       
       public Shipper findShipper(int shipperId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ShipperDao.getInstance().find(con, shipperId);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find shiiper with id " + shipperId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
       
        public List<Shipper> findAllShippers() throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            
            return ShipperDao.getInstance().findAll(con);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find all shippers", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
        
     public void deleteShipper(int shipperId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Shipper shipper = ShipperDao.getInstance().find(con, shipperId);
            if (shipper != null) {
                ShipperDao.getInstance().delete(con, shipper);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to delete shipper with shipper id " + shipperId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateShipper(Shipper shipper) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            ShipperDao.getInstance().update(con, shipper);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to update shipper " + shipper, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
      
}
