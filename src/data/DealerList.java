package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import mng.LogIn;
import tools.MyTools;
import tools.MyToys;
import tools.MyToysUpdate;

public class DealerList extends ArrayList<Dealer> implements dealerDAO {
    
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private static final String dataFile = "dealers.txt";
    
    static Scanner sc = MyToys.sc;
    LogIn loginObj = null;
    
    boolean changed = false;

    public DealerList(LogIn loginObj) {
        this.loginObj = loginObj;
    }

    public void addDealer() {
        try {

            String DealerID, DealerName, DealerAddr, DealerPhone;
            boolean continuing = true;

            do {
                //Product ID 5 character va khong dc trunhg
                DealerID = MyToys.getStringbyFormat("Input Dealer ID: ", "The Dealer ID must be 4 character and no spaces", "[\\S]{4}$");
                DealerID = DealerID.toUpperCase();
                if (checkDupplicateByID(DealerID) || checkDupplicatedByIDFromFile(DealerID, dataFile)) {
                    System.err.println(DealerID + " is exist");
                }
            } while (checkDupplicateByID(DealerID) || checkDupplicatedByIDFromFile(DealerID, dataFile));

            //name
            DealerName = MyToys.getString("Input Dealer Name: ", "The dealer name must not empty");
            DealerName = MyToys.toUpperFirstLetter(DealerName);
            DealerAddr = MyToys.getString("Input Address: ", "The address must not empty");
            
            do {
                //Product ID 5 character va khong dc trunhg
                DealerPhone = MyToys.getStringbyFormat("Input phone number: ", "The phone number must be 9 or 11 number", PHONEPATTERN);
                if (checkDupplicateByPhoneNumber(DealerPhone)) {
                    System.err.println(DealerPhone + " is exist");
                }
            } while (checkDupplicateByPhoneNumber(DealerPhone));
            
            this.add(new Dealer(DealerID, DealerName, DealerAddr, DealerPhone, continuing));
            System.err.println("Create successfully");
            changed = true;

            if (goBackMenu()) {
                addDealer();
            } else {
                System.err.println("Exit");
            }
        } catch (Exception e) {
        }

    }

    @Override
    public void printAllDealers() {
        if (this.isEmpty()) {
            System.err.println("Have no any Dealer");
            return;
        }

        System.out.println("Here the list:");
        System.out.println("Dealer ID |Dealer Name |Address             |Phone Number   |Continuing");
        for (Dealer x : this) {
            x.showProfile();
        }

        if (goBackMenu()) {
            printAllDealers();
        } else {
            System.err.println("Exit");
        }
    }


    public void printDealerFromFile() {
        if (this.isEmpty()) {
            System.err.println("Have no any Dealer");
            return;
        }

        Collections.sort(this, new Comparator<Dealer>() {
            @Override
            public int compare(Dealer o1, Dealer o2) {
                int result = o1.getID().compareToIgnoreCase(o2.getID());
                if (result == 0) {
                    return (int) (o1.getName().compareToIgnoreCase(o2.getName()));
                }
                return result;
            }
        });

        System.out.println("Here the list:");
        System.out.println("Dealer ID |Dealer Name |Address             |Phone Number   |Continuing");
        for (Dealer x : this) {
            x.showProfile();
        }

    }

//------------------------------------------------------------------------------
    //check trong file
    public void checkDealerExistV2() throws IOException {

        ArrayList<Dealer> dealerList = new ArrayList<>();
        dealerList = readFromFilel(dataFile);

        boolean check = true;
        String checkDealerName;
        checkDealerName = MyToys.getString("Input Dealer Name: ", "The Dealer Name must be not empty!!");

        if (checkDupplicateByName(checkDealerName, dealerList)) {
            System.err.println("Exist Dealer " + checkDealerName);
        } else {
            System.err.println("No Dealer Found!");
            check = false;
        }

        if (goBackMenu()) {
            checkDealerExistV2();
        } else {
            System.err.println("Exit");
        }
    }

