/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author marko
 */
public class Order implements Serializable  {
    
    private int order_id;
    private Timestamp orderDate;
    private Customer customer;
    private Employee employee;
    private Shipper shipper;

    public Order() {
    }
    
    

    public Order(int order_id, Timestamp orderDate, Customer customer, Employee employee, Shipper shipper) {
        this.order_id = order_id;
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
    }

    public Order(Timestamp orderDate, Customer customer, Employee employee, Shipper shipper) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
    }
     public Order(Customer customer, Employee employee, Shipper shipper) {
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
    }

      public Order(Customer customer, Employee employee, Shipper shipper, int order_id) {
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
         this.order_id = order_id;
    }
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }
    
    
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order{");
        sb.append("order_id=").append(order_id);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", customer=").append(customer);
        sb.append(", employee=").append(employee);
        sb.append(", shipper=").append(shipper);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
