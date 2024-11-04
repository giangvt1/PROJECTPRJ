/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class WorkerSchedule {
    private int id;
    private int quantity;
    private Employee e;
    private ScheduleCampaign sc;

    public WorkerSchedule() {
    }

    public WorkerSchedule(int id, int quantity, Employee e, ScheduleCampaign sc) {
        this.id = id;
        this.quantity = quantity;
        this.e = e;
        this.sc = sc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Employee getE() {
        return e;
    }

    public void setE(Employee e) {
        this.e = e;
    }

    public ScheduleCampaign getSc() {
        return sc;
    }

    public void setSc(ScheduleCampaign sc) {
        this.sc = sc;
    }
    
}
