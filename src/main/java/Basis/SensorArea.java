package Basis;

import Observer.Sensor;

import java.util.ArrayList;

public class SensorArea {
    private final ArrayList<Sensor> sensorList;
    private Engine engine;

    public SensorArea() {
        this.sensorList = new ArrayList<>();
        for (int numberOfSensors = 0; numberOfSensors < 10; numberOfSensors++) {
            Sensor sensor = new Sensor();
            sensor.setSensorArea(this);
            sensorList.add(sensor);
        }
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public ArrayList<Sensor> getSensorList() {
        return sensorList;
    }
}