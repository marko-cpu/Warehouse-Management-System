/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.Supplier;
import com.fink.projectpa.exception.ProjectpaException;
import com.fink.projectpa.service.ProductService;
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
@Path("product")
public class ProductRest {
    
     private final ProductService productService = ProductService.getInstance();
     

    @GET
    @Path("/{productName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductByName(@PathParam("productName") String productName) throws ProjectpaException {
        return productService.findProduct(productName);
    }
    
   /* @GET
    @Path("/supplier/{supplierName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Supplier getProductByCategoryName(@PathParam("supplierName") String supplierName) throws projectpa {
        return productService.findProductSupplier(supplierName);
    }*/
    
    @GET
    @Path("/findAllProduct")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProduct() throws ProjectpaException {
        return productService.findAllProduct();
    }
     
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) throws ProjectpaException{
            productService.addNewProduct(product);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product) throws ProjectpaException {
            productService.updateProduct(product);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productId") int productId) throws ProjectpaException {
            productService.deleteProduct(productId);
            return Response.ok().build();
    }
     
}
