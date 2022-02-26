package Observer;

import java.util.Random;

public class TelemetryValue {
    private int minimum;
    private int maximum;
    private String ID;
    private int value;
    private Sensor sensor;

    public TelemetryValue() {
    }

    public void setValue() {
        Random rand = new Random();
        this.value = (rand.nextInt(maximum - minimum + 1) + minimum);
    }

    public int getValue() {
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

    public void valueRangeExceeded(float percentage) {
        this.value *= percentage;
        this.sensor.notifyParameterValueExceeded(this);
    }
}