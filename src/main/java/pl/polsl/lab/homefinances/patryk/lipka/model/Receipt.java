package pl.polsl.lab.homefinances.patryk.lipka.model;

import pl.polsl.lab.homefinances.patryk.lipka.exception.InvalidDateException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class stores data about transaction
 *
 * @author Patryk Lipka
 * @version 1.0
 */

public class Receipt {
    private List<pl.polsl.lab.homefinances.patryk.lipka.model.Product> productList;
    private Double value;
    private String date;
    private String shopName;

    /**
     * Constructor of Receipt
     *
     * @param date     date of Receipt we want to create
     * @param shopName name of shop where the purchase took place
     */
    public Receipt(String date, String shopName) {
        this.value = 0.0;
        this.date = date;
        this.shopName = shopName;
        this.productList = new ArrayList<pl.polsl.lab.homefinances.patryk.lipka.model.Product>();
    }

    /**
     * Method allows us to get list of Products of Receipt
     *
     * @return method returns list of Products of Receipt
     */
    public List<pl.polsl.lab.homefinances.patryk.lipka.model.Product> getProductList() {
        return productList;
    }

    /**
     * Method allows us to get value of Receipt
     *
     * @return method returns value of Receipt
     */
    public Double getValue() {
        return value;
    }

    /**
     * Method allows us to get date of Receipt
     *
     * @return method returns date of Receipt
     */
    public String getDate() {
        return date;
    }

    /**
     * Method allows us to set new date to Receipt
     *
     * @param dateString new date we want to assign to Receipt
     * @throws InvalidDateException exception thrown when date is invalid
     */
    public void setDate(String dateString) throws InvalidDateException {
        if (isDateValid(dateString)) {
            this.date = dateString;
        } else {
            throw new InvalidDateException("Invalid date!");
        }
    }

    /**
     * Method that checks if date is valid
     *
     * @param dateString date we want to check
     * @return method returns true if date is valid and false if it's not
     */
    public boolean isDateValid(String dateString) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            format.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Method allows us to get name of shop from Receipt
     *
     * @return returns name of shop from Receipt
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Method adds new Product to the list
     *
     * @param name  is the name of the product
     * @param price is the price of the product
     */
    public void addProductToList(String name, Double price) {
        pl.polsl.lab.homefinances.patryk.lipka.model.Product product = new pl.polsl.lab.homefinances.patryk.lipka.model.Product(name, price);
        productList.add(product);
    }

    /**
     * Method adds price of Product to value of Receipt
     */
    public void addValue(double productPrice) {
        this.value += productPrice;
    }

    /**
     * Method subtracts price of Product to value of Receipt
     */
    public void subValue(double productPrice) {
        this.value -= productPrice;
    }
}
