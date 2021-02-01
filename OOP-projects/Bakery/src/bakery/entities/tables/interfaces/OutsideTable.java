package bakery.entities.tables.interfaces;

public class OutsideTable extends BaseTable{
    private final static double pricePerPerson = 3.50;

    public OutsideTable(int tableNumber, int capacity) {
        super(tableNumber, capacity, pricePerPerson);
    }
}
