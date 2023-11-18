/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.exception.ProjectpaException;
import com.fink.projectpa.service.CustomerService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;




/**
 *
 * @author marko
 */
@Path("customer")
public class CustomerRest {
    
    
    private final CustomerService customerService = CustomerService.getInstance();
    
     
    @GET
    public Response ping(){
        return Response
                .ok("customer")
                .build();   
    }
    
    
    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerById(@PathParam("customerId") int customerId) throws ProjectpaException {
        return customerService.findCustomer(customerId);
    }
    
    @GET
    @Path("/findAllCutomers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomer() throws ProjectpaException {
        return customerService.findAllCutomers();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) throws ProjectpaException{
            customerService.createNewCustomer(customer);
            return Response.ok().build();
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) throws ProjectpaException {
            customerService.updateCustomer(customer);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("customerId") int customerId) throws ProjectpaException {
            customerService.deleteCustomerById(customerId);
            return Response.ok().build();
    }
    
    
}
