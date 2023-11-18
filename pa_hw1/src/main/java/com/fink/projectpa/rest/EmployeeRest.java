/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;


import com.fink.projectpa.data.Employee;
import com.fink.projectpa.exception.ProjectpaException;
import com.fink.projectpa.service.EmployeeService;
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
@Path("employee")
public class EmployeeRest {
    
    private final EmployeeService employeeService = EmployeeService.getInstance();
    
    
     
    @GET
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getCustomerById(@PathParam("employeeId") int employeeId) throws ProjectpaException {
        return employeeService.findEmployee(employeeId);
    }
    
    @GET
    @Path("/findAllEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployee() throws ProjectpaException {
        return employeeService.findAllEmployee();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) throws ProjectpaException{
            employeeService.createNewEmployee(employee);
            return Response.ok().build();
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) throws ProjectpaException {
            employeeService.updateEmployee(employee);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("employeeId") int employeeId) throws ProjectpaException {
            employeeService.deleteEmployeeByUsername(employeeId);
            return Response.ok().build();
    }
}