    private boolean checkDupplicateByName(String s, ArrayList<Dealer> p) {
        for (Dealer x : p) {
            if (x.getName().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
//--------------------------------------------------------------------------------

    public boolean checkDupplicateByID(String s) {
        for (Dealer x : this) {
            if (x.getID().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkDupplicateByPhoneNumber(String s) {
        for (Dealer x : this) {
            if (x.getPhone().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDupplicateByName(String s) {
        for (Dealer x : this) {
            if (x.getName().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    public void searchDealerByPartOfName() {
        if (this.isEmpty()) {
            System.err.println("Have no any Dealer");
            return;
        }

        String searchName;
        searchName = MyToys.getString("Input Dealer Name to search: ", "Empty!!");
        int pos = searchDealerByPartOfName(searchName);
        if (pos >= 0) {
            System.out.println("FOUND!!");
            for (Dealer x : this) {
                if ((x.getName().toUpperCase()).contains(searchName.toUpperCase())) {
                    x.showProfile();
                }
            }
        } else {
            System.err.println("NOT FOUND!!");
        }

        if (goBackMenu()) {
            searchDealerByPartOfName();
        } else {
            System.err.println("Exit");
        }
    }

    public int searchDealerByPartOfName(String searchName) {
        for (int i = 0; i < this.size(); i++) {
            if ((this.get(i).getName().toUpperCase()).contains(searchName.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }
    
    
    

    public int searchDealerByID(String searchID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equalsIgnoreCase(searchID)) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealerByID() {
        if (this.isEmpty()) {
            System.err.println("Have no any Dealer");
            return;
        }

        String searchID;
        searchID = MyToys.getString("Input Dealer ID to search: ", "Empty!!");
        int pos = searchDealerByID(searchID);
        if (pos >= 0) {
            System.out.println("FOUND!!");
            for (Dealer x : this) {
                if (x.getID().equalsIgnoreCase(searchID)) {
                    x.showProfile();
                }
            }
        } else {
            System.err.println("NOT FOUND!!");
        }

        if (goBackMenu()) {
            searchDealerByID();
        } else {
            System.err.println("Exit");
        }
    }

    public void updateDealer() {
        String updateID;

        String updateName;
        String updateAddr;
        String updatePhone;
        boolean updateContinuing;

        updateID = MyToys.getStringbyFormat("Input Dealer ID you want to update: ", "The Dealer ID must be four character and no spaces", "[\\S]{4}$");
        int pos = searchDealerByID(updateID);

        if (pos >= 0) {

            System.out.print("Old Dealer name: " + this.get(pos).getName() + " | New name: ");
            updateName = sc.nextLine().trim();
            if (updateName.length() > 0) {
                updateName = MyToys.toUpperFirstLetter(updateName);
                this.get(pos).setName(updateName);
            }

            System.out.print("Old address: " + this.get(pos).getAddr() + " | New address: ");
            updateAddr = sc.nextLine().trim();
            if (updateAddr.length() > 0) {
                this.get(pos).setAddr(updateAddr);
            }
            //

            updatePhone = MyToysUpdate.updateString("Old Phone Number: " + this.get(pos).getPhone() + " | New Phone Number: ", "The Phone Number must be 9 or 11 digits", PHONEPATTERN);
            if (MyToysUpdate.checkStringUpdate(updatePhone)) {
                this.get(pos).setPhone(updatePhone);
            }

            System.out.println("    Continuing:");
            System.out.println("    0. Not Available");
            System.out.println("    1. Available");
            System.out.println("    2. No update");

            int choice = MyToys.getAnInteger("Your choose: ", "Please choose 0 to 2\n", 0, 2);
            if (choice == 0) {
                updateContinuing = false;;
            } else if (choice == 1) {
                updateContinuing = true;
            } else {
                updateContinuing = this.get(pos).isContinuing();
            }

            this.get(pos).setContinuing(updateContinuing);
            System.err.println("Update Successfully!!");
            changed = true;
        } else {
            System.err.println("Dealer ID not found!!!");
            return;
        }
    }

    public void removeDealer() {
        String deleteID;

        deleteID = MyToys.getStringbyFormat("Input Dealer ID you want to delete: ", "The Dealer ID must be 4 character and no spaces", "[\\S]{4}$");
        int pos = searchDealerByID(deleteID);

        if (pos >= 0) {
            this.remove(pos);
            System.err.println("Delete Success!!");
        } else {
            System.err.println("Delete Fail!!");
            return;
        }
    }

    public boolean goBackMenu() {
        boolean choice = MyToys.getChoose("Do you want to continue(Y/N): ", "Y", "N");
        if (choice) {
            return true;
        }
        return false;
    }

    private Dealer createDealer(String line) {
        StringTokenizer stk = new StringTokenizer(line, ",");
        String IDLine = stk.nextToken().trim();
        String NameLine = stk.nextToken().trim();
        String AddressLine = stk.nextToken().trim();
        String PhoneNumberLine = stk.nextToken().trim();
        boolean ContinuingLine = MyTools.parseBool(stk.nextToken().trim());
        return (new Dealer(IDLine, NameLine, AddressLine, PhoneNumberLine, ContinuingLine));
    }

    private ArrayList<Dealer> readFromFilel(String fileName) throws IOException {
        ArrayList<Dealer> dealerList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        String line;

        try {
            fr = new FileReader(dataFile);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                dealerList.add(createDealer(line));
            }
        } catch (Exception e) {
        }
        return dealerList;
    }

    private void loadDealerFromFile() {
        FileReader fr = null;
        BufferedReader br = null;
        String line;

        try {
            fr = new FileReader(dataFile);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                this.add(createDealer(line));
            }
            fr.close();
            br.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void initWithFile() {
        try {
            loadDealerFromFile();
        } catch (Exception e) {
            System.err.println("initWithFile error");
        }

    }

    private boolean checkDupplicatedByIDFromFile(String checkID, String fileName) throws IOException {

        ArrayList<Dealer> listProduct = readFromFilel(fileName);
        for (Dealer x : listProduct) {
            if (x.getID().equalsIgnoreCase(checkID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DealerList getContinuingList() {
        DealerList list = new DealerList(loginObj);
        for (Dealer x : this) {
            if (x.isContinuing()) {
                list.add(x);
            }
        }
        return list;
    }

    @Override
    public DealerList getUnContinuingList() {
        DealerList list = new DealerList(loginObj);
        for (Dealer x : this) {
            if (!x.isContinuing()) {
                list.add(x);
            }
        }
        return list;
    }

    @Override
    public void printContinuingDealers() {
        DealerList list = getContinuingList();
        if (list.isEmpty()) {
            System.err.println("No Continuing Dealers");
            return;
        }
        System.out.println("Dealer ID |Dealer Name |Address             |Phone Number   |Continuing");
        System.out.println("Continuing Dealers List:");
        for (Dealer x : list) {
            x.showProfile();
        }
    }

    @Override
    public void printUnContinuingDealers() {
        DealerList list = getUnContinuingList();
        if (list.isEmpty()) {
            System.err.println("No UnContinuing Dealers");
            return;
        }

        System.out.println("Dealer ID |Dealer Name |Address             |Phone Number   |Continuing");
        System.out.println("UnContinuing Dealers List:");
        for (Dealer x : list) {
            x.showProfile();
        }
    }

    @Override
    public void writeDealerToFile() {
        if (changed) {
            FileWriter fw = null;
            PrintWriter pw = null;
            try {
                fw = new FileWriter(dataFile);
                pw = new PrintWriter(fw);

                for (Dealer x : this) {
                    pw.println(x);
                }
                changed = false;
                System.out.println("Write to file complete!!");
                pw.close();
                fw.close();
            } catch (Exception e) {
            }
        }
    }

    public boolean isChanged() {
        if (changed == true) {
            return true;
        }
        return false;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public void sortList() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        }

        System.out.println("------======(Sort Type)======------");
        System.out.println("    1   -   Sort by Dealer ID.");
        System.out.println("    2   -   Sort by Dealer Name.");
        System.out.println("    3   -   Sort by Dealer Address.");
        System.out.println("    4   -   Sort by Dealer Phone Number.");
        System.out.println("    5   -   Quit to menu.");

        int choice = MyToys.getAnInteger("Your choose: ", "Please choose 1...5\n", 1, 5);
        if (choice == 1) {
            Collections.sort(this, new Comparator<Dealer>() {
                @Override
                public int compare(Dealer o1, Dealer o2) {
                    return o1.getID().compareToIgnoreCase(o2.getID());
                }
            });
        } else if (choice == 2) {
            Collections.sort(this, new Comparator<Dealer>() {
                @Override
                public int compare(Dealer o1, Dealer o2) {
                    return o1.getName().compareToIgnoreCase(o2.getName());

                }
            });
        } else if (choice == 3) {
            Collections.sort(this, new Comparator<Dealer>() {
                @Override
                public int compare(Dealer o1, Dealer o2) {
                    return o1.getAddr().compareToIgnoreCase(o2.getAddr());

                }
            });
        } else if (choice == 4) {
            Collections.sort(this, new Comparator<Dealer>() {
                @Override
                public int compare(Dealer o1, Dealer o2) {
                    return o1.getPhone().compareToIgnoreCase(o2.getPhone());
                }
            });
        } else {
            System.err.println("Exit");
            return;
        }

        System.err.println("Sort complete!!");
    }

}
