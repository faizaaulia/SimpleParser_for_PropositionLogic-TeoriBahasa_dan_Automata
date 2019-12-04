package tubes;

import java.util.Scanner;

/** @author faizaaulia */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Logika Proposisi: ");
        String formula = sc.nextLine();
        String cek = formula.toLowerCase();
        Parser p = new Parser(cek);
        
        p.prosesToken();
        p.showToken();
        p.validation();
        //p.showKata();
    }
}
