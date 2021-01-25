package onlineShop.models.products.components;

public class VideoCard extends BaseComponent {
    private final double MULTIPLIER = 1.15;

    public VideoCard(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
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
