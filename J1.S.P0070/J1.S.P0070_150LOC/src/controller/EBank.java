/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
//import java.util.Locale;
import java.util.*;

/**
 *
 * @author nghie
 */
public class EBank {

    // Ngôn ngữ mặc định
    private ResourceBundle r = ResourceBundle.getBundle("model/En");
//    private ResourceBundle r = ResourceBundle.getBundle("model/Vi");

    public void setLocate(Locale locate) {
        if (locate.getDisplayLanguage().equalsIgnoreCase("Vietnamese")) {
            r = ResourceBundle.getBundle("model/Vi");
        } else if (locate.getDisplayLanguage().equalsIgnoreCase("English")) {
            r = ResourceBundle.getBundle("model/En");
        }
    }

    public String getMessage(String key) {
        return r.getString(key);
    }

    // Hàm check account number (10 kí tự phải là số)
    public String checkAccountNumber(String accountNumber) {
        if (accountNumber.matches("\\d{10}")) {
            return "";
        }
        return getMessage("errAccNum");
    }

    // Hàm check password (phải thỏa mãn từ 8 đến 31 kí tự, và bao gồm chữ và số)
    public String checkPassword(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,31}$")) {
            return "";
        }
        return getMessage("errPw");

//        String result = "";
//        if (password.length() >= 8 && password.length() <= 31) {
//            boolean haveDigit = false, haveLetter = false;
//            for (int i = 0; i < password.length(); i++) {
//                if (Character.isLetter(password.charAt(i))) {
//                    haveLetter = true;
//                } else if (Character.isDigit(password.charAt(i))) {
//                    haveDigit = true;
//                }
//                if (haveDigit && haveLetter) {
//                    result = password;
//                    break;
//                }
//            }
//        }
//        return result;
    }

    // Hàm tạo captcha
    public String generateCaptcha(/*int len*/) {
        char data[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9'};
        char index[] = new char[5];
//        char index[] = new char[len];

        Random r = new Random();
        int i = 0;
        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
        }
        return new String(index);
    }

    // Hàm check captcha (Chỉ cần có kí tự trong captchaGenerate)
    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        if (captchaGenerate.contains(captchaInput) && !captchaInput.isEmpty()) {
            return "";
        }
        return getMessage("errCapttcha");
    }

}
