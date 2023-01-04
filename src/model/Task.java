package model;

import java.sql.Timestamp;

public class Task {
   
    private int userID;
    private int TaskID;
    private Timestamp dateCreated;
    private String description;
    private String task;
    
    public Task(){  }
    
    public Task(Timestamp dateCreated, String description, String task) {
        this.dateCreated = dateCreated;
        this.description = description;
        this.task = task;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Timestamp timestamp) {
        this.dateCreated = timestamp;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    } 
    public int getTaskID() {
        return TaskID;
    }
    public void setTaskID(int taskID) {
        TaskID = taskID;
    }
}
