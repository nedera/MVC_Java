/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author nghie
 */
public class Controller {

    private List<Student> students = new ArrayList<>();

    // Hàm cho bên view truy cập lấy dữ liệu để in
    public List<Student> allStudent() {
        return students;
    }

    // Thêm sinh viên vào list students
    public void addStudent(Student stu) {
        if (stu == null) {
            return;
        }
        // Nếu sinh viên cho vào mà trùng mã id, kì học, courseName thì không add nữa
        if (findStudentExist(stu) != 0) {
            return;
        }
        students.add(stu);
    }

    // Hàm gán tên môn học (Chỉ nhập được 3 môn). Nếu khác trả về empty bên view lặp lại vòng lặp đến khi đúng
    public String setCourse(String courseName){
//        return (courseName.equalsIgnoreCase("Java") || courseName.equalsIgnoreCase(".Net") || courseName.equalsIgnoreCase("C/C++"));
        if(courseName.equalsIgnoreCase("Java"))
            return "Java";
        else if(courseName.equalsIgnoreCase(".Net"))
            return ".Net";
        else if( courseName.equalsIgnoreCase("C/C++"))
            return "C/C++";
        else 
            return "";
    }
    
    // Hàm tìm xem 1 student truyền vào đã có trong list hay chưa?
    // 0 là chưa có, 1 là có 1 bản (Update sẽ sucess), 2 là đã có 2 bản (Update fail, và phải xóa đi 1 bản)
    public int findStudentExist(Student stu) {
        int howManyrecord = 0;
        for (Student student : students) {
            if (stu.getId() == student.getId() && stu.getSemester().equals(student.getSemester()) && stu.getCourseName().equals(student.getCourseName())) {
                howManyrecord++;
                if (howManyrecord == 2) {
                    break;
                }
            }
        }
        return howManyrecord;
    }

    // Tìm kiếm bằng tên hoặc 1 phần của tên sinh viên (Trả về 1 cái list chứa địa chỉ)
    public List<Student> findStuByName(String name) {
        List<Student> studentLst = new ArrayList<>();
        for (Student student : students) {
            if ((student.getName().toLowerCase()).contains(name.toLowerCase())) {
                studentLst.add(student);
            }
        }
        return studentLst;
    }

    // Lọc ra sinh viên có ID giống nhau phục vụ cho việc update
    // Trả về 1 cái list chứa địa chỉ cho người dùng chọn cho dễ
    public List<Student> findStuByID(int id) {
        List<Student> studentLst = new ArrayList<>();
        for (Student student : students) {
            if (student.getId() == id) {
                studentLst.add(student);
            }
        }
        return studentLst;
    }

    // Sắp xếp danh sách sinh viên đã nhập bằng tên
    public List<Student> sortByName(List<Student> needSort) {
        needSort.sort(new compareStudentByName());
        return needSort;
    }

    // Tìm sinh viên bằng id và trả về địa chỉ index trong student list
    // Phục vụ cho thêm bản student nếu người dùng nhập sinh viên đã có id rồi
    // Thì không bắt người dùng nhập tên nữa
    public int findID(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    // Update lại 1 sinh viên nào đó bằng id (Sửa tên) UPDATE tất cả record
    public void updateName(int id, String name) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(name);
            }
        }
    }

    // Xóa 1 sinh viên đã chọn (Địa chỉ)
    public void delStudent(Student student) {
        students.remove(student);
    }

    // Report
    // Như add report nếu chưa có và sửa report nếu đã có (tăng total course lên)
    public List<Report> showReports() {
        List<Report> reports = new ArrayList<>();
        Report report, pointer;
        for (Student student : students) {
            report = new Report(student.getId(), student.getName(), student.getCourseName(), 1);
            // Nếu chưa có report trong list thì sẽ thêm mới còn không sửa nó
            // findReport trả về null nghĩa là chưa có.
            pointer = findReport(reports, report);
            if (pointer == null) {
                reports.add(report);
            } else {
                findReport(reports, report).setTotalCourse(report.getTotalCourse() + 1);
            }
        }
        return reports;
    }

    // Check xem report đã có trong list hay chưa. nếu có trả về địa chỉ ô nhớ, còn không trả về null
    public Report findReport(List<Report> reports, Report needCheck) {
        for (Report report : reports) {
            if (report.getId() == needCheck.getId() && report.getCourseName().equals(needCheck.getCourseName())) {
                return report;
            }
        }
        return null;
    }

}
