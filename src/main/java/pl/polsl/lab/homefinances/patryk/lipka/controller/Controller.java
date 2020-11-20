package pl.polsl.lab.homefinances.patryk.lipka.controller;

import pl.polsl.lab.homefinances.patryk.lipka.exception.InvalidDateException;
import pl.polsl.lab.homefinances.patryk.lipka.model.Member;
import pl.polsl.lab.homefinances.patryk.lipka.model.Product;
import pl.polsl.lab.homefinances.patryk.lipka.model.Receipt;
import pl.polsl.lab.homefinances.patryk.lipka.view.ViewController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class allows the access and manipulation of data
 *
 * @author Patryk Lipka
 * @version 1.0
 */

public class Controller {
    private List<Member> model;
    private ViewController view;

    /**
     * Constructor of Controller
     *
     * @param model model stores list of Member objects
     * @param view  is a ViewController class we use to display outputs
     */
    public Controller(List<Member> model, ViewController view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Method that checks if the input is a String that can be converted into Integer type of number
     *
     * @param input String, which we want to check
     * @return method returns true if the String can be converted and false if not
     */
    public boolean isInteger(String input) {
        if (input == null) {
            return false;
        }
        try {
            int number = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Method that checks if the input is a String that can be converted into Double type of number
     *
     * @param input String, which we want to check
     * @return method returns true if the String can be converted and false if not
     */
    public boolean isDouble(String input) {
        if (input == null) {
            return false;
        }
        try {
            double number = Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Method allows us to get Member from the list
     *
     * @param memberNumber it's the number of the Member you want to get
     * @return method returns a Member from the list
     */
    public Member getFamilyMember(int memberNumber) {
        return model.get(memberNumber);
    }

    /**
     * Method allows us to get name of Member from the list
     *
     * @param memberNumber it's the number of the Member you want to get the name of
     * @return method returns name of Member from the list
     */
    public String getFamilyMemberName(int memberNumber) {
        return model.get(memberNumber).getName();
    }

    /**
     * Method checks if a Receipt exists
     *
     * @param memberNumber  input Member number from whom we try to get Receipt
     * @param receiptNumber input Receipt number which we try to get
     * @return method returns true if this Receipt exists and false if it does not
     */
    public boolean tryToGetReceipt(String memberNumber, String receiptNumber) {
        if (isInteger(memberNumber) && isInteger(receiptNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            int receiptNumberInt = Integer.parseInt(receiptNumber);
            if (memberNumberInt < model.size()) {
                if (receiptNumberInt < model.get(memberNumberInt).getReceiptList().size()) {
                    return true;
                } else {
                    view.printLogMessage("Receipt number out of range, choose a number between: 0 and " +
                            (model.get(memberNumberInt).getReceiptList().size() - 1));
                }
            } else {
                view.printLogMessage("Member number out of range, choose a number between: 0 and " +
                        (model.size() - 1));
            }
        } else {
            view.printLogMessage("Member number or receipt number is not a number!");
        }
        return false;
    }

    /**
     * Method adds new Member to the list
     *
     * @param member it's the Member you want to add
     */
    public void addFamilyMember(Member member) {
        model.add(member);
    }

    /**
     * Method adds new Member to the ListOfFamilyMembers
     *
     * @param name it's the name of Member you want to add
     */
    public void addFamilyMember(String name) {
        Member member = new Member(name);
        addFamilyMember(member);
    }

    /**
     * Method removes Member from the list if the Member exists
     *
     * @param memberNumber number of Member who we want to remove
     */
    public void deleteFamilyMember(String memberNumber) {
        if (isInteger(memberNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            if (memberNumberInt < model.size()) {
                model.remove(memberNumberInt);
            } else {
                view.printLogMessage("Number out of range, choose a number between: 0 and " +
                        (model.size() - 1));
            }
        } else {
            view.printLogMessage("Not a number!");
        }
    }


    /**
     * Method adds new Receipt to the Member
     *
     * @param memberNumber it's the number of Member you want to add the Receipt to
     * @param shopName     it's the name of the shop where the items were bought
     * @return method returns true if the Receipt has been added
     */
    public boolean addNewReceiptToFamilyMember(String memberNumber, String shopName) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(date);
        Receipt receipt = new Receipt(dateString, shopName);
        if (isInteger(memberNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            if (memberNumberInt < model.size()) {
                model.get(memberNumberInt).getReceiptList().add(receipt);
                return true;
            } else {
                view.printLogMessage("Number out of range, choose a number between: 0 and " +
                        (model.size() - 1));
            }
        } else {
            view.printLogMessage("Not a number!");
        }
        return false;
    }

    /**
     * Method deletes Receipt from Member
     *
     * @param memberNumber  it's a number of Member from whom we want to delete Receipt
     * @param receiptNumber it's a number of Receipt which we want to delete
     */
    public void deleteReceipt(String memberNumber, String receiptNumber) {
        if (isInteger(memberNumber) && isInteger(receiptNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            int receiptNumberInt = Integer.parseInt(receiptNumber);
            if (memberNumberInt < model.size()) {
                if (receiptNumberInt < model.get(memberNumberInt).getReceiptList().size()) {
                    model.get(memberNumberInt).getReceiptList().remove(receiptNumberInt);
                } else {
                    view.printLogMessage("Receipt number out of range, choose a number between: 0 and " +
                            (model.get(memberNumberInt).getReceiptList().size() - 1));
                }
            } else {
                view.printLogMessage("Member number out of range, choose a number between: 0 and " +
                        (model.size() - 1));
            }
        } else {
            view.printLogMessage("Member number or receipt number is not a number!");
        }
    }


    /**
     * Method allows us to add new Product to Receipt
     *
     * @param memberNumber  number of Member to whose Receipt we want to add new Product to
     * @param receiptNumber number of Receipt to which we want to add a new Product to
     * @param productName   name of the Product we want to add
     * @param productPrice  price of the Product we want to add
     */
    public void addProductToReceipt(String memberNumber, String receiptNumber, String productName, String productPrice) {
        if (isInteger(memberNumber) && isInteger(receiptNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            int receiptNumberInt = Integer.parseInt(receiptNumber);
            if (memberNumberInt < model.size()) {
                if (receiptNumberInt < model.get(memberNumberInt).getReceiptList().size()) {
                    if (isDouble(productPrice)) {
                        double productPriceDouble = Double.parseDouble(productPrice);
                        model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).addProductToList(productName, productPriceDouble);
                        model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).addValue(productPriceDouble);
                    } else {
                        view.printLogMessage("Price is not a number!");
                    }
                } else {
                    view.printLogMessage("Receipt number out of range, choose a number between: 0 and " +
                            (model.get(memberNumberInt).getReceiptList().size() - 1));
                }
            } else {
                view.printLogMessage("Member number out of range, choose a number between: 0 and " +
                        (model.size() - 1));
            }
        } else {
            view.printLogMessage("Member number or receipt number is not a number!");
        }
    }

    /**
     * Method allows us to change the date of chosen Receipt
     *
     * @param memberNumber  number of Member whose Receipt date we want to change
     * @param receiptNumber number of Receipt which date we want to change
     * @param dateString    new date that will override the previous one
     */
    public void changeDateOfReceipt(String memberNumber, String receiptNumber, String dateString) {
        if (isInteger(memberNumber) && isInteger(receiptNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            int receiptNumberInt = Integer.parseInt(receiptNumber);
            if (memberNumberInt < model.size()) {
                if (receiptNumberInt < model.get(memberNumberInt).getReceiptList().size()) {
                    try {
                        model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).setDate(dateString);
                    } catch (InvalidDateException exception) {
                        view.printLogMessage("Date unchanged!");
                    }
                } else {
                    view.printLogMessage("Receipt number out of range, choose a number between: 0 and " +
                            (model.get(memberNumberInt).getReceiptList().size() - 1));
                }
            } else {
                view.printLogMessage("Member number out of range, choose a number between: 0 and " +
                        (model.size() - 1));
            }
        } else {
            view.printLogMessage("Member number or receipt number is not a number!");
        }
    }

    /**
     * Method allows us to add items to the last added Receipt
     *
     * @param memberNumber number of Member to whose Receipt we want to add item to
     * @param productName  name of the Product which we want to add
     * @param productPrice price of the Product which we want to add
     */
    public void addProductToLastReceipt(String memberNumber, String productName, String productPrice) {
        if (isInteger(memberNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            if (memberNumberInt < model.size()) {
                if (isDouble(productPrice)) {
                    int receiptNumberInt = model.get(memberNumberInt).getReceiptList().size() - 1;
                    double productPriceDouble = Double.parseDouble(productPrice);
                    model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).addProductToList(productName, productPriceDouble);
                    model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).addValue(productPriceDouble);
                } else {
                    view.printLogMessage("Price is not a number!");
                }

            } else {
                view.printLogMessage("Number out of range, choose a number between: 0 and " +
                        (model.size() - 1));
            }
        } else {
            view.printLogMessage("Member number is not a number!");
        }
    }

    /**
     * Method allows us to change the date of last added Receipt
     *
     * @param memberNumber number of Member whose date of Receipt we want to change
     * @param dateString   new date that will override the previous one
     */
    public void changeDateOfLastReceipt(String memberNumber, String dateString) {
        if (isInteger(memberNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            if (memberNumberInt < model.size()) {
                int receiptNumberInt = model.get(memberNumberInt).getReceiptList().size() - 1;
                try {
                    model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).setDate(dateString);
                } catch (InvalidDateException exception) {
                    view.printLogMessage("Date unchanged!");
                }
            } else {
                view.printLogMessage("Member number out of range, choose a number between: 0 and " +
                        (model.size() - 1));
            }
        } else {
            view.printLogMessage("Member number is not a number!");
        }
    }

    /**
     * Method allows us to delete Product from chosen Receipt
     *
     * @param memberNumber  number of Member whose Receipt we want to delete Product
     * @param receiptNumber number of Receipt from which we want to delete Product
     * @param productNumber number of Product we want to delete
     */
    public void deleteProductFromReceipt(String memberNumber, String receiptNumber, String productNumber) {
        if (isInteger(memberNumber) && isInteger(receiptNumber) && isInteger(productNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            int receiptNumberInt = Integer.parseInt(receiptNumber);
            int productNumberInt = Integer.parseInt(productNumber);
            if (memberNumberInt < model.size()) {
                if (receiptNumberInt < model.get(memberNumberInt).getReceiptList().size()) {
                    if (productNumberInt < model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).getProductList().size()) {
                        double value = model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).getProductList().get(productNumberInt).getPrice();
                        model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).subValue(value);
                        model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).getProductList().remove(productNumberInt);
                    } else {
                        view.printLogMessage("Product number out of range, choose a number between: 0 and " +
                                (model.get(memberNumberInt).getReceiptList().get(receiptNumberInt).getProductList().size() - 1));
                    }
                } else {
                    view.printLogMessage("Receipt number out of range, choose a number between: 0 and " +
                            (model.get(memberNumberInt).getReceiptList().size() - 1));
                }
            } else {
                view.printLogMessage("Member number out of range, choose a number between: 0 and " +
                        (model.size() - 1));
            }
        } else {
            view.printLogMessage("Member number or Receipt number or Product number is not a number!");
        }
    }

    /**
     * Method prints data of Member with all the Receipts
     *
     * @param memberNumber it's the number of the Member you want to print
     */
    public void printFamilyMemberWithReceipts(String memberNumber) {
        printFamilyMember(memberNumber);
        if (isInteger(memberNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            if (getFamilyMember(memberNumberInt).getReceiptList().size() > 0) {
                for (int i = 0; i < getFamilyMember(memberNumberInt).getReceiptList().size(); i++) {
                    Receipt receipt = getFamilyMember(memberNumberInt).getReceiptList().get(i);
                    String shopName = receipt.getShopName();
                    String dateString = receipt.getDate();
                    double value = receipt.getValue();
                    view.printReceiptData(i, shopName, dateString, value);

                    if (getFamilyMember(memberNumberInt).getReceiptList().get(i).getProductList().size() > 0) {
                        for (Product product : getFamilyMember(memberNumberInt).getReceiptList().get(i).getProductList()) {
                            String productName = product.getName();
                            double productPrice = product.getPrice();
                            view.printProduct(productName, productPrice);
                        }
                    }
                }
            }
        }
    }


    /**
     * Method prints data of Member
     *
     * @param memberNumber it's the number of the Member you want to print
     */
    public void printFamilyMember(String memberNumber) {
        if (isInteger(memberNumber)) {
            int memberNumberInt = Integer.parseInt(memberNumber);
            if (memberNumberInt < model.size()) {
                String name = getFamilyMemberName(memberNumberInt);
                view.printMember(name);
            } else {
                view.printLogMessage("Member number out of range, choose a number between: 0 and " +
                        (model.size() - 1));
            }
        } else {
            view.printLogMessage("Not a number!");
        }
    }
}
