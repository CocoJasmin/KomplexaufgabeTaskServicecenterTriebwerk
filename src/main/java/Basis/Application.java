package Basis;

import COR.*;
import Observer.Sensor;
import Observer.ServiceCenter;
import Observer.TelemetryValue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application implements ActionListener {
    private final Timer timer = new Timer(1000, this);
    Airplane airplane;
    ServiceCenter serviceCenter;
    Team t01;
    Team t02;
    Team t03;
    Team t04;
    Team t05;

    public Application() {
        airplane = new Airplane();
        serviceCenter = new ServiceCenter();
        t05 = new T05();
        t04 = new T04(t05);
        t03 = new T03(t04);
        t02 = new T02(t03);
        t01 = new T01(t02);
        Sensor sensor;
        for (int sensorAreaCount = 0; sensorAreaCount < 5; sensorAreaCount++) {
            for (int sensorListCount = 0; sensorListCount < 10; sensorListCount++) {
                for (Engine engine : airplane.getEnginesList()) {
                    sensor = engine.getSensorAreaList().get(sensorAreaCount).getSensorList().get(sensorListCount);
                    sensor.addServiceCenter(serviceCenter);
                }
            }
        }
        timer.start();
        try {
            while (true) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        Application application = new Application();
    }

    public void actionPerformed(ActionEvent evt) {
        airplane.setNewParameterValues();
        String codeID = serviceCenter.determineCodeID();
        t01.handleCode(codeID);
        t01.setTeamIsResponsible(false);
        t02.setTeamIsResponsible(false);
        t03.setTeamIsResponsible(false);
        t04.setTeamIsResponsible(false);
        t05.setTeamIsResponsible(false);
        TelemetryValue parameter;
        for (int sensorAreaCount = 0; sensorAreaCount < 5; sensorAreaCount++) {
            for (int sensorListCount = 0; sensorListCount < 10; sensorListCount++) {
                for (int telemetryValueCount = 0; telemetryValueCount < 5; telemetryValueCount++) {
                    for (Engine engine : airplane.getEnginesList()) {
                        parameter = engine.getSensorAreaList().get(sensorAreaCount).getSensorList().get(sensorListCount).getTelemetryValueList().get(telemetryValueCount);
                        parameter.setValue();
                    }
                }
            }
        }
    }
}