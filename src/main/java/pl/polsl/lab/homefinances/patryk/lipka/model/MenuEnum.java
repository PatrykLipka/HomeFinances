package pl.polsl.lab.homefinances.patryk.lipka.model;

/**
 * Enum class created to make menu easier to read
 *
 * @author Patryk Lipka
 * @version 1.0
 */
public enum MenuEnum {
    addMember(0),
    deleteMember(1),
    addReceipt(2),
    changeDate(3),
    deleteReceipt(4),
    addProduct(5),
    deleteProduct(6),
    printFilter(7),
    printMemberWithReceipts(8),
    invalidOption(9);

    private int menuValue;

    /**
     * Constructor of MenuEnum
     *
     * @param menuValue value of certain enum
     */
    MenuEnum(int menuValue){
       this.menuValue = menuValue;
    }
}

