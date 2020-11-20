package pl.polsl.lab.homefinances.patryk.lipka.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class stores data about Member and his list of transactions
 *
 * @author Patryk Lipka
 * @version 1.0
 */
public class Member {
    private String name;
    private List<Receipt> receiptList;

    /**
     * Constructor of Member
     *
     * @param name name of Member we want to create
     */
    public Member(String name) {
        this.name = name;
        this.receiptList = new ArrayList<Receipt>();
    }

    /**
     * Method allows us to get name of Member
     *
     * @return method returns name of Member
     */
    public String getName() {
        return name;
    }

    /**
     * Method allows us to get list of Receipts connected to Member
     *
     * @return method returns list of Receipts connected to Member
     */
    public List<Receipt> getReceiptList() {
        return receiptList;
    }
}
