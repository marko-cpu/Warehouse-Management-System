/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.OrderDao;
import com.fink.projectpa.dao.ProductDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.dao.SupplierDao;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.Supplier;
import com.fink.projectpa.exception.ProjectpaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marko
 */
public class ProductService {
    
    private static final ProductService instance = new ProductService();

    private ProductService() {
        
    }

    public static ProductService getInstance() {
        return instance;
    }

    public void addNewProduct(Product product) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

             ProductDao.getInstance().create(con, product);
        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to add new product " + product, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    
     public Product findProduct(String productName) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ProductDao.getInstance().findName(con, productName);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find product with name " + productName, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
     
       public Supplier findProductSupplier(String supplierName) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return SupplierDao.getInstance().findName(con, supplierName);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find product category with name " + supplierName, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
       
         public List<Product> findAllProduct() throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            
            return ProductDao.getInstance().findAll(con);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find All product", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
       
    public void deleteProduct(int productId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Product product = ProductDao.getInstance().findId(con, productId);
            if (product != null) {
                ProductDao.getInstance().delete(con, product);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to delete product with id " + productId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateProduct(Product product) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            ProductDao.getInstance().update(con, product);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to update product " + product, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
