package pl.polsl.lab.homefinances.patryk.lipka.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab.homefinances.patryk.lipka.exception.InvalidDateException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class of Receipt class
 *
 * @author Patryk Lipka
 * @version 1.0
 */
class ReceiptTest {
    /**
     * Test of setDate method, of class Receipt.
     *
     * @param date date which will be tested
     * @param isValid is passed date valid
     */
    @ParameterizedTest
    @CsvSource({"20/12/1975, true", "30/02/2020, false", "2/2/1999, true", "19-04-2020, false"})
    void setDateTest(String date, boolean isValid) {
        //GIVEN
        Receipt receipt = new Receipt("13/07/2020", "Little Shop");
        //WHEN
        try{
            receipt.setDate(date);
        }
        //THEN
        catch (InvalidDateException exception) {
            assertFalse(isValid);
        }
    }

    /**
     * Test of setDate method, of class Receipt exception throwing.
     *
     * @param date date which will be tested
     * @param isValid is passed date valid
     */
    @ParameterizedTest
    @CsvSource({"20/12/1975, true", "30/02/2020, false", "2/2/1999, true", "19-04-2020, false"})
    void setDateExceptionThrowTest(String date, boolean isValid) {
        //GIVEN
        Receipt receipt = new Receipt("13/07/2020", "Little Shop");
        //WHEN
        try{
            receipt.setDate(date);
            if(!isValid) fail();
        }
        //THEN
        catch (InvalidDateException exception) {
            assertFalse(isValid);
        }
    }

    /**
     * Test of addProductToList method, of class Receipt.
     *
     * @param name name of Product
     * @param price price of Product
     */
    @ParameterizedTest
    @CsvSource({"Product1, 0.0", "Product2, -5", "123, 120.3"})
    void addProductToListTest(String name, Double price) {
        //GIVEN
        Receipt receipt = new Receipt("13/07/2020", "Little Shop");
        //WHEN
        receipt.addProductToList(name, price);
        //THEN
        assertEquals(name, receipt.getProductList().get(0).getName());
        assertEquals(price, receipt.getProductList().get(0).getPrice());
    }

    /**
     * Test of addValue method, of class Receipt.
     * @param productPrice is a price of Product which price you want to add
     * @param expectedValue is an expected value after addition
     */
    @ParameterizedTest
    @CsvSource({"5.0, 10.0","-3.5, 1.5", "0, 5.0"})
    void addValueTest(double productPrice, double expectedValue) {
        //GIVEN
        Receipt receipt = new Receipt("13/07/2020", "Little Shop");
        Product product = new Product("Product1", 5.0);
        receipt.addProductToList(product.getName(), product.getPrice());
        //WHEN
        receipt.addValue(product.getPrice());
        receipt.addValue(productPrice);
        //THEN
        assertEquals(expectedValue, receipt.getValue());
    }

    /**
     * Test of subValue method, of class Receipt.
     * @param productPrice is a price of Product which price you want to subtract
     * @param expectedValue is an expected value after subtraction
     */
    @ParameterizedTest
    @CsvSource({"5.0, 0.0", "-3.5, 8.5", "0, 5.0"})
    void subValueTest(double productPrice, double expectedValue) {
        //GIVEN
        Receipt receipt = new Receipt("13/07/2020", "Little Shop");
        Product product = new Product("Product1", 5.0);
        receipt.addProductToList(product.getName(), product.getPrice());
        //WHEN
        receipt.addValue(product.getPrice());
        receipt.subValue(productPrice);
        //THEN
        assertEquals(expectedValue, receipt.getValue());
    }
}