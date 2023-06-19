package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import tools.MyTools;
import tools.MyToys;
import tools.MyToysFile;

public class LogIn {

    private static final String FILE_NAME = "accounts.txt";
    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public Account getAcc() {
        return acc;
    }
    

    //---------------------------------------//
    
    
    public static void main(String[] args) throws IOException {
        Account acc = null, newAcc = null;
        boolean cont = true, valid = false;
        
        System.out.println("");
        int choice = 0;

        do {
            choice = mainScreen();
            if (choice == 2) {
                System.out.println("--==(CREATE NEW ACCOUNT)==--");
                newAcc = signIn();
                if (writeAccountFromFile(FILE_NAME, newAcc)) {
                    System.out.println("Create account success\n");
                } else {
                    System.out.println("Create account fail\n");
                }
            } else if (choice == 1) {
                do {
                    AccountChecker accChk = new AccountChecker();
                    acc = LogIn();
                    acc = accChk.check(acc);
                    if (acc == null) {
                        cont = MyToys.getChoose("Invalid account - Try again? (Y/N): ", "Y", "N");
                    }
                    if (acc == null && !cont) {
                        System.out.println("QUIT!");
                        System.exit(0);
                    }
                } while (acc == null && cont);

                LogIn loginObj = new LogIn(acc);
                if (acc.getRole().equalsIgnoreCase("ACC-1")) {
                    System.out.println("ACC-1 account");
                    System.out.println("LOGIN PROGRAM PROCESS");
                    System.out.println("Hello " + acc.getAccName() + ":");
                    Menu menu = new Menu("Dealer Management Program");
                    menu.addOption("    1.     Add new dealer");
                    menu.addOption("    2.     Search a dealer");
                    menu.addOption("    3.     Remove a dealer");
                    menu.addOption("    4.     Update a dealer");
                    menu.addOption("    5      Sort dealers list(new)");
                    menu.addOption("    6.     Print all dealers");
                    menu.addOption("    7.     Print continuing dealers");
                    menu.addOption("    8.     Print UN-Continuing dealers");
                    menu.addOption("    9.     Write to file");
                    menu.addOption("    Other. Exit");

                    DealerList dealerList = new DealerList(loginObj);
                    dealerList.initWithFile();

                    choice = 0;
                    do {
                        menu.printMenu();
                        choice = MyToys.getAnInterger("    Input your choice: ", "Your choice must be a number!!!");

                        switch (choice) {

                            case 1:
                                dealerList.addDealer();
                                break;
                            case 2:
                                dealerList.searchDealerByID();
                                break;
                            case 3:
                                dealerList.removeDealer();
                                break;
                            case 4:
                                dealerList.updateDealer();
                                break;
                            case 5:
                                dealerList.sortList();
                                break;
                            case 6:
                                dealerList.printAllDealers();
                                break;
                            case 7:
                                dealerList.printContinuingDealers();
                                break;
                            case 8:
                                dealerList.printUnContinuingDealers();
                                break;
                            case 9:
                                dealerList.writeDealerToFile();
                                break;
                            case 10:
                                dealerList.searchDealerByPartOfName();
                                break;
                            default:
                                if (dealerList.isChanged()) {
                                    boolean res = MyTools.parseBool("Data changed. Write to file?");
                                    if (res == true) {
                                        dealerList.writeDealerToFile();
                                    } else {
                                        System.out.println("Goodbye, see you soon!");
                                    }
                                }
                        }
                    } while (choice >= 0 && choice <= 10);
                } else if (acc.getRole().equalsIgnoreCase("ACC-2")) {
                    System.out.println("Deliveries is on process...");
                } else {
                    System.out.println("New update will comming soon <3");
                }
            } else {
                System.err.println("QUIT");
            }

        } while (choice != 3);

    }

    public static int mainScreen() {
        System.out.println("--==MAIN SCREEN==--");
        System.out.println("1.      LOGIN IN");
        System.out.println("2.      SIGN IN");
        System.out.println("3.      QUIT");
        int choice = MyToys.getAnInteger("Your choice: ", "Please choose 1 to 3", 1, 3);
        return choice;
    }

    public static Account LogIn() {
        String accName, pwd;
        accName = MyToys.getStringbyFormat("UserName: ", "UserName must be at least 5 character and no space", "[\\S]{5,}$");
        pwd = MyToys.getStringbyFormat("Password: ", "Password must be at least 5 character and no space", "[\\S]{5,}$");

        System.out.println();

        return new Account(accName, pwd);
    }

    public static Account signIn() throws IOException {
        String accName, pwd, role;
        AccountChecker accChk = new AccountChecker();
        do {
            accName = MyToys.getStringbyFormat("UserName: ", "UserName must be at least 5 character and no space", "[\\S]{5,}$");
            
            if (accChk.checkAccount(accName))
            System.out.println(accName + " is existed\n");
        } while (accChk.checkAccount(accName));
        pwd = MyToys.getStringbyFormat("Password: ", "Password must be at least 5 character and no space", "[\\S]{5,}$");

        System.out.println("Get Role");
        System.out.println("1       BOSS");
        System.out.println("2.      ACC-0");
        System.out.println("3.      ACC-1");
        int choice = MyToys.getAnInteger("Your choice: ", "Choose in range 1...3", 1, 3);
        if (choice == 1) {
            role = "BOSS";
        } else if (choice == 2) {
            role = "ACC-0";
        } else {
            role = "ACC-1";
        }

        return (new Account(accName, pwd, role));
    }

    public static boolean writeAccountFromFile(String fileName, Account newAcc) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter(fileName, true);
            pw = new PrintWriter(fw);

            pw.println(newAcc);

            fw.close();
            pw.close();
            return true;
        } catch (Exception e) {
            System.out.println("errr");
            return false;
        }
    }

}
