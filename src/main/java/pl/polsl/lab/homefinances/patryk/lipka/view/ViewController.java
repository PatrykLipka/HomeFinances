package pl.polsl.lab.homefinances.patryk.lipka.view;

/**
 * Class allows us to print data
 *
 * @author Patryk Lipka
 * @version 1.0
 */

public class ViewController {

    /**
     * Method allows us to print a Member name
     *
     * @param memberName name of Member which we print
     */
    public void printMember(String memberName) {
        System.out.println("Member name: " + memberName);
    }

    /**
     * Method allows us to print data of Receipt
     *
     * @param receiptNumber number of Receipt we want to print data of
     * @param shopName      name of shop to print
     * @param dateString    date of Receipt to print
     * @param value         value of Receipt to print
     */
    public void printReceiptData(int receiptNumber, String shopName, String dateString, double value) {
        System.out.println("\nReceipt number: " + receiptNumber +
                "\nShop name: " + shopName +
                "\nDate: " + dateString +
                "\nValue: " + value);
    }

    /**
     * Method allows us to print data of Product
     *
     * @param productName  name of Product to print
     * @param productPrice price of Product to print
     */
    public void printProduct(String productName, double productPrice) {
        System.out.println("    Product name: " + productName +
                "\n    Product price: " + productPrice);
    }

    /**
     * Method prints log messages
     *
     * @param message message to print
     */
    public void printLogMessage(String message) {
        System.out.println(message);
    }

    /**
     * Method prints menu
     */
    public void printMenu(){
        System.out.println("\nMenu:\n" +
                "0) Add new Member\n" +
                "1) Delete Member\n" +
                "2) Add new Receipt\n" +
                "3) Change date of Receipt\n" +
                "4) Delete Receipt\n" +
                "5) Add Product to Receipt\n" +
                "6) Delete Product from Receipt\n" +
                "7) Print number of Receipts with value lower than 50 and value higher than 200 of Member\n" +
                "8) Print Member with list of Receipts\n" +
                "Anything else will exit the program\n");
    }
}
