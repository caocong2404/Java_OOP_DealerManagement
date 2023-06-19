package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tools.MyToysFile;

public class Config {

    private static final String CONFIG_FILE = "config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;

    public Config() {
        readData();
    }

    public String getAccountFile() {
        return accountFile;
    }

    public String getDealerFile() {
        return dealerFile;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }

    private void readData() {
        List<String> lines = null;
        try {
            lines = MyToysFile.readLineFromFile(CONFIG_FILE);//readfile;
            for (String line : lines) {
                line = line.toUpperCase();
                String[] parts = line.split(":");

                if (line.indexOf("ACCOUN") >= 0) {
                    accountFile = parts[1].trim();
                } else if (line.indexOf("DEAL") >= 0) {
                    dealerFile = parts[1].trim();
                } else if (line.indexOf("DELIVERY") >= 0) {
                    deliveryFile = parts[1].trim();
                }
            }
        } catch (Exception e) {
            System.err.println("CONFIG ERROR");
        }
    }

}
