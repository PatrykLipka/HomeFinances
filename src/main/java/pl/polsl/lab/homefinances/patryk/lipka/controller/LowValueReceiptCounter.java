package pl.polsl.lab.homefinances.patryk.lipka.controller;

import pl.polsl.lab.homefinances.patryk.lipka.model.Receipt;

import java.util.ArrayList;

/**
 * Class is used to apply filter on list of Receipts
 *
 * @author Patryk Lipka
 * @version 1.0
 */
public class LowValueReceiptCounter implements ReceiptCounter {
    /**
     * Method applies filter on list of Receipts
     * @param receipts receipt list on which we want to apply filter on
     * @return method returns number of Receipts from list of Receipts with value lower than 50
     */
    @Override
    public Integer apply(ArrayList<Receipt> receipts) {
        return Math.toIntExact(receipts
                .stream()
                .filter(r -> r.getValue() < 50)
                .count());
    }
}
