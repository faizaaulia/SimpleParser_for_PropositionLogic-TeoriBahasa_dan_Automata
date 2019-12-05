package tubes;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    
    public boolean isOperator(String token) {
        //memeriksa apakh token merupakan operator
        return "2".equals(token) || "3".equals(token) || "4".equals(token) || 
                "5".equals(token) || "6".equals(token) || "7".equals(token);
    }
    
    public boolean isOperand(String token) {
        //memeriksa apakh token merupakan operand
        return "p".equals(token) || "q".equals(token) || "r".equals(token)
                || "s".equals(token);
    }
    
    public boolean groupingPosition(List<String> token) {
        //memeriksa posisi grouping
        int a = 0,b = 0;
        //mencari kurng buka atau kurung tutup
        for (int i = 0; i < token.size(); i++) {
            //jika ditemukan kurung buka
            if ("9".equals(token.get(i))) {
                a = 9;
                //cari kurung tutup
                for (int j = 0; j < token.size(); j++) {
                    if ("10".equals(token.get(j))) {
                        b = 10;
                        break;
                    }
                }
            } 
            //jika ditemukan kurung buka
            else if ("10".equals(token.get(i))) {
                a = 10;
                //cari kurung buka
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
    
    public boolean cekKurung(List<String> token) {
        List<Integer> arr = new ArrayList<>(); 
        int i = 0;
        boolean cek = true;
        for (int j = 0; j < token.size(); j++) {
            //memasukkan kurung buka/tutup ke arr
            if ("9".equals(token.get(j)) || "10".equals(token.get(j))) {
                arr.add(j);
                i++;
                //jika ditemukan kurung buka dan operator setelah kurung buka
                if (j != 0 && j != token.size()-1 &&
                        isOperatorBanding(token.get(j+1)) &&
                        "9".equals(token.get(j))) {
                    cek = false;
                    break;
                }
            }
        }
        for (int j = 0; j < arr.size(); j++) {
            if (j != arr.size()-1) {
                //jika ditemukan kurung buka setela kurung tutup
                if (arr.get(j) - arr.get(j+1) == 1){
                    cek = false;
                    break;
                }
            }
        }
        return cek;
    }
    
    public boolean isGrouping(List<String> token) {
        boolean cek = true;
        int countBuka = 0,countTutup = 0;
        //hitung jumlah kurung buka dan kurung tutup
        for (String i:token) {
            if (i.equals("9"))
                countBuka ++;
            else if (i.equals("10"))
                countTutup++;
        }
//        System.out.println(cek = countBuka == countTutup);
//        System.out.println(groupingPosition(token));
//        System.out.println(cekKurung(token));
        return (cek = countBuka == countTutup) && groupingPosition(token) && cekKurung(token);
    }
    
    public boolean posisiOperator(List<String> token) {
        int p = token.size();
        boolean cek1 = true, cek2 = true;
        for (int i = 0; i < token.size(); i++) {
            //jika ditemukan iff
            if ("8".equals(token.get(i))) {
                //jika sebelum iff bukan operand
                if (i > 0 && token.get(i-1) != "1" ) {
                    cek2 = false;
                    break;
                }
            } 
            //jika ditemukan operator di akhir formula
            else if (isOperator(token.get(i)) && i == token.size()-1) {
                cek2 = false;
                break;
            }
        }
        //jika ditemukan operator di awal/akhir atau di dua token awal
        if (isOperator(token.get(0)) || isOperator(token.get(p-1)) || 
                "8".equals(token.get(0)) || "8".equals(token.get(p-1)) || 
                isOperator(token.get(0)) && isOperator(token.get(1)) ||
                "10".equals(token.get(p-1)) && isOperator(token.get(p-2)))
            cek1 = false;
        return cek1 && cek2;
    }
    
    //memerika token merupakan operator yg membutuhkan pembanding
    public boolean isOperatorBanding(String token) {
        return "3".equals(token) || "4".equals(token) || "5".equals(token)
                || "7".equals(token) || "8".equals(token);
    }
    
    //memeriksa token merupakan operand
    public boolean cekOperand(List<String> token) {
        List<Integer> arr = new ArrayList<>(); 
        int i = 0;
        boolean cek = true;
        //System.out.println("Token Size: " + token.size());
        for (int j = 0; j < token.size(); j++) {
            //jika token merupakan operand dimasukkan ke arr
            if ("1".equals(token.get(j))) {
                arr.add(j);
                i++;
            }
        }
        for (int j = 0; j < arr.size(); j++) {
            if (j != arr.size()-1) {
                //jika operand bertemu dengan operand
                if (arr.get(j) - arr.get(j+1) == -1)
                    cek = false;
            }
        }
        return cek;
    }
    
    //memeriksa panjang token
    public boolean panjang(List<String> token) {
        boolean cek = true;
        //jika panjang token hanya 1
        if (token.size() == 1) 
            //jika token merupakan operator
            if (isOperator(token.get(0)))
                cek = false;
        return cek;
    }
    
    //memeriksa ditemukan token error atau tidak
    public boolean isNoError(List<String> token) {
        boolean cek = true;
        for(String s:token) {
            //jika ditemukan token error
            if (s == "error")
                cek = false;
        }
        return cek;
    }
    
    //cek semua kondisi di atas
    public boolean cekAll(List<String> token) {
        return isNoError(token) && panjang(token) && posisiOperator(token) && isGrouping(token)
                && cekOperand(token);
    }
}