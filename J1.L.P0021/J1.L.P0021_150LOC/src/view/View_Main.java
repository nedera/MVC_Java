/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.List;
import java.util.Scanner;
import model.*;

/**
 *
 * @author nghie
 */
public class View_Main {

    Controller ctrl = new Controller();

    public static void main(String[] args) {
        View_Main vm = new View_Main();

        vm.addStudent(new Student(1, "Nguyen Van Nghiep", "Spring2019", "C/C++"));
        vm.addStudent(new Student(1, "Nguyen Van Nghiep", "Spring2019", "C/C++"));

        vm.addStudent(new Student(1, "Nguyen Van Nghiep", "Summer2019", "C/C++"));
        vm.addStudent(new Student(1, "Nguyen Van Nghiep", "Fall2019", "Java"));
        vm.addStudent(new Student(1, "Nguyen Van Nghiep", "Spring2020", ".Net"));

        vm.addStudent(new Student(2, "Ta Thien Hiep", "Spring2019", "C/C++"));
        vm.addStudent(new Student(2, "Ta Thien Hiep", "Summer2019", "Java"));
        vm.addStudent(new Student(2, "Ta Thien Hiep", "Fall2019", "Java"));
        vm.addStudent(new Student(2, "Ta Thien Hiep", "Spring2020", ".Net"));
        vm.addStudent(new Student(2, "Ta Thien Hiep", "Summer2020", ".Net"));

        vm.addStudent(new Student(3, "Nguyen Dinh Tai", "Spring2019", "C/C++"));
        vm.addStudent(new Student(3, "Nguyen Dinh Tai", "Fall2019", "Java"));
        vm.addStudent(new Student(3, "Nguyen Dinh Tai", "Spring2020", ".Net"));

        vm.inputChoice();
    }

