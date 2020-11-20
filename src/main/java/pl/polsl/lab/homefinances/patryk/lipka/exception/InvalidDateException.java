package pl.polsl.lab.homefinances.patryk.lipka.exception;

/**
 * Class stores data about InvalidDateException
 *
 * @author Patryk Lipka
 * @version 1.0
 */
public class InvalidDateException extends Exception {
    /**
     * Constructor of InvalidDateException
     *
     * @param errorMessage message we want to create InvalidDateException with
     */
    public InvalidDateException(String errorMessage) {
        super(errorMessage);
    }
}
