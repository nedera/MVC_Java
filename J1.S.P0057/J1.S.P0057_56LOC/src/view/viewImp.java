/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.controllerImp;
import java.util.Scanner;
import model.User;

/**
 *
 * @author nghie
 */
public class viewImp{

    private controllerImp controller;
    
    public static void main(String[] args) {
        viewImp v = new viewImp();
        Scanner sc = new Scanner(System.in);
        v.Display();
        v.InputChoice(sc);
    }

    public void Display() {
        System.out.print("====== USER MANAGEMENT SYSTEM ======\n1. Create a new account"
                + "\n2. Login system"
                + "\n3. Exit\n");
    }

    public void InputChoice(Scanner sc) {
        int choice;
        controller = new controllerImp();
        controller.writeToList();
        do {
            choice = CheckInput.getInt(sc, "> Choose: ", 1, 3);
            switch (choice) {
                case 1:
                    addAccount(createrAccount(sc));
                    System.out.println("Create account successful!");
                    break;
                case 2:
                    if(find(createrAccount(sc)) != null)
                        System.out.println("Login successful!");
                    else
                        System.out.println("Login failed!");
                    break;
                case 3:
                    controller.writeToFile(controller.dataLst(),"user.dat"); 
                    break;
            }
        } while (choice != 3);
    }

    public User createrAccount(Scanner sc) {
        String newUsername = CheckInput.getsString(sc, 5, "Enter Username: ", false, "");
        String password = CheckInput.getsString(sc, 6, "Enter Password: ", false, "");
        return new User(newUsername, password);
    }

    public void addAccount(User acc) {
        controller.addAccount(acc);
    }

    public User find(User acc) {
        return controller.find(acc);
    }

}
