package Basis;

import Observer.TelemetryValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Airplane {
    private final ArrayList<Engine> enginesList;

    public Airplane() {
        this.enginesList = new ArrayList<>();
        for (int numberOfEngines = 0; numberOfEngines < 4; numberOfEngines++) {
            Engine engine = new Engine();
            engine.setAirplane(this);
            enginesList.add(engine);
        }
        initializeTelemetryValues();
    }

    private void initializeTelemetryValues() {
        int parameterID = 0;
        TelemetryValue parameter;
        Map<String, Integer> minAndMax;
        for (int sensorAreaCount = 0; sensorAreaCount < 5; sensorAreaCount++) {
            for (int sensorListCount = 0; sensorListCount < 10; sensorListCount++) {
                for (int telemetryValueCount = 0; telemetryValueCount < 5; telemetryValueCount++) {
                    parameterID++;
                    System.out.println();
                    System.out.println();
                    minAndMax = getRandomMinAndMax();
                    for (Engine engine : this.getEnginesList()) {
                        parameter = engine.getSensorAreaList().get(sensorAreaCount).getSensorList().get(sensorListCount).getTelemetryValueList().get(telemetryValueCount);
                        parameter.setMinimum(minAndMax.get("Minimum"));
                        parameter.setMaximum(minAndMax.get("Maximum"));
                        setParameterID(parameter, parameterID);
                        setRandomValidValue(parameter);
                        System.out.println("ID: " + parameter.getID() + " MIn: " + parameter.getMinimum() + " max. " + parameter.getMaximum() + " Value: " + parameter.getValue());
                    }
                }
            }
        }
    }

    private Map<String, Integer> getRandomMinAndMax() {
        Random rand = new Random();
        Map<String, Integer> minAndMax = new HashMap<>();
        int value1 = ((rand.nextInt(40 + 1 - 2) + 2)) * 25;
        int value2 = ((rand.nextInt(40 + 1 - 2) + 2)) * 25;
        if (value1 > value2) {
            minAndMax.put("Minimum", value2);
            minAndMax.put("Maximum", value1);
        } else {
            minAndMax.put("Minimum", value1);
            minAndMax.put("Maximum", value2);
        }
        return minAndMax;
    }

    private void setParameterID(TelemetryValue parameter, int ID) {
        if (ID < 10) {
            parameter.setID("P00" + ID);
        } else if (ID < 100) {
            parameter.setID("P0" + ID);
        } else parameter.setID("P" + ID);
    }

    private void setRandomValidValue(TelemetryValue parameter) {
        parameter.setValue();
    }


    public ArrayList<Engine> getEnginesList() {
        return enginesList;
    }
}