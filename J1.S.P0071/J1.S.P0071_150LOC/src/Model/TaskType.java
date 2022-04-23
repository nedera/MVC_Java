/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author nghie
 */
public class TaskType {
    private int id;
    private String nameTask;

    public TaskType() {
    }

    public TaskType(int id, String nameTask) {
        this.id = id;
        this.nameTask = nameTask;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }
    
}
