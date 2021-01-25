package onlineShop.models.products.components;

public class CentralProcessingUnit extends BaseComponent {
    private final double MULTIPLIER = 1.25;

    public CentralProcessingUnit(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance, generation);
    }


    @Override
    public double getOverallPerformance() {
        return this.overallPerformance*MULTIPLIER;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}