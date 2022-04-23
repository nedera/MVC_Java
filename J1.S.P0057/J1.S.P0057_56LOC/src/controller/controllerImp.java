/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author nghie
 */
public class controllerImp implements IController {

    private List<User> userLst = new ArrayList<>() ;
    
    public void writeToList(){
        userLst = readFromFile("user.dat");

    }
    public List dataLst() {
        return userLst;
    }

    public void addAccount(User acc) {
        userLst.add(acc);
    }

    public User find(User acc) {
        for (User user : userLst) {
            if (user.getName().equals(acc.getName()) && user.getPassword().equals(acc.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public <T> void writeToFile(List<T> list, String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public <T> List<T> readFromFile(String fileName) {
        List<T> list = new ArrayList<>();
        File file = new File(fileName);
        if (file.length() > 0) {
            try {
                file.createNewFile();
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object o = ois.readObject();
                list = (List<T>) o;
                ois.close();
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

}
