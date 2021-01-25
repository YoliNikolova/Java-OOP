package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;

import onlineShop.models.products.BaseProduct;

import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;


public class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return overallPerformance;
        } else {
            double avg = 0;
            for (Component component : components) {
                avg += component.getOverallPerformance();
            }
            avg = avg / components.size();
            return (avg + overallPerformance);
        }
    }

    @Override
    public double getPrice() {
        double pricesOfComponents = 0;
        for (Component component : components) {
            pricesOfComponents += component.getPrice();
        }
        double pricesOfPer = 0;
        for (Peripheral peripheral : peripherals) {
            pricesOfPer += peripheral.getPrice();
        }
        return this.price + pricesOfComponents + pricesOfPer;
    }


    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        String currType = component.getClass().getSimpleName();
        boolean flag = false;
        for (Component component1 : components) {
            if (currType.equals(component1.getClass().getSimpleName())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT, currType, this.getClass().getSimpleName(), getId()));
        } else {
            this.components.add(component);
        }
    }

    @Override
    public Component removeComponent(String componentType) {
        Component componentToRemove = null;
        for (Component component : components) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                componentToRemove = component;
                break;
            }
        }
        if (components.isEmpty() || componentToRemove == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT, componentType, this.getClass().getSimpleName(), this.getId()));
        } else {
            components.remove(componentToRemove);
            return componentToRemove;
        }

    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        String currType = peripheral.getClass().getSimpleName();
        boolean flag = false;
        for (Peripheral peripheral1 : peripherals) {
            if (currType.equals(peripheral1.getClass().getSimpleName())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL, currType, this.getClass().getSimpleName(), this.getId()));
        } else {
            this.peripherals.add(peripheral);
        }
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral perToRemove = null;
        for (Peripheral per : peripherals) {
            if (per.getClass().getSimpleName().equals(peripheralType)) {
                perToRemove = per;
                break;
            }
        }
        if (peripherals.isEmpty() || perToRemove == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL, peripheralType, this.getClass().getSimpleName(), this.getId()));
        } else {
            peripherals.remove(perToRemove);
            return perToRemove;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(System.lineSeparator());
        sb.append(" ").append(String.format(OutputMessages.COMPUTER_COMPONENTS_TO_STRING, this.components.size())).append(System.lineSeparator());
        components.stream().forEach(e -> sb.append("  ").append(e.toString()).append(System.lineSeparator()));

        if (!peripherals.isEmpty()) {
            double sum = 0.0;
            for (Peripheral peripheral : peripherals) {
                sum += peripheral.getOverallPerformance();
            }
            sum = sum / (double) peripherals.size();
            sb.append(" ").append(String.format(OutputMessages.COMPUTER_PERIPHERALS_TO_STRING, this.peripherals.size(), sum)).append(System.lineSeparator());
            peripherals.stream().forEach(e ->
                    sb.append("  ").append(e.toString()));
        }else{
            sb.append(" ").append(String.format(OutputMessages.COMPUTER_PERIPHERALS_TO_STRING, 0, 0.0)).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
