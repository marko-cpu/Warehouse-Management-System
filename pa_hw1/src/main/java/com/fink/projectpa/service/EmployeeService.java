/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;



import com.fink.projectpa.dao.EmployeeDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.exception.ProjectpaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author marko
 */
public class EmployeeService {
    
    
    private static final EmployeeService instance = new EmployeeService();

    private EmployeeService() {
    }
    
    
      public static EmployeeService getInstance() {
        return instance;
    }
      
    
    
      public void createNewEmployee(Employee employee) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

           
            con.setAutoCommit(false);

            EmployeeDao.getInstance().create(con, employee);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to add new employee " + employee, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
      
      
     public Employee findEmployee(int employeeId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return EmployeeDao.getInstance().find(con, employeeId);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find employee with ID " + employeeId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
       
        public List<Employee> findAllEmployee() throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            
            return EmployeeDao.getInstance().findAll(con);

        } catch (SQLException ex) {
            throw new ProjectpaException("Failed to find all employees", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
        
    public void deleteEmployeeByUsername(int employeeId) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Employee employee = EmployeeDao.getInstance().find(con, employeeId);
            if (employee != null) {
                EmployeeDao.getInstance().delete(con, employee);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to delete employee with employeeId " + employeeId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateEmployee(Employee employee) throws ProjectpaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            EmployeeDao.getInstance().update(con, employee);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ProjectpaException("Failed to update employee " + employee, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

   
      
    
}
