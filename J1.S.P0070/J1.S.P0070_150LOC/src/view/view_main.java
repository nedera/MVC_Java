/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.EBank;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author nghie
 */
public class view_main {

    EBank ctrl = new EBank();

    public static void main(String[] args) {
        view_main vm = new view_main();
        vm.inputChoice();
    }

    // Hàm switch - choice cho người dùng lựa chọn sau đó gọi các hàm phía dưới
    private void inputChoice() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
//            System.out.println("-------Login--------");
//            System.out.println("1. Vietnamese\n2. English\n3. Exit");
            System.out.println(ctrl.getMessage("choseLang"));
            choice = CheckInput.getInt(sc, ctrl.getMessage("getOption"), 1, 3);
            switch (choice) {
                case 1:
                    setLocate(new Locale("vi"));
                    checkAccountNumber();
                    checkPassword();
                    checkCaptcha();
                    break;
                case 2:
                    setLocate(new Locale("en"));
                    checkAccountNumber();
                    checkPassword();
                    checkCaptcha();
                    break;
                case 3:
                    break;
            }
        } while (choice != 3);
    }

    // gọi đến hàm setLocate bên con troll (Sau khi biết được người dùng nhập TV hay TA)
    private void setLocate(Locale locate) {
        ctrl.setLocate(locate);
    }

    // Lấy tin nhắn tương ứng với tiếng và bảo người dùng nhập số tài khoản
    // Sau đó gọi hàm checkAccountNumber() bên controller để check thỏa mãn hay không?
    // Nếu thỏa màn thì giữ nguyên còn không in ra lỗi(hàm bên controllẻ trả về mes lỗi) và tiếp tục bảo người dùng nhập lại
    private void checkAccountNumber() {
        Scanner sc = new Scanner(System.in);
        String accNum,getMessAcc;
        do {
            System.out.print(ctrl.getMessage("accNum"));
            accNum = sc.nextLine();
            getMessAcc = ctrl.checkAccountNumber(accNum);
            if (!getMessAcc.equals("")) {
                System.err.println(getMessAcc);
            } else {
                break;
            }
        } while (true);
    }

    // Lấy tin nhắn tương ứng với tiếng và bảo người dùng nhập mật khẩu
    // Sau đó gọi hàm checkPassword() bên controller để check thỏa mãn hay không?
    // Nếu thỏa màn thì giữ nguyên còn không in ra lỗi(hàm bên controller trả về mes lỗi) và tiếp tục bảo người dùng nhập lại
    private void checkPassword() {
        Scanner sc = new Scanner(System.in);
        String pass, getMessPass;
        do {
            System.out.print(ctrl.getMessage("pw"));
            pass = sc.nextLine();
            getMessPass = ctrl.checkPassword(pass);
            if (!getMessPass.equals("")) {
                System.err.println(getMessPass);
            } else {
                break;
            }
        } while (true);
    }

    // Lấy tin nhắn tương ứng với tiếng và bảo người dùng nhập captcha đã random
    // Sau đó gọi hàm checkCaptcha(captchaInput, captchaGenerate) bên controller để check giống nhau hay không?
    // Nếu giống nhau thì break vòng do - while. Còn không random ra captcha mới và bảo người dùng nhập lại
    private void checkCaptcha() {
        Scanner sc = new Scanner(System.in);
        String captchaGenerate, captchaInput, getMessCaptcha;
        do {
            captchaGenerate = ctrl.generateCaptcha();
            System.out.println(ctrl.getMessage("captchaGenerate") + captchaGenerate);
            System.out.print(ctrl.getMessage("captchaInput"));
            captchaInput = sc.nextLine();
            getMessCaptcha = ctrl.checkCaptcha(captchaInput, captchaGenerate);
            if (!getMessCaptcha.equals("")) {
                System.err.println(getMessCaptcha);
            }else
                break;
        } while (true);
    }
}
