/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.exception.ProjectpaException;
import com.fink.projectpa.service.OrderService;
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
@Path("order")
public class OrderRest {
     private final OrderService orderService = OrderService.getInstance();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrd(Order order) throws ProjectpaException{
            orderService.createOrder(order.getCustomer(), order.getEmployee(), order.getShipper());
            return Response.ok().build();
    }
    
    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getCustomerById(@PathParam("orderId") int orderId) throws ProjectpaException {
        return orderService.findOrder(orderId);
    }
    
    
     @GET
    @Path("/findAllOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders() throws ProjectpaException {
        return orderService.findAllOrders();
    }
    
    
    @DELETE
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("orderId") int orderId) throws ProjectpaException {
            orderService.deleteOrderById(orderId);
            return Response.ok().build();
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrd(Order order) throws ProjectpaException {
            orderService.updateOrder(order.getCustomer(), order.getEmployee(), order.getShipper(), order.getOrder_id());
            return Response.ok().build();
    }
    
    
}
