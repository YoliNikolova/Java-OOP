package onlineShop.models.products.peripherals;


public class Headset extends BasePeripheral {

    public Headset(int id, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance, connectionType);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
