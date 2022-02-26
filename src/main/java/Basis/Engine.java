package Basis;

import java.util.ArrayList;

public class Engine {
    private final ArrayList<SensorArea> sensorAreasList;
    private Airplane airplane;

    public Engine() {
        this.sensorAreasList = new ArrayList<>();
        for (int numberOfAreas = 0; numberOfAreas < 5; numberOfAreas++) {
            SensorArea sensorArea = new SensorArea();
            sensorArea.setEngine(this);
            sensorAreasList.add(sensorArea);
        }
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public ArrayList<SensorArea> getSensorAreaList() {
        return sensorAreasList;
    }
}