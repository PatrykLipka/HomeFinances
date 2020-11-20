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
}
