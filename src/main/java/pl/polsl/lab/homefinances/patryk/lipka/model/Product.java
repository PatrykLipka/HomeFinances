package pl.polsl.lab.homefinances.patryk.lipka.model;

/**
 * Class stores data about product
 *
 * @author Patryk Lipka
 * @version 1.0
 */

public class Product {
    private String name;
    private double price;

    /**
     * Constructor of Product
     *
     * @param name  name of Product to create
     * @param price price of Product to create
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Method allows us to get name of Product
     *
     * @return method returns name of Product
     */
    public String getName() {
        return name;
    }

    /**
     * Method allows us to get price of Product
     *
     * @return method returns price of Product
     */
    public double getPrice() {
        return price;
    }
}
