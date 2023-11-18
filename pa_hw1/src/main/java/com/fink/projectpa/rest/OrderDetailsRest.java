/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.OrderDetail;
import com.fink.projectpa.exception.ProjectpaException;
import com.fink.projectpa.service.OrderDetailsService;
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
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marko
 */
@Path("orderDetail")
public class OrderDetailsRest {
     private final OrderDetailsService orderDetailsService = OrderDetailsService.getInstance();
     
     
    @GET
    @Path("/{orderDetailId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetail getCustomerById(@PathParam("orderDetailId") int orderDetailId) throws ProjectpaException {
        return orderDetailsService.findOrderDetail(orderDetailId);
    }
    
    @GET
    @Path("/findAllOrdersDetail")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDetail> getAllOrdersDet() throws ProjectpaException {
        return orderDetailsService.findAllOrdersDetail();
    }
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrdDet(OrderDetail orderDetail) throws ProjectpaException, SQLException{
            orderDetailsService.createOrderDetail(orderDetail.getOrder(), orderDetail.getProduct(), orderDetail.getQuantity());
            return Response.ok().build();
    }
        
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrdDet(OrderDetail orderDetail) throws ProjectpaException {
            orderDetailsService.updateOrderDetail(orderDetail.getOrder(), orderDetail.getProduct(), orderDetail.getQuantity(), orderDetail.getOrderDetail_id() );
            return Response.ok().build();
    }
    
      
    @DELETE
    @Path("/{orderDetailId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("orderDetailId") int orderDetailId) throws ProjectpaException {
            orderDetailsService.deleteOrderDetailById(orderDetailId);
            return Response.ok().build();
    }
    
    
}
