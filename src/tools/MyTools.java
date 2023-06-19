
package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class MyTools {
    
    public static final Scanner SC = new Scanner(System.in);
    
    public static boolean validPassword (String str, int minLen){
        if (str.length() < minLen) return false;
        return  str.matches("\".*[a-zA-Z]+.*\"") &&
                str.matches("\".*[\\\\d]+.*\"") &&
                str.matches(".*[\\W]+.*");
    }
    
    public static Date parseDate(String dateStr, String dateFormat){
        SimpleDateFormat dF = (SimpleDateFormat)SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static boolean parseBool(String boolStr){
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

}