    // Switch - choice để chọn option
    private void inputChoice() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("WELCOME TO STUDENT MANAGEMENT\n"
                    + "1. Create\n"
                    + "2. Find and Sort\n"
                    + "3. Update/Delete\n"
                    + "4. Report\n"
                    + "5. Exit\n"
                    + "(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program).");
            choice = CheckInput.getInt(sc, ">> Your choice: ", 1, 5);
            switch (choice) {
                case 1:
                    // Check xem đã có 10 sinh viên hay chưa?
                    if (ctrl.allStudent().size() <= 10) {
                        addStudent(createStudent());
                    } else {
                        String confirm = CheckInput.getsString(sc, "You have 10 studens already! Do you want to continue (Y/N)? ", false, "[YyNn]");
                        if (confirm.compareToIgnoreCase("y") == 0) {
                            addStudent(createStudent());
                        }
                    }
                    break;
                case 2:
                    findAndSort();
                    break;
                case 3:
                    System.err.println(">> All records of student <<");
                    displayRecord(ctrl.allStudent());
                    update_Del();
                    break;
                case 4:
                    report();
                    break;
                case 5:
                    break;
            }
        } while (choice != 5);

    }

    // Người dùng tạo student (Dùng findId để xem có id hay chưa nếu có rồi không cần nhập tên
    public Student createStudent() {
        Scanner sc = new Scanner(System.in);
        Student newStudent = new Student();
        newStudent.setId(CheckInput.getIntNoMax(sc, String.format("Enter student id (>=%d): ", 1), 1));
        // check xem có cần nhập tên hay không
        int checkID = ctrl.findID(newStudent.getId());
        if (checkID == -1) {
            newStudent.setName(CheckInput.getsString(sc, "Enter student's name (Not empty): ", false, ""));
        } else {
            newStudent.setName(ctrl.allStudent().get(checkID).getName());
            System.err.println("The student id exists. You don't need to input student's name");
        }
        newStudent.setSemester(CheckInput.getsString(sc, "Enter semester (Not empty): ", false, ""));
        String courseName;
        do {
            System.out.print("Enter course's name (Java, .Net, C/C++): ");
            courseName = sc.nextLine();
            courseName = ctrl.setCourse(courseName);
        } while (courseName.isEmpty());
        newStudent.setCourseName(courseName);
        return newStudent;
    }

    // Add 1 student vào list bên controller (Dùng createStudent() ở trên để tạo)
    public void addStudent(Student student) {
        ctrl.addStudent(student);
    }

    // Người dùng nhập id nếu tồn tại tiến hành update hoặc xóa
    public void update_Del() {
        Scanner sc = new Scanner(System.in);
        int id = CheckInput.getIntNoMax(sc, String.format("Enter student id (>=%d): ", 1), 1);
        if (ctrl.findID(id) != -1) {
            // Đưa ra 1 bảng của sinh viên có id nhập vào người dùng nhập index của list findStuByID(id)
            System.out.println("================= Choose a record in the table =================");
            displayRecord(ctrl.findStuByID(id));
            int idxSmallRecord = CheckInput.getInt(sc, "Enter index of a record: ", 1, ctrl.findStuByID(id).size());
            // Hỏi người dùng muốn update hay xóa
            String x = CheckInput.getsString(sc, "Do you want to update (U) or delete (D) student: ", false, "[UuDd]");
            if (x.compareToIgnoreCase("U") == 0) {
                // Hiển thị tên cũ và bảo người dùng nhập tên mới (SAU ĐÓ UPDATE LẠI TẤT CẢ CÁC TÊN CÓ ID ĐÃ CHỌN)
                System.out.println("Student's old name: " + ctrl.allStudent().get(ctrl.findID(id)).getName() + "!");
                String newName = CheckInput.getsString(sc, "Please enter new name (Not empty): ", false, "");

                // Gọi hàm update name bên controller truyền vào id và new name
                ctrl.updateName(id, newName);
                Student stuUpdate = ctrl.findStuByID(id).get(idxSmallRecord - 1);
                Student preUpdate = new Student(stuUpdate.getId(), stuUpdate.getName(), stuUpdate.getSemester(), stuUpdate.getCourseName());
                // Tiến hành update kì học và môn học do người dùng nhập mới
                String newSemester = CheckInput.getsString(sc, "Enter new semester (Not empty): ", false, "");
                String newCourse;
                do {
                    System.out.print("Enter new course's name (Java, .Net, C/C++): ");
                    newCourse = sc.nextLine();
                    newCourse = ctrl.setCourse(newCourse);
                } while (newCourse.isEmpty());

                stuUpdate.setSemester(newSemester);
                stuUpdate.setCourseName(newCourse);
                if (ctrl.findStudentExist(stuUpdate) == 2) {
                    String choice = CheckInput.getsString(sc, "The student who you updated is exist. Do you wanna dele this record (Y/N): ", true, "[YyNn]");
                    if (choice.equalsIgnoreCase("Y")) {
                        ctrl.delStudent(stuUpdate);
                    } else {
                        stuUpdate.setCourseName(preUpdate.getCourseName());
                        stuUpdate.setSemester(preUpdate.getSemester());
                    }
                } else {
                    System.err.println(">> Update sucessfully! <<");
                }
            } else {
                // Xóa thì sẽ đơn giản hơn. Gọi hàm xóa bên controller và truyền địa chỉ vào
                ctrl.delStudent(ctrl.findStuByID(id).get(idxSmallRecord - 1));
                System.err.println(">> Dele sucessfully! <<");
            }
        } else {
            System.err.println("The id doesn't exit");
        }
    }

    // Tìm kiếm và sắp xếp người dùng sẽ nhập 1 phần của tên và gọi hàm findStuByName(partOfName) nó sẽ trả về list
    // Sau đó sắp xếp và in ra
    public void findAndSort() {
        Scanner sc = new Scanner(System.in);
        String partOfName = CheckInput.getsString(sc, "Enter name student or a part of name (not empty): ", false, "");
        if (!ctrl.findStuByName(partOfName).isEmpty()) {
            System.out.println(">> Table all students have name is " + partOfName + " <<");
            displayRecord(ctrl.sortByName(ctrl.findStuByName(partOfName)));
            return;
        }
        System.err.println(">> You don't have any student! with you input <<");
    }

    // Gọi hàm showReports() nó trả về list<Report> và in ra
    public void report() {
        System.out.println(">> The report <<");
        for (Report report : ctrl.showReports()) {
            System.out.printf("%-3s | %-20s | %-12s | %s\n", report.getId(), report.getName(), report.getCourseName(), report.getTotalCourse());
        }
    }

    // Hiện các list sinh viên(Phục vụ hiển thị hoặc update)
    public void displayRecord(List<Student> stuLst) {
        System.out.printf("%-8s%-16s%-20s%-16s%-16s\n", "Index", "Student ID", "Name", "Semester", "Course Name");
        int i = 1;
        for (Student student : stuLst) {
            System.out.printf("%-8s%-16s%-20s%-16s%-16s\n", i, student.getId(), student.getName(), student.getSemester(), student.getCourseName());
            i++;
        }
    }
}
