package car;

import java.io.Serializable;

public interface Car extends Serializable {
    Integer TIRES = 4;

    public String getModel();

    public String getColor();

    public Integer getHorsePower();

    public String countryProduced();
}
