package tools;

import java.util.Scanner;

public class MyToys {

    public static Scanner sc = new Scanner(System.in);
    
    public static boolean getChoose(String inputMsg, String opOne, String opTwo){
        String choose;
        while (true) {            
            System.err.print(inputMsg);
            choose = (sc.nextLine().trim());
            if (choose.equalsIgnoreCase(opOne))
                return true;
            else if (choose.equalsIgnoreCase(opTwo))
                return false;
            else System.err.println("Enter again!!\nChoose (" + opOne +" or " + opTwo + ")");
        }
    }

    public static int getAnInterger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int getAnInteger(String inputMsg, String errorMsg, int minValue, int maxValue) {
        int n;
        //swap
        if (minValue > maxValue) {
            int t = minValue;
            minValue = maxValue;
            maxValue = t;
        }

        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < minValue || n > maxValue) {
                    throw new Exception();
                } else {
                    return n;
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }

        }
    }

    public static double getDouble(String inputMsg, String errorMsg) {
        double n;

        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getDouble(String inputMsg, String errorMsg, double minValue, double maxValue) {
        double n;
        if (minValue > maxValue) {
            double t = minValue;
            minValue = maxValue;
            maxValue = t;
        }

        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < minValue || n > maxValue) {
                    throw new Exception();
                } else {
                    return n;
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getStringbyFormat(String inputMsg, String errorMsg, String format) {
        String id;

        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            if (!id.matches(format)) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        String id;

        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            if (id.length() == 0 || id.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }
    
    public static String toUpperFirstLetter(String input){
        String result = "";
        String arr[] = input.split("\\s+");
        for (String x : arr) {
            result += toUpperOneFirstLetter(x) + " ";
        }
        return result.trim();
    }
    
    private static String toUpperOneFirstLetter(String input){
        return input.substring(0,1).toUpperCase() + input.substring(1);
    }

}
