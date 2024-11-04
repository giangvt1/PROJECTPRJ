package model;

import java.sql.Date;
import java.util.ArrayList;
import model.accesscontroller.Department;

public class Plan {
    private int id;
    private Department dept;
    private Date start;
    private Date end;
    private Employee emps;
    private ArrayList<PlanCampaign> campaigns = new ArrayList<>();

    // Constructor for easier initialization
    public Plan() {}
    
    public Plan(int id, Department dept, Date start, Date end) {
        this.id = id;
        this.dept = dept;
        this.start = start;
        this.end = end;
    }

    // Getter and Setter for Employee
    public Employee getEmps() {
        return emps;
    }

    public void setEmps(Employee emps) {
        this.emps = emps;
    }

    // Getter and Setter for Plan ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for Department
    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    // Getter and Setter for Start Date
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    // Getter and Setter for End Date
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    // Getter and Setter for Campaigns list
    public ArrayList<PlanCampaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(ArrayList<PlanCampaign> campaigns) {
        this.campaigns = campaigns;
    }
}
