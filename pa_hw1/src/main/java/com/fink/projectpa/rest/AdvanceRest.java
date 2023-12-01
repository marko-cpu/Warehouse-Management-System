/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.exception.ProjectpaException;
import com.fink.projectpa.service.AdvanceService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marko
 */
@Path("advance")
public class AdvanceRest {
    
      private final AdvanceService advanceService = AdvanceService.getInstance();
      
      
    @GET
    @Path("/customers-and-orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> listCustomersAndOrders() throws ProjectpaException {
        return advanceService.listCustomersAndOrders();
    }
    
    @GET
    @Path("/supplier/{supplierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getCustomerById(@PathParam("supplierId") int supplierId) throws ProjectpaException {
        return advanceService.listProductsForSupplier(supplierId);
    }
     
    @GET
    @Path("/shipper/{shipperId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> listProductsByShipper(@PathParam("shipperId") int shipperId) throws ProjectpaException {
        return advanceService.listProductsByShipper(shipperId);
    }
    
    @GET
    @Path("/total-sum-product")
    @Produces(MediaType.APPLICATION_JSON)
    public Double calculateTotalOrderValue() throws ProjectpaException {
        return advanceService.calculateTotalOrderValue();
    }
    
    @GET
    @Path("/customerid/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> calculateOrderTotalForCustomer(@PathParam("customerId") int customerId) throws ProjectpaException {
        return advanceService.calculateOrderTotalForCustomer(customerId);
    }
    
    @GET
    @Path("/shipperTotal/{shipperId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> calculateOrderTotalForShipper(@PathParam("shipperId") int shipperId) throws ProjectpaException {
        return advanceService.calculateOrderTotalForShipper(shipperId);
    }
    
    @GET
    @Path("/supplierTotal/{supplierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> calculateOrderTotalForSupplier(@PathParam("supplierId") int supplierId) throws ProjectpaException {
        return advanceService.calculateOrderTotalForSupplier(supplierId);
    }
    
    @GET
    @Path("/max-value-order-employee")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> findEmployeeWithMaxOrderValue() throws ProjectpaException {
        return advanceService.findEmployeeWithMaxOrderValue();
    }
    
    @GET
    @Path("/top-two-product")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> findTopTwoProducts() throws ProjectpaException {
        return advanceService.findTopTwoProducts();
    }
    
    @GET
    @Path("/top-four-customer")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> findTopFourCustomers() throws ProjectpaException {
        return advanceService.findTopFourCustomers();
    }
    
    @GET
    @Path("/top-supplier")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> findTopSupplier() throws ProjectpaException {
        return advanceService.findTopSupplier();
    }
}

