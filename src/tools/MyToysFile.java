
package tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class MyToysFile {
    
    public static List<String> readLineFromFile(String fileName) throws FileNotFoundException, IOException{
        List<String> list = new ArrayList<>();
        FileReader fr = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(fr);

        String line;

        while ((line = bf.readLine()) != null) {
            list.add(line);
        }
        return list;
    }
    
    public boolean writeLineFromFile(String fileName, List list) {
        
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < list.size(); i++) {
                pw.println(list.get(i));
            }
            pw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
        return false;
    }
    
    
    
    
}
