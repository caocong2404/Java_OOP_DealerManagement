
package data;

import java.io.IOException;
import java.util.List;
import tools.MyToysFile;


public class AccountChecker {
    
    private String accFile;
    private static String SEPARATOR = ",";
    public AccountChecker() throws IOException{
        setupAccFile();
    }
    
    private void setupAccFile() throws IOException{
        Config cR = new Config();
        accFile = cR.getAccountFile();   
    }
    
    public Account check(Account acc) throws IOException{
        List<String> lines = MyToysFile.readLineFromFile(accFile);
        
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            if(parts.length < 3) return null;
            if (parts[0].equalsIgnoreCase(acc.getAccName()) &&
                parts[1].equalsIgnoreCase(acc.getPwd()))
                return (new Account(parts[0].trim(), parts[1].trim(), parts[2].trim()));
        }
        return null;
    }
    
    
    public boolean checkAccount(String userName) throws IOException{
        List<String> lines = MyToysFile.readLineFromFile(accFile);
        
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            if(parts.length < 3) return false;
            if (parts[0].equalsIgnoreCase(userName))
                return true;
        }
        return false;
    }
    
}
