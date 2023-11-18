/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;


import com.fink.projectpa.data.Supplier;
import com.fink.projectpa.exception.ProjectpaException;
import com.fink.projectpa.service.SupplierService;
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
@Path("supplier")
public class SupplierRest {
    
     private final SupplierService supplierService = SupplierService.getInstance();
    
    
    @GET
    @Path("/id/{supplierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Supplier getSuppliersById(@PathParam("supplierId") int supplierId) throws ProjectpaException {
        return supplierService.findSupplier(supplierId);
    }
    
    @GET
    @Path("/name/{supplierName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Supplier getSuppliersByNam(@PathParam("supplierName") String supplierName) throws ProjectpaException {
        return supplierService.findSupplierByName(supplierName);
    }
    
    @GET
    @Path("/findAllSuppliers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Supplier> getAllSuppliers() throws ProjectpaException {
        return supplierService.findAllSuppliers();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSupplier(Supplier supplier) throws ProjectpaException{
            supplierService.createNewSupplier(supplier);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSupplier(Supplier supplier) throws ProjectpaException {
            supplierService.updateSupplier(supplier);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{supplierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSupplier(@PathParam("supplierId") int supplierId) throws ProjectpaException {
            supplierService.deleteSupplier(supplierId);
            return Response.ok().build();
    }
}
