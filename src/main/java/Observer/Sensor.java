package Observer;

import Basis.SensorArea;

import java.util.ArrayList;

public class Sensor {
    private final ArrayList<TelemetryValue> TelemetryValueList;
    private final ArrayList<ISensorChangedListener> serviceCenterObserver;
    private SensorArea sensorArea;


    public Sensor() {
        this.TelemetryValueList = new ArrayList<>();
        this.serviceCenterObserver = new ArrayList<>();
        for (int numberOfTelemetryValues = 0; numberOfTelemetryValues < 5; numberOfTelemetryValues++) {
            TelemetryValue telemetryValue = new TelemetryValue();
            telemetryValue.setSensor(this);
            TelemetryValueList.add(telemetryValue);
        }
    }

    public SensorArea getSensorArea() {
        return sensorArea;
    }

    public void setSensorArea(SensorArea sensorArea) {
        this.sensorArea = sensorArea;
    }

    public void addServiceCenter(ISensorChangedListener serviceCenter) {
        serviceCenterObserver.add(serviceCenter);
    }

    public void removeServiceCenter(ISensorChangedListener serviceCenter) {
        serviceCenterObserver.remove(serviceCenter);
    }

    public void notifyParameterValueExceeded(TelemetryValue parameter) {
        for (ISensorChangedListener serviceCenter : serviceCenterObserver) {
            serviceCenter.updateSensorStatus(this.getSensorArea().getEngine(), parameter);
        }
    }

    public ArrayList<TelemetryValue> getTelemetryValueList() {
        return TelemetryValueList;
    }
}