/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author acer
 */
public class ScheduleCampaign {
    private int id;
    private PlanCampaign camps;
    private  Date date;
    private String shift;
    private int quantity;
    private Product pros;

    public PlanCampaign getCamps() {
        return camps;
    }

    public void setCamps(PlanCampaign camps) {
        this.camps = camps;
    }


    public Product getPros() {
        return pros;
    }

    public void setPros(Product pros) {
        this.pros = pros;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
