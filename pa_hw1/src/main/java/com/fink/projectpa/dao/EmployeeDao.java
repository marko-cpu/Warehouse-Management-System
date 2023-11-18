/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;



import com.fink.projectpa.data.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marko
 */
public class EmployeeDao {

   private static final EmployeeDao instance = new EmployeeDao();

    private EmployeeDao() {
    }

    public static EmployeeDao getInstance() {
        return instance;
    }
    
    
    
     public void create(Connection con, Employee employee) throws SQLException {
         
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {

            ps = con.prepareStatement("INSERT INTO Employee(username, last_name, first_name, birth_date) VALUES(?,?,?,?)");
            ps.setString(1, employee.getUsername());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getFirstName());
            ps.setDate(4, employee.getBirthDate());
            
            // Execute the insert statement
             ps.executeUpdate();
        
             // Commit the changes if you're in a transaction
             con.commit();
           
        } finally {
            ResourcesManager.closeResources(rs, ps);
            
        }
    
    
    }
    
    public void update(Connection con, Employee employee) throws SQLException {
         PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE Employee SET last_name=?, first_name=?, birth_date=? WHERE username=?");
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setDate(3, employee.getBirthDate());
            ps.setString(4, employee.getUsername());
            ps.executeUpdate();

           
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    
    }
    
    public void delete(Connection con, Employee employee) throws SQLException {
        
          PreparedStatement ps = null;
        try {

            //delete order
           // OrderDao.getInstance().deleteEmp(con, employee);
           
            //delete customer
            ps = con.prepareStatement("DELETE FROM `Employee` WHERE employee_id=?");
            ps.setInt(1, employee.getEmployee_id());
            ps.executeUpdate();

            

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    
    }
    
    public Employee find(Connection con, int employeeId) throws SQLException {
    
     PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `Employee` where employee_id=?");
            ps.setInt(1, employeeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                
              
           
            
                employee = new Employee(rs.getInt("employee_id"),rs.getString("username"),rs.getString("last_name"),rs.getString("first_name"),
                        rs.getDate("birth_date"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return employee;
    
    }
    
    public Employee findName(Connection con, String username) throws SQLException {
    
     PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        try {
            ps = con.prepareStatement("SELECT * FROM `Employee` where username=?");
            ps.setString(1, username);
                rs = ps.executeQuery();
                if (rs.next()) {



                    employee = new Employee(rs.getInt("employee_id"),rs.getString("username"),rs.getString("last_name"),rs.getString("first_name"),
                            rs.getDate("birth_date"));
                }
            } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return employee;
    
    }
    
    public List<Employee> findAll(Connection con) throws SQLException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM Employee");
            rs = ps.executeQuery();
            while (rs.next()) {
                
        
               Employee employee;
                employee = new Employee(rs.getString("username"),rs.getString("last_name"), rs.getString("first_name"),rs.getDate("birth_date"));
               employeeList.add(employee);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return employeeList;
    }
    
    }
    

