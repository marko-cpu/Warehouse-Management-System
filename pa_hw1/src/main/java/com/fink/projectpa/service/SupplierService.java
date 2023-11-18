/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;


import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.dao.SupplierDao;
import com.fink.projectpa.data.Supplier;
import com.fink.projectpa.exception.ProjectpaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marko
 */
public class SupplierService {
    
      private static final SupplierService instance = new SupplierService();

    private SupplierService() {
    }
    
    
      public static SupplierService getInstance() {
        return instance;
    }
      
      
    public void createNewSupplier(Supplier supplier) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

           
            con.setAutoCommit(false);

            SupplierDao.getInstance().create(con, supplier);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to add new supplier " + supplier, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
      
      
      
       public Supplier findSupplier(int supplierId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return SupplierDao.getInstance().find(con, supplierId);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find supplier with id" + supplierId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
       
        public Supplier findSupplierByName(String supplierName) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return SupplierDao.getInstance().findName(con, supplierName);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find supplier with Name " + supplierName, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
       
        public List<Supplier> findAllSuppliers() throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            
            return SupplierDao.getInstance().findAll(con);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find all suppliers", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
        
        public void deleteSupplier(int supplierId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Supplier supplier = SupplierDao.getInstance().find(con, supplierId);
            if (supplier != null) {
                SupplierDao.getInstance().delete(con, supplier);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to delete supplier with supplier id " + supplierId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateSupplier(Supplier supplier) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            SupplierDao.getInstance().update(con, supplier);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to update supplier " + supplier, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
}
