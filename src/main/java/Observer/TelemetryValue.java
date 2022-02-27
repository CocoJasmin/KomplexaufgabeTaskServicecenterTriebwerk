package Observer;

import java.util.Random;

public class TelemetryValue {
    private int minimum;
    private int maximum;
    private String ID;
    private double value;
    private Sensor sensor;

    public TelemetryValue() {
    }

    public void setValue() {
        Random rand = new Random();
        this.value = rand.nextInt(maximum - minimum + 1) + minimum;
    }

    public double getValue() {
        return value;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public void setNewValue() {
        double probabilityNumber = Math.random();
        System.out.println("Eingetroffene WKT:" + probabilityNumber);
        if (probabilityNumber < 0.05) {
            if (probabilityNumber < 0.03) {
                if (probabilityNumber < 0.01) {
                    valueRangeExceeded(1.2);
                } else valueRangeExceeded(1.1);
            } else valueRangeExceeded(1.05);
        } else {
            setValue();
        }
    }

    public void valueRangeExceeded(double percentage) {
        System.out.println("WARNING:");
        System.out.println("\tIn Parameter: " + getID());
        System.out.println("\t- Old Value: " + (int) getValue());
        this.value = this.maximum * percentage;
        System.out.println("\t- New value  " + (int) getValue() + " exceeds the maximum value of " + getMaximum() + " by " + (int) ((percentage - 1) * 100) + "%.");
        this.sensor.notifyParameterValueExceeded(this);
    }
}