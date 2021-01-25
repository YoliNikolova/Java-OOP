package onlineShop.models.products.components;

public class RandomAccessMemory extends BaseComponent {
    private final double MULTIPLIER = 1.20;

    public RandomAccessMemory(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance, generation);
    }

    @Override
    public double getOverallPerformance() {
        return overallPerformance*MULTIPLIER;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
