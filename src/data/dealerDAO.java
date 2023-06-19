
package data;


public interface dealerDAO {
    public void initWithFile();
    public DealerList getContinuingList();
    public DealerList getUnContinuingList();
    public void searchDealerByID();
    public void addDealer();
    public void removeDealer();
    public void updateDealer();
    public void printAllDealers();
    public void printContinuingDealers();
    public void printUnContinuingDealers();
    public void writeDealerToFile();
}
