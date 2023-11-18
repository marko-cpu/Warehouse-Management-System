package com.fink.projectpa;

import com.fink.projectpa.service.AdvanceService;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures Jakarta RESTful Web Services for the application.
 */
@ApplicationPath("rest")
public class JakartaRestConfiguration extends Application {
    
    public static void main(String[] args) {
    
     /* AdvanceService advanceService = AdvanceService.getInstance();
      
    
      System.out.println("1. List Customers and Orders:");
      advanceService.listCustomersAndOrders();
      
      //int supplierId = 1; 
      //advanceService.listProductsForSupplier(supplierId);
      */
      
    }
    
}
