package State;

public class Off implements ISmartphoneState {
    public void pressButton(Smartphone smartphone) {
        smartphone.setState(new On());
    }

    public String toString() {
        return "Off";
    }
}