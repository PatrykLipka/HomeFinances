package pl.polsl.lab.homefinances.patryk.lipka.controller;

import pl.polsl.lab.homefinances.patryk.lipka.model.Member;
import pl.polsl.lab.homefinances.patryk.lipka.view.ViewController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class handles the interaction between application and user, it shows the menu and allows user to manipulate data.
 *
 * @author Patryk Lipka
 * @version 1.1
 */

public class Main {
    /**
     * Main method of application.
     * Reads run arguments they are invalid program will end, if there are more arguments than one the program will only read the first one
     *
     * @param args number of option with which you want to start the program
     */
    public static void main(String[] args) {
        List<Member> memberList = new ArrayList<>();
        ViewController viewController = new ViewController();

        Controller controller = new Controller(memberList, viewController);

        boolean argsUsed = false;
        Scanner scanner = new Scanner(System.in);
        int optionInt;
        do {
            System.out.println("\nMenu:\n" +
                    "1) Add new Member\n" +
                    "2) Delete Member\n" +
                    "3) Add new Receipt\n" +
                    "4) Change date of Receipt\n" +
                    "5) Delete Receipt\n" +
                    "6) Add Product to Receipt\n" +
                    "7) Delete Product from Receipt\n" +
                    "8) Print number of Receipts with value lower than 50 and value higher than 200 of Member\n" +
                    "9) Print Member with list of Receipts\n" +
                    "Anything else will exit the program\n");
            String option;
            if (args.length != 0 && !argsUsed){
                option = args[0];
                argsUsed = true;
            } else {
                option = scanner.nextLine();
            }
            if (controller.isInteger(option)) {
                optionInt = Integer.parseInt(option);
            } else {
                optionInt = -1;
            }
            switch (optionInt) {
                case 1:
                    System.out.println("Enter new Member name:");
                    String name = scanner.nextLine();
                    controller.addFamilyMember(name);
                    break;
                case 2:
                    System.out.println("Enter the number of Member you want to delete");
                    String memberNumber = scanner.nextLine();
                    controller.deleteFamilyMember(memberNumber);
                    break;
                case 3:
                    System.out.println("Enter the number of Member to add a new Receipt to:");
                    memberNumber = scanner.nextLine();
                    System.out.println("Enter name of the shop:");
                    String shopName = scanner.nextLine();
                    if (controller.addNewReceiptToFamilyMember(memberNumber, shopName)) {
                        System.out.println("Do you want to change date of Receipt?\n" +
                                "1- Yes\n" +
                                "2- No, leave today's date\n");
                        String doYouWantToChangeDate = scanner.nextLine();
                        if (doYouWantToChangeDate.compareTo("1") == 0) {
                            String tempPartialDate;
                            String dateString;
                            System.out.println("Enter day:");
                            tempPartialDate = scanner.nextLine();
                            if (tempPartialDate.matches("\\d")) {
                                tempPartialDate = "0" + tempPartialDate;
                            }
                            dateString = tempPartialDate + "/";
                            System.out.println("Enter month:");
                            tempPartialDate = scanner.nextLine();
                            if (tempPartialDate.matches("\\d")) {
                                tempPartialDate = "0" + tempPartialDate;
                            }
                            dateString += tempPartialDate + "/";
                            System.out.println("Enter year:");
                            dateString += scanner.nextLine();
                            controller.changeDateOfLastReceipt(memberNumber, dateString);
                        }
                        System.out.println("Do you want to add items to the Receipt?\n" +
                                "1- Yes\n" +
                                "2- No\n");
                        String doYouWantToAddItems = scanner.nextLine();
                        while (doYouWantToAddItems.compareTo("1") == 0) {
                            System.out.println("Enter name of the Product:");
                            String productName = scanner.nextLine();
                            System.out.println("Enter price of the Product:");
                            String productPrice = scanner.nextLine();
                            controller.addProductToLastReceipt(memberNumber, productName, productPrice);

                            System.out.println("Do you want to add more items to the Receipt?\n" +
                                    "1- Yes\n" +
                                    "2- No\n");
                            doYouWantToAddItems = scanner.nextLine();
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter the number of Member you want to change the date of a Receipt:");
                    memberNumber = scanner.nextLine();
                    System.out.println("Enter the number of the Receipt:");
                    String receiptNumber = scanner.nextLine();

                    if (controller.tryToGetReceipt(memberNumber, receiptNumber)) {
                        String tempPartialDate;
                        String dateString;
                        System.out.println("Enter day:");
                        tempPartialDate = scanner.nextLine();
                        if (tempPartialDate.matches("\\d")) {
                            tempPartialDate = "0" + tempPartialDate;
                        }
                        dateString = tempPartialDate + "/";
                        System.out.println("Enter month:");
                        tempPartialDate = scanner.nextLine();
                        if (tempPartialDate.matches("\\d")) {
                            tempPartialDate = "0" + tempPartialDate;
                        }
                        dateString += tempPartialDate + "/";
                        System.out.println("Enter year:");
                        dateString += scanner.nextLine();
                        System.out.println(dateString);
                        controller.changeDateOfReceipt(memberNumber, receiptNumber, dateString);
                    }
                    break;
                case 5:
                    System.out.println("Enter the number of Member to delete a Receipt from:");
                    memberNumber = scanner.nextLine();
                    System.out.println("Enter the number of the receipt to delete:");
                    receiptNumber = scanner.nextLine();
                    controller.deleteReceipt(memberNumber, receiptNumber);
                    break;
                case 6:
                    System.out.println("Enter the number of Member to add a new Product to Receipt:");
                    memberNumber = scanner.nextLine();
                    System.out.println("Enter the number of the Receipt:");
                    receiptNumber = scanner.nextLine();

                    if (controller.tryToGetReceipt(memberNumber, receiptNumber)) {
                        String doYouWantToAddItems = "1";
                        while (doYouWantToAddItems.compareTo("1") == 0) {
                            System.out.println("Enter name of the Product:");
                            String productName = scanner.nextLine();
                            System.out.println("Enter price of the Product:");
                            String productPrice = scanner.nextLine();
                            controller.addProductToReceipt(memberNumber, receiptNumber, productName, productPrice);

                            System.out.println("Do you want to add more items to the Receipt?\n" +
                                    "1- Yes\n" +
                                    "2- No\n");
                            doYouWantToAddItems = scanner.nextLine();
                        }
                    }
                    break;
                case 7:
                    System.out.println("Enter the number of Member to delete a Product from Receipt:");
                    memberNumber = scanner.nextLine();
                    System.out.println("Enter the number of the Receipt:");
                    receiptNumber = scanner.nextLine();

                    if (controller.tryToGetReceipt(memberNumber, receiptNumber)) {
                        String doYouWantToDeleteItems = "1";
                        while (doYouWantToDeleteItems.compareTo("1") == 0) {
                            System.out.println("Enter number of the Product:");
                            String productNumber = scanner.nextLine();
                            controller.deleteProductFromReceipt(memberNumber, receiptNumber, productNumber);

                            System.out.println("Do you want to delete more items from the Receipt?\n" +
                                    "1- Yes\n" +
                                    "2- No\n");
                            doYouWantToDeleteItems = scanner.nextLine();
                        }
                    }
                    break;
                case 8:
                    System.out.println("Enter the number of Member you want to know amount of Receipts with value lower than 50 and value higher than 200");
                    memberNumber = scanner.nextLine();
                    controller.printNumberOfLowAndHighValueReceipts(memberNumber);
                    break;
                case 9:
                    System.out.println("Enter the number of Member you want to print with list of Receipts:");
                    memberNumber = scanner.nextLine();
                    controller.printFamilyMemberWithReceipts(memberNumber);
                    break;
                default:
                    break;
            }

        } while (optionInt >= 1 && optionInt <= 9);
        scanner.close();
    }
}
