package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;

import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    List<Computer> computers;

    public ControllerImpl() {
        this.computers = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {

        boolean flag = false;
        for (Computer computer : computers) {
            if (computer.getId() == id) {
                flag = true;
                break;
            }
        }
        if (flag) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
        }
        if (!computerType.equals("Laptop") && !computerType.equals("DesktopComputer")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }
        if (computerType.equals("Laptop")) {
            Computer computer = new Laptop(id, manufacturer, model, price);
            this.computers.add(computer);
        } else if (computerType.equals("DesktopComputer")) {
            Computer computer = new DesktopComputer(id, manufacturer, model, price);
            this.computers.add(computer);
        }

        return String.format(OutputMessages.ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model,
                                double price, double overallPerformance, String connectionType) {
        boolean flag = false;
        boolean flagForComp = false;
        for (Computer computer : computers) {
            if (computer.getId() == computerId) {
                flagForComp = true;
                break;
            }
        }
        if (!flagForComp) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        for (Computer computer : computers) {
            for (Peripheral per : computer.getPeripherals()) {
                if (per.getId() == id) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
        }

        Peripheral per = null;
        if (peripheralType.equals("Headset")) {
            per = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals("Keyboard")) {
            per = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals("Monitor")) {
            per = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals("Mouse")) {
            per = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }

        for (Computer computer : computers) {
            if (computer.getId() == computerId) {
                computer.addPeripheral(per);
            }
        }
        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        int perId = 0;
        Peripheral perToRemove=null;
        boolean flagForComp = false;
        for (Computer computer : computers) {
            if (computer.getId() == computerId) {
                flagForComp = true;
                break;
            }
        }
        if (!flagForComp) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        for (Computer computer : computers) {
            if (computer.getId() == computerId) {
                for (Peripheral peripheral : computer.getPeripherals()) {
                    if(peripheral.getClass().getSimpleName().equals(peripheralType)){
                        perId=peripheral.getId();
                        break;
                    }
                }
            }
            computer.removePeripheral(peripheralType);
        }

        return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, perId);
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model,
                               double price, double overallPerformance, int generation) {
        boolean flagForComp = false;
        for (Computer computer : computers) {
            if (computer.getId() == computerId) {
                flagForComp = true;
                break;
            }
        }
        if (!flagForComp) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        boolean flag = false;
        for (Computer computer : computers) {
            for (Component component : computer.getComponents()) {
                if (component.getId() == id) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
        }

        Component component = null;
        if (componentType.equals("CentralProcessingUnit")) {
            component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("Motherboard")) {
            component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("PowerSupply")) {
            component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("RandomAccessMemory")) {
            component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("SolidStateDrive")) {
            component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("VideoCard")) {
            component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }

        for (Computer computer : computers) {
            if (computer.getId() == computerId) {
                computer.addComponent(component);
            }
        }
        return String.format(OutputMessages.ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        int componentId = 0;
        boolean flagForComp = false;
        for (Computer computer : computers) {
            if (computer.getId() == computerId) {
                flagForComp = true;
                break;
            }
        }
        if (!flagForComp) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        for (Computer computer : computers) {
            if (computer.getId() == computerId) {
                for (Component component : computer.getComponents()) {
                    componentId=component.getId();
                }
            }
            computer.removeComponent(componentType);
        }

        return String.format(OutputMessages.REMOVED_COMPONENT, componentType, componentId);
    }

    @Override
    public String buyComputer(int id) {
        Computer comp = null;
        boolean flagForComp = false;
        for (Computer computer : computers) {
            if (computer.getId() == id) {
                flagForComp = true;
                break;
            }
        }
        if (!flagForComp) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        for (Computer computer : computers) {
            if (id == computer.getId()) {
                comp = computer;
                break;
            }
        }
        computers.remove(comp);
        return comp.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        double max = 0;
        Computer compToBuy = null;
        for (Computer computer : computers) {
            if (computer.getPrice() <= budget) {
                if (computer.getOverallPerformance() > max) {
                    max = computer.getOverallPerformance();
                    compToBuy = computer;
                }
            }
        }
        if (computers.isEmpty() || compToBuy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
        } else {
            this.computers.remove(compToBuy);
            return compToBuy.toString();
        }
    }

    @Override
    public String getComputerData(int id) {
        Computer comp = null;
        for (Computer computer : computers) {
            if (computer.getId() == id) {
                comp = computer;
            }
        }
        if (comp == null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
        }
        return comp.toString();
    }
}
