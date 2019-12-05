package tubes;

import java.util.ArrayList;
import java.util.List;

/** @author faizaaulia */
public class Parser {

    private String formula;
    private List<String> token;
    private List<String> kata;
    
    public Parser(String formula) {
        this.formula = formula;
        token = new ArrayList<>();
        kata = new ArrayList<>();
    }
    
    public void getSuku() {
        //memisahkan inputan menjadi per kata dan dimasukkan ke arr
        int p = formula.length();
        char[] arr = formula.toCharArray();
        String con = "";
        for (int i = 0; i < p; i++) {
            if (arr[i] != ' ' || i == p-1) {
                if (arr[i] == '(' || (i != p-1 && arr[i+1] == '(')) {
                    con += arr[i];
                    kata.add(con);
                    con = "";
                } else if (i == p-1) {
                    if (arr[i] == ' ') {
                        kata.add(con);
                        con = "";
                    } else if (arr[i] == ')') {
                        con += arr[i];
                        kata.add(con);
                        con = "";
                    } else {
                        con += arr[i];
                        kata.add(con);
                        con = "";
                    }
                } else {
                    if (i != p-1 && arr[i+1] == ')') {
                        con += arr[i];
                        kata.add(con);
                        con = "";
                    } else
                        con += arr[i];
                }
            } else {
                if (arr[i-1] != '(') {
                    if (arr[i-1] == ' ')
                        con = "";
                    else {
                        kata.add(con);
                        con = "";
                    }
                }
            }
        }
    }
    
    public boolean isOperand(String x) {
        //memeriksa apakah string merupakan operand
        return "p".equals(x) || "q".equals(x) 
                || "r".equals(x) || "s".equals(x);
    }
    
    public boolean isNot(String x) {
        //memeriksa apakah string merupakan operator not
        char[] cek = x.toCharArray();
        if (cek.length == 3) {
            return cek[0] == 'n' && cek[1] 
                    == 'o' && cek[2] == 't';
        } else {
            return false;
        }
    }
    
    public boolean isAnd(String x) {
        //memeriksa apakah string merupakan operator and
        char[] cek = x.toCharArray();
        if (cek.length == 3) {
            return cek[0] == 'a' && cek[1] 
                    == 'n' && cek[2] == 'd';
        } else {
            return false;
        }
    }
    
    public boolean isOr(String x) {
        //memeriksa apakah string merupakan operator or
        char[] cek = x.toCharArray();
        if (cek.length == 2) {
            return cek[0] == 'o' && cek[1] 
                    == 'r';
        } else {
            return false;
        }
    }
    
    public boolean isXor(String x) {
        //memeriksa apakah string merupakan operator xor
        char[] cek = x.toCharArray();
        if (cek.length == 3) {
            return cek[0] == 'x' && cek[1] 
                    == 'o' && cek[2] == 'r';
        } else {
            return false;
        }
    }
    
    public boolean isIf(String x) {
        //memeriksa apakah string merupakan operator if
        char[] cek = x.toCharArray();
        if (cek.length == 2) {
            return cek[0] == 'i' && cek[1] == 'f';
        } else {
            return false;
        }
    }
    
    public boolean isThen(String x) {
        //memeriksa apakah string merupakan operator then
        char[] cek = x.toCharArray();
        if (cek.length == 4) {
            return cek[0] == 't' && cek[1] == 'h' 
                    && cek[2] == 'e' && cek[3] == 'n';
        } else {
            return false;
        }
    }
    
    public boolean isIff(String x) {
        //memeriksa apakah string merupakan operator iff
        char[] cek = x.toCharArray();
        if (cek.length == 3) {
            return cek[0] == 'i' && cek[1] 
                    == 'f' && cek[2] == 'f';
        } else {
            return false;
        }
    }
    
    public boolean isBuka(String x) {
        //memeriksa apakah string merupakan grouping
        char[] cek = x.toCharArray();
        return cek[0] == '(';
    }
    
    public boolean isTutup(String x) {
        //memeriksa apakah string merupakan grouping
        char[] cek = x.toCharArray();
        return cek[0] == ')';
    }
    
    public void prosesToken() {
        //memproses token dari setiap kata
        String cek;
        char[] x;
        this.getSuku();
        for (int i = 0; i < kata.size(); i++) {
            cek = kata.get(i);
            if (isOperand(cek))
                token.add("1");
            else if (isNot(cek))
                token.add("2");
            else if (isAnd(cek))
                token.add("3");
            else if (isOr(cek))
                token.add("4");
            else if (isXor(cek))
                token.add("5");
            else if (isIf(cek))
                token.add("6");
            else if (isThen(cek))
                token.add("7");
            else if (isIff(cek))
                token.add("8");
            else if (isBuka(cek))
                token.add("9");
            else if (isTutup(cek))
                token.add("10");
            else 
                token.add("error");
        }
    }
    
    public void validation() {
        Validator validate = new Validator();
        System.out.print("Output: ");
        if (validate.cekAll(token) == true)
            System.out.println("VALID");
        else
            System.out.println("TIDAK VALID ");
        System.out.println("no error " + validate.isNoError(token));
        System.out.println("panjang " + validate.panjang(token));
        System.out.println("operator " + validate.posisiOperator(token));
        System.out.println("operand " + validate.cekOperand(token));
        System.out.println("grouping " + validate.isGrouping(token));
    }
    
    public void showKata() {
        //this.getSuku();
        System.out.println("Kata: ");
        int j = 0;
        for (String i:kata) {
            System.out.println(i + " " + j);
            j++;
        }
    }
    
    public void showToken() {
        System.out.print("Token: ");
        for (String i:token) {
            if(i == "error") {
                System.out.print("error");
                break;
            }
            System.out.print(i + " ");
        }
        System.out.println("");
    }
}