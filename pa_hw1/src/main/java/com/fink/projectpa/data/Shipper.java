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
public class Shipper implements Serializable {
    
    private int shipperId;
    private String shipperName;
    private String phone;

    public Shipper() {
    }
    
    

    public Shipper(int shipperId, String shipperName, String phone) {
        this.shipperId = shipperId;
        this.shipperName = shipperName;
        this.phone = phone;
    }

    public Shipper(String shipperName, String phone) {
        this.shipperName = shipperName;
        this.phone = phone;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shipper{");
        sb.append("shipperId=").append(shipperId);
        sb.append(", shipperName=").append(shipperName);
        sb.append(", phone=").append(phone);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
