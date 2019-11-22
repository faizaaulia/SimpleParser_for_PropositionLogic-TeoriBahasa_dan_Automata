/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.util.Scanner;

/**
 *
 * @author faizaaulia
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Logika Proposisi: ");
        String formula = sc.nextLine();
        Parser p = new Parser(formula);
        
        p.prosesToken();
        p.showToken();
    }
}
