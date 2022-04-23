/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Comparator;
import model.Student;

/**
 *
 * @author nghie
 */
public class compareStudentByName implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
        String [] tokens1 = o1.getName().split("\\s+");
        String [] tokens2 = o2.getName().split("\\s+");
        return tokens1[tokens1.length - 1].compareToIgnoreCase(tokens2[tokens2.length - 1]);
    }
    
}
