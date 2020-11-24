package pl.polsl.lab.homefinances.patryk.lipka.controller;

import pl.polsl.lab.homefinances.patryk.lipka.model.Receipt;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Interface that extends Function interface and is used in other classes
 *
 * @author Patryk Lipka
 * @version 1.1
 */
public interface ReceiptCounter extends Function<ArrayList<Receipt>, Integer> {

}

