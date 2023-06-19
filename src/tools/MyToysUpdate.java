package tools;

import java.util.Scanner;

public class MyToysUpdate {

    private static Scanner sc = new Scanner(System.in);

    public static String updateString(String inputMsg, String errorMsg, String format) {
        String input;

        do {
            System.out.print(inputMsg);
            input = sc.nextLine();

            if (input.equals("")) {
                return "none";
            }

        } while (!getStringbyFormat(input, errorMsg, format));

        return input;
    }


    public static int updateInteger(String inputMsg, String errorMsg, int minValue, int maxValue) {
        String input;
        int update;
        boolean check = true;

        do {
            System.out.print(inputMsg);
            input = sc.nextLine();

            if ((input + "").equals("")) {
                return -1;
            }

            if (checkIsNumber(input)) {
                update = Integer.parseInt(input);
                if (getAnInteger(update, errorMsg, minValue, maxValue)) 
                    return update;  
                else
                    System.out.println(errorMsg);
            } else 
                System.out.println(errorMsg);
            
        } while (check);
        return -1;
    }
    
    public static double updateDouble(String inputMsg, String errorMsg, int minValue, int maxValue) {
        String input;
        double update;
        boolean check = true;

        do {
            System.out.print(inputMsg);
            input = sc.nextLine();

            if ((input + "").equals("")) {
                return -1;
            }

            if (checkIsNumber(input)) {
                update = Double.parseDouble(input);
                if (getAnDouble(update, errorMsg, minValue, maxValue)) 
                    return update;  
                else
                    System.out.println(errorMsg);
            } else 
                System.out.println(errorMsg);
            
        } while (check);
        return -1;
    }
    
    private static boolean getAnDouble(double n, String errorMsg, int minValue, int maxValue) {
        //swap
        if (minValue > maxValue) {
            int t = minValue;
            minValue = maxValue;
            maxValue = t;
        }
        if (n < minValue || n > maxValue) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean getAnInteger(int n, String errorMsg, int minValue, int maxValue) {
        //swap
        if (minValue > maxValue) {
            int t = minValue;
            minValue = maxValue;
            maxValue = t;
        }
        if (n < minValue || n > maxValue) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkIsNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if(Character.isLetter(s.charAt(i)))
            return false;
        }
        return true;
    }

    public static boolean checkIntegerUpdate(int n) {
        if (n == -1) {
            return false;
        }
        return true;
    }
    
    public static boolean checkDoubleUpdate(double n) {
        if (n == -1) {
            return false;
        }
        return true;
    }

    public static boolean checkStringUpdate(String s) {
        if (s.equals("none")) {
            return false;
        }
        return true;
    }

    private static boolean getStringbyFormat(String id, String errorMsg, String format) {
        if (id.matches(format)) {
            return true;
        } else {
            System.out.println(errorMsg);
            return false;
        }
    }
    

}

