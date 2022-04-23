
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
public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        m.printReverse("hello there");
        m.printReverse("");
    }

    public void printReverse(String word) {
        Scanner sc = new Scanner(System.in);
        while (word/*.trim()*/.isEmpty()) {
            System.out.println("Your input is empty. Try again...");
            System.out.print("Enter a sentence: ");
            word = sc.nextLine();
        }
        String[] tokens = word.split("\\s+"); 
        StringBuffer out = new StringBuffer();
        for (int i = tokens.length - 1; i >= 0; i--) {
            if (i == tokens.length - 1) {
                out.append(tokens[i].substring(0, 1).toUpperCase()).append(tokens[i].substring(1)).append(" ");
            } else if (i == 0) {
                out.append(tokens[i]);
            } else {
                out.append(tokens[i]).append(" ");
            }
        }
        System.out.println("The reversed: " + out);
    }

}
