/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

import java.io.Serializable;

/**
 *
 * @author marko
 */
public class OrderDetail implements Serializable {
    
    
    private int orderDetail_id;
    private Order order;
    private Product product;
    private int quantity;

    public OrderDetail(Order order, Product product, int Quantity,int orderDetail_id) {
        
        this.order = order;
        this.product = product;
        this.quantity = Quantity;
        this.orderDetail_id = orderDetail_id;
    }

    public OrderDetail() {
    }

    public OrderDetail(Order order, Product product) {
        this.order = order;
        this.product = product;

    }

    public OrderDetail(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
    
    
    
    

    public int getOrderDetail_id() {
        return orderDetail_id;
    }

    public void setOrderDetail_id(int orderDetail_id) {
        this.orderDetail_id = orderDetail_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int Quantity) {
        this.quantity = Quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OrderDetail{");
        sb.append("orderDetail_id=").append(orderDetail_id);
        sb.append(", order=").append(order);
        sb.append(", product=").append(product);
        sb.append(", Quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
