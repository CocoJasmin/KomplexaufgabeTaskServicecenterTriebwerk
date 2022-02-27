package State;

public class On implements ISmartphoneState {
    public void pressButton(Smartphone smartphone) {
        smartphone.setState(new Off());
    }

    public String toString() {
        return "On";
    }
}