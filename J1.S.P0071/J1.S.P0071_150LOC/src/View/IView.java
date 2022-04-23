/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Task;
import java.util.List;

/**
 *
 * @author nghie
 */
public interface IView {
//    <T>  void getDataTasks(List <T> list);
    Task createTask();
    void addTask( Task newTask);
    void deleteTask();
}
