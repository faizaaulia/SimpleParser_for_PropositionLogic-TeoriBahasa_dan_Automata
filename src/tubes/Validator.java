package tubes;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    
    public boolean isOperator(String token) {
        return "2".equals(token) || "3".equals(token) || "4".equals(token) || 
                "5".equals(token) || "6".equals(token) || "7".equals(token);
    }
    
    public boolean groupingPosition(List<String> token) {
        int a = 0,b = 0;
        for (int i = 0; i < token.size(); i++) {
            if ("9".equals(token.get(i))) {
                a = 9;
                for (int j = 0; j < token.size(); j++) {
                    if ("10".equals(token.get(j))) {
                        b = 10;
                        break;
                    }
                }
            } else if ("10".equals(token.get(i))) {
                a = 10;
                for (int j = 0; j < token.size(); j++) {
                    if ("9".equals(token.get(j))) {
                        b = 9;
                        break;
                    }
                }
            }
            break;
        }
        return a < b || (a == 0 && b == 0);
    }
    
    public boolean isGrouping(List<String> token) {
        boolean cek = true;
        int countBuka = 0,countTutup = 0;
        for (String i:token) {
            if (i.equals("9"))
                countBuka ++;
            else if (i.equals("10"))
                countTutup++;
        }
        return (cek = countBuka == countTutup) && groupingPosition(token) && cekKurung(token);
    }
    
    public boolean posisiOperator(List<String> token) {
        int p = token.size();
        boolean cek1 = true, cek2 = true;
        for (int i = 0; i < token.size(); i++) {
            if ("8".equals(token.get(i))) {
                if (i > 0 && token.get(i-1) != "1") {
                    cek2 = false;
                    break;
                }
            }
        }
        if (isOperator(token.get(0)) && isOperator(token.get(p-1)) || 
                "8".equals(token.get(0)) || "8".equals(token.get(p-1)) || 
                isOperator(token.get(0)) && isOperator(token.get(1)) ||
                "10".equals(token.get(p-1)) && isOperator(token.get(p-2)))
            cek1 = false;
        return cek1 && cek2;
    }
    
    public boolean cekKurung(List<String> token) {
        List<Integer> arr = new ArrayList<>(); 
        int i = 0;
        boolean cek = true;
        for (int j = 0; j < token.size(); j++) {
            if ("9".equals(token.get(j)) || "10".equals(token.get(j))) {
                arr.add(j);
                i++;
            }
        }
        for (int j = 0; j < arr.size(); j++) {
            if (j != arr.size()-1) {
                if (arr.get(j) - arr.get(j+1) == -1)
                    cek = false;
            }
        }
        return cek;
    }
    
    public boolean cekOperand(List<String> token) {
        List<Integer> arr = new ArrayList<>(); 
        int i = 0;
        boolean cek = true;
        //System.out.println("Token Size: " + token.size());
        for (int j = 0; j < token.size(); j++) {
            if ("1".equals(token.get(j))) {
                arr.add(j);
                i++;
            }
        }
        for (int j = 0; j < arr.size(); j++) {
            if (j != arr.size()-1) {
                if (arr.get(j) - arr.get(j+1) == -1)
                    cek = false;
            }
        }
        return cek;
    }
    
    public boolean panjang(List<String> token) {
        boolean cek = true;
        if (token.size() == 1) 
            if (isOperator(token.get(0)) || "1".equals(token.get(0)))
                cek = false;
        return cek;
    }
    
    public boolean isNoError(List<String> token) {
        boolean cek = true;
        for(String s:token) {
            if (s == "error")
                cek = false;
        }
        return cek;
    }
    
    public boolean cekAll(List<String> token) {
        return isNoError(token) && panjang(token) && posisiOperator(token) && isGrouping(token)
                && cekOperand(token);
    }
}