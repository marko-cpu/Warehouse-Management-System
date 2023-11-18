/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author marko
 */
public class Employee implements Serializable {
    
    private int employee_id;
    private String username;
    private String lastName;
    private String firstName;
    private Date birthDate;

    public Employee() {
    }
    
    

    public Employee(int employee_id, String username, String lastName, String firstName, Date bithDate) {
        this.employee_id = employee_id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = bithDate;
    }

    public Employee(String username,String lastName, String firstName, Date bithDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = bithDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    
    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date bithDate) {
        this.birthDate = bithDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee{");
        sb.append("employee_id=").append(employee_id);
        sb.append(", username=").append(username);
        sb.append(", lastName=").append(lastName);
        sb.append(", firstName=").append(firstName);
        sb.append(", birthDate=").append(birthDate);
        sb.append('}');
        return sb.toString();
    }

   
    
    
    
    
    
}
