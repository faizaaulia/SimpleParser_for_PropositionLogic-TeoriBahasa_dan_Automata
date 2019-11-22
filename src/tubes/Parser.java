/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author faizaaulia
 */
public class Parser {

    private String formula;
    private List<Integer> token;
    private List<String> kata;
    
    public Parser(String formula) {
        this.formula = formula;
        token = new ArrayList<>();
        kata = new ArrayList<>();
    }

    public String getFormula() {
        return formula;
    }
    
    public void getSuku() {
        int p = formula.length();
        char[] arr = formula.toCharArray();
        String con = "";
        for (int i = 0; i < p; i++) {
            if (arr[i] != ' ' || i == p-1) {
                if (i == p-1) {
                    con += arr[i];
                    kata.add(con);
                }
                con += arr[i];
            } else {
                kata.add(con);
                con = "";
            }
        }
        //System.out.println("Jumlah suku: " + suku.size());
        /*
        for (String i:suku) {
            System.out.println(i);
        } */
    }
    
    public boolean isOperand(String x) {
        return "p".equals(x) || "q".equals(x) 
                || "r".equals(x) || "s".equals(x);
    }
    
    public boolean isNot(String x) {
        char[] cek = x.toCharArray();
        if (cek.length == 3) {
            return cek[0] == 'n' && cek[1] 
                    == 'o' && cek[2] == 't';
        } else {
            return false;
        }
    }
    
    public boolean isAnd(String x) {
        char[] cek = x.toCharArray();
        if (cek.length == 3) {
            return cek[0] == 'a' && cek[1] 
                    == 'n' && cek[2] == 'd';
        } else {
            return false;
        }
    }
    
    public boolean isOr(String x) {
        char[] cek = x.toCharArray();
        if (cek.length == 2) {
            return cek[0] == 'o' && cek[1] 
                    == 'r';
        } else {
            return false;
        }
    }
    
    public boolean isXor(String x) {
        char[] cek = x.toCharArray();
        if (cek.length == 3) {
            return cek[0] == 'x' && cek[1] 
                    == 'o' && cek[2] == 'r';
        } else {
            return false;
        }
    }
    
    public boolean isIf(String x) {
        char[] cek = x.toCharArray();
        if (cek.length == 2) {
            return cek[0] == 'i' && cek[1] == 'f';
        } else {
            return false;
        }
    }
    
    public boolean isThen(String x) {
        char[] cek = x.toCharArray();
        if (cek.length == 4) {
            return cek[0] == 't' && cek[1] == 'h' 
                    && cek[2] == 'e' && cek[3] == 'n';
        } else {
            return false;
        }
    }
    
    public boolean isIff(String x) {
        char[] cek = x.toCharArray();
        if (cek.length == 3) {
            return cek[0] == 'i' && cek[1] 
                    == 'f' && cek[2] == 'f';
        } else {
            return false;
        }
    }
    
    public boolean isBuka(String x) {
        char[] cek = x.toCharArray();
        return cek[0] == '(';
//        if (cek.length == 1) {
//            return cek[0] == '(';
//        } else {
//            return false;
//        }
    }
    
    public boolean isTutup(String x) {
        char[] cek = x.toCharArray();
        return cek[0] == ')';
//        if (cek.length == 1) {
//            return cek[0] == ')';
//        } else {
//            return false;
//        }
    }
    
    public void prosesToken() {
        String cek;
        this.getSuku();
        for (int i = 0; i < kata.size(); i++) {
            cek = kata.get(i);
            if (isOperand(cek))
                token.add(1);
            else if (isNot(cek))
                token.add(2);
            else if (isAnd(cek))
                token.add(3);
            else if (isOr(cek))
                token.add(4);
            else if (isXor(cek))
                token.add(5);
            else if (isIf(cek))
                token.add(6);
            else if (isThen(cek))
                token.add(7);
            else if (isIff(cek))
                token.add(8);
            else if (isBuka(cek))
                token.add(9);
            else if (isTutup(cek))
                token.add(10);
            else token.add(11);
        }
    }
    
    public void showToken() {
        System.out.print("Token: ");
        for (int i:token) {
            if(i == 11) {
                System.out.println("error");
                break;
            }
            System.out.print(i + " ");
        }
        System.out.println("");
    }
}
