/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.Task;
import Model.TaskType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author nghie
 */
public class View implements IView {

    Controller ctrl = new Controller();

    public static void main(String[] args) {
        View v = new View();
        v.addTaskTypeFixed();
        v.getDataTasks("taskTypes");
//        v.addTask(v.createTask());
//        v.addTask(v.createTask());
//        v.getDataTasks("tasks");
        v.inputChoice();
    }

    // Thêm 4 taskType đã cho trước đề bài
    public void addTaskTypeFixed() {
        ctrl.addTaskType(new TaskType(1, "Code"));
        ctrl.addTaskType(new TaskType(2, "Test"));
        ctrl.addTaskType(new TaskType(3, "Design"));
        ctrl.addTaskType(new TaskType(4, "Review"));
    }

    // Switch - case và gọi các hàm bên dưới
    public void inputChoice() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            getDataTasks("Menu");
            choice = checkDataIn.getInt(sc, ">>>    Choice: ", 1, 4, true);
            System.out.println("");
            switch (choice) {
                case 1:
                    System.out.println("------------Add Task------------");
                    addTask(createTask());
                    System.out.println("Add a task sucessful!\n");
                    break;
                case 2:
                    System.out.println("-------Del Task-------");
                    deleteTask();
                    break;
                case 3:
                    System.out.println("----------------------------------- Task -----------------------------------");
                    getDataTasks("tasks");
                    System.out.println("");
                    break;
                case 4:
                    break;
            }
        } while (choice != 4);

    }

    // Phục vụ switch - case phía trên hiển thị format của đề bài
    public void getDataTasks(String nameLst) {
        if (nameLst.equals("Menu")) {
            System.out.println("========= Task program =========");
            System.out.println("    1.  Add Task");
            System.out.println("    2.  Delete Task");
            System.out.println("    3.  Display Task");
            System.out.println("    4.  exit");
        } else if (nameLst.equals("taskTypes")) {
            System.out.println("===Task Types===");
            System.out.printf("%-3s %s\n", "ID", "Name");
            for (TaskType list : ctrl.getTaskTypes()) {
                System.out.printf("%-3s %s\n", list.getId(), list.getNameTask());
            }

        } else if (nameLst.equals("tasks")) {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            System.out.printf("%-5s%-16s%-12s%-12s%-12s%-12s%-12s\n", "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
            for (Task tsk : ctrl.getTasks()) {
                System.out.printf("%-5d%-16s%-12s%-12s%-12s%-12s%-12s\n", tsk.getId(), tsk.getName(), ctrl.chageTaskTypeId(tsk.getTaskTypeID()), df.format(tsk.getDate()), tsk.getPlan(), tsk.getAssignee(), tsk.getReviewer());
            }
        }

    }

    // Cô bảo sửa lại thời gian vào làm việc và thời gian về
    //(Đã viết thêm  hàm getTimeF bên checkDataIn (Class tiện ích) nhập từ >=  8 và <17.5)
    //(Đã viết thêm  hàm getTimeT bên checkDataIn (Class tiện ích) nhập từ  > Tgian bắt đầu và <= 17.5)
    @Override
    public Task createTask() {
        Scanner sc = new Scanner(System.in);
        String name = checkDataIn.getString(sc, "Requirement Name: ", false, "");
        int taskTypeID = checkDataIn.getInt(sc, "Task Type: ", 1, 4, true);
        Date date = checkDataIn.getDate(sc, "Date: ");
//        float Time = 17.5F;
        float fromTime = checkDataIn.getTimeF(sc, String.format("From(>=%s - <%s): ", 8, 17.5), 8, 17.5F);
        float toTime = checkDataIn.getTimeT(sc, String.format("To(>%s - <=%s): ", fromTime, 17.5), fromTime, 17.5F);
        String assignee = checkDataIn.getString(sc, "Assignee: ", false, "");
        String reviewer = checkDataIn.getString(sc, "Reviewer: ", false, "");
        int id;
        if (ctrl.getTasks().isEmpty()) {
            id = 1;
        } else {
            id = ctrl.getTasks().get(ctrl.getTasks().size() - 1).getId() + 1;
        }
        return new Task(id, taskTypeID, name, date, toTime - fromTime, assignee, reviewer);
    }

    @Override
    public void addTask(Task newTask) {

        ctrl.addTask(newTask);
    }

    //cô bảo sửa deleteTask. Có 1 cái biến false (Là hỏi xem có cần check max min hay không false - là không cần)
    // Hoặc nếu cần check thì nó sẽ giao động từ số 1 đến id lớn nhất (Là ở cuối danh sách).
    @Override
    public void deleteTask() {
        Scanner sc = new Scanner(System.in);
        int maxID = ctrl.getTasks().get(ctrl.getTasks().size() - 1).getId();
        int id = checkDataIn.getInt(sc, "ID: ", 1, maxID, false);
        // ctrl.findId(Id) là để xem id đó có ở trong task list đã nhập hay không?
        Task needRemove = ctrl.findId(id);
        if (needRemove == null) {
            System.out.println("Delete Failed! ID not exist!\n");
            return;
        }
        ctrl.deleteTask(needRemove);
        System.out.println("Delete a task sucessful!\n");
    }

}
