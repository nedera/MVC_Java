package View;


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
public class checkDataIn {
    public static int getInt(Scanner sc, String msg, int min, int max, boolean needCheck){
        int result;
        String Line;
        do {            
            System.out.print(msg);
            Line = sc.nextLine();
            try {
                result = Integer.parseInt(Line);
                if(!needCheck ||(result >= min && result <= max) ) break;
                else continue;
            } catch (Exception e) {
            }
        } while (true);
        return result;
    }
    
    
    
    public static float getFloat(Scanner sc, String msg, float min, float max, boolean eql){
        float result;
        String Line;
        do {            
            System.out.print(msg);
            Line = sc.nextLine();
            try {
                result = Float.parseFloat(Line);
                if(result >= min && result <= max ) break;
                else continue;
            } catch (Exception e) {
            }
        } while (true);
        return result;
    }
    public static float getTimeF(Scanner sc, String msg, float min, float max){
        float result;
        String Line;
        do {            
            System.out.print(msg);
            Line = sc.nextLine();
            try {
                result = Float.parseFloat(Line);
                if(result >= min && result < max ) break;
                else continue;
            } catch (Exception e) {
            }
        } while (true);
        return result;
    }
    public static float getTimeT(Scanner sc, String msg, float min, float max){
        float result;
        String Line;
        do {            
            System.out.print(msg);
            Line = sc.nextLine();
            try {
                result = Float.parseFloat(Line);
                if(result > min && result <= max ) break;
                else continue;
            } catch (Exception e) {
            }
        } while (true);
        return result;
    }
    public static String getString(Scanner sc, String msg, boolean isEmty,String pattern) {
        String Line;
        do {
            System.out.print(msg);
            Line = sc.nextLine();
            if (Line.isEmpty() && !isEmty) {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
