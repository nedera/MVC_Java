package view;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nghie
 */
public class CheckInput {
    public static int getInt(Scanner sc, String msg, int min, int max){
        int result;
        String Line;
        do {            
            System.out.print(msg);
            Line = sc.nextLine();
            try {
                result = Integer.parseInt(Line);
                if(result >= min && result <= max ) break;
                else continue;
            } catch (Exception e) {
                continue;
            }
        } while (true);
        return result;
    }
    public static String getsString(Scanner sc, int len, String msg, boolean isEmty,String pattern) {
        String Line;
        do {
            System.out.print(msg);
            Line = sc.nextLine();
            if ((Line.length() <= len || Line.contains(" ")) && !isEmty) {
                System.out.printf("You must enter least at %s characters, and no space!\n",len);
                continue;
            } else {
                if(pattern.isEmpty()) break;
                else if(Line.matches(pattern)) break;
                else continue;
                
            }
        } while (true);
        return Line;
    }
    public static Date getDate(Scanner sc, String msg){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        String line = "";
        Date date;
        do {            
            System.out.print(msg);
            line = sc.nextLine();
            try {
                date = dateFormat.parse(line);
                break;
            } catch (Exception e) {
                continue;
            }
        } while (true);
        return date;
    }
}
