package ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setHeight(height);
        this.setLength(length);
        this.setWidth(width);
    }

    private void setHeight(double height) {
        if (!isGreaterThanZero(height)) {
            throw new IllegalArgumentException("Height cannot be zero or negative.");
        }
        this.height = height;
    }

    private void setLength(double length) {
        if (!isGreaterThanZero(length)) {
            throw new IllegalArgumentException("Length cannot be zero or negative.");
        }
        this.length = length;
    }

    private void setWidth(double width) {
        if (!isGreaterThanZero(width)) {
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }
        this.width = width;
    }

    private boolean isGreaterThanZero(double value) {
        return value > 0;
    }

    public double calculateSurfaceArea() {
        return 2 * length * width + calculateLateralSurfaceArea();
    }

    public double calculateLateralSurfaceArea() {
        return 2 * length * height + 2 * width * height;
    }

    public double calculateVolume() {
        return height * length * width;
    }
}
