/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.exception.ProjectpaException;
import com.fink.projectpa.service.CustomerService;
import com.fink.projectpa.service.ShipperService;
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
@Path("shipper")
public class ShipperRest {
    
      private final ShipperService shipperService = ShipperService.getInstance();
      
      
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addShipper(Shipper shipper) throws ProjectpaException{
            shipperService.createNewShipper(shipper);
            return Response.ok().build();
    }
    
     @GET
    @Path("/{shipperId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shipper getShipperById(@PathParam("shipperId") int shipperId) throws ProjectpaException {
        return shipperService.findShipper(shipperId);
    }
    
    @GET
    @Path("/findAllShippers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shipper> getAllShippers() throws ProjectpaException {
        return shipperService.findAllShippers();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShipper(Shipper shipper) throws ProjectpaException {
            shipperService.updateShipper(shipper);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{shipperId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteShipperr(@PathParam("shipperId") int shipperId) throws ProjectpaException {
            shipperService.deleteShipper(shipperId);
            return Response.ok().build();
    }
    
}
