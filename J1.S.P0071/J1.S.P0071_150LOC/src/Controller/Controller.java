/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Task;
import Model.TaskType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nghie
 */
public class Controller implements IController {

    private ArrayList<Task> taskLst = new ArrayList<>();
    private ArrayList<TaskType> tskTypeLst = new ArrayList<>();
    
    //Hàm thêm task type vào list bên controller
    public void addTaskType(TaskType newTask) {
        tskTypeLst.add(newTask);
    }

    // Trả về list các task cho bên view truy cập. Phục vụ hiển thị
    public List<Task> getTasks() {
        return taskLst;
    }

    // Trả về list các task type (Có 4 cái đề fixed k sửa được) cho bên view truy cập. Phục vụ hiển thị
    public List<TaskType> getTaskTypes() {
        return tskTypeLst;
    }

    // Trả về tên đầy đủ của tasktype khi người dùng nhập bằng Id (Phục vụ hiển thị)
    public String chageTaskTypeId(int id) {
        return tskTypeLst.get(id - 1).getNameTask();
    }

    // Thêm task mà người dùng nhập vào list bên controller
    @Override
    public void addTask(Task newTask) {
        taskLst.add(newTask);
    }

    // Xóa task trong list bằng địa chỉ (Dùng hàm find ID ở ngay phía dưới)
    @Override
    public void deleteTask(Task needRemove) {
        taskLst.remove(needRemove);
    }

    public Task findId(int id) {
        for (Task task : taskLst) {
            if (task.getId() == id) {
//                System.out.println("tim thay");
                return task;
            }
        }
        return null;
    }

}
