
package Model;

import java.util.Date;

/**
 *
 * @author nghie
 */
public class Task {
    private int id = 0;
    private int taskTypeID;
    private String name;
    private Date date;
    private float plan;
  
    private String assignee;
    private String reviewer;

    public Task() {
    }

    public Task(int id, int taskTypeID, String name, Date date, float plan, String assignee, String reviewer) {
        this.taskTypeID = taskTypeID;
        this.name = name;
        this.date = date;
        this.plan = plan;
        this.assignee = assignee;
        this.reviewer = reviewer;
        this.id = id;
        
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public int getTaskTypeID() {
        return taskTypeID;
    }

    public void setTaskTypeID(int taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPlan() {
        return plan;
    }

    public void setPlan(float plan) {
        this.plan = plan;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    
    
}
