package Basis;

import COR.*;
import Observer.Sensor;
import Observer.ServiceCenter;
import Observer.TelemetryValue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application implements ActionListener {
    Airplane airplane;
    ServiceCenter serviceCenter;
    int actionPerformedCounter;
    Team t01;
    Team t02;
    Team t03;
    Team t04;
    Team t05;

    /* Here is the initialization of the airplane, the serviceCenter and the required teams.
    The observer ServiceCenter is also added as a listener to the observable sensors here.
     */
    public Application() {
        //initialization of the airplane, the serviceCenter and the required teams
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
        /*The simulation timer starts here.
         Every second the actionPerformed(ActionEvent evt) is executed. TNew telemetry values are set there.
         Because the application should not run forever, I have limited the runs to 4.
        */
        actionPerformedCounter = 0;
        Timer timer = new Timer(1000, this);
        timer.start();
        try {
            while (actionPerformedCounter < 4) {
                Thread.sleep(1000);
            }
            timer.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        System.out.println("hi");
        System.out.println(5/0);
       // new Application();
    }

    /*
    airplane.setNewParameterValues();
        -> The normal range per parameter and engine exceeds the maximum value with the probabilities given in the task.
    String codeID = serviceCenter.determineCodeID();
        ->Afterwards the serviceCenter determines the correct code.
    01.handleCode(codeID);
        -> The determinedCode is handled over to team T01.
           If team T01 isn't responsible, it handles the code over to its successor team T02.
           If T02 isn't responsible, it handles the code over to its successor team T03 and so on.
    t01.setTeamIsResponsible(false);
        -> the boolean value if the team is responsible is reset.
           This value is mainly needed to lock in the JUNIT-Test ObserverTest if the Observer-Pattern is correct implemented.
    Afterwards the values of the parameters are set to a randomly chosen value within the normal range.
     */
    public void actionPerformed(ActionEvent evt) {
        actionPerformedCounter++;
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Run number:" + actionPerformedCounter);
        System.out.println("---------------------------------------------------------------------------------------------");
        airplane.setNewParameterValues();
        String codeID = serviceCenter.determineCodeID();
        t01.handleCode(codeID);
        t01.setTeamIsResponsible(false);
        t02.setTeamIsResponsible(false);
        t03.setTeamIsResponsible(false);
        t04.setTeamIsResponsible(false);
        t05.setTeamIsResponsible(false);
        System.out.println("---------------------------------------------------------------------------------------------");
        TelemetryValue parameter;
        for (int sensorAreaCount = 0; sensorAreaCount < 5; sensorAreaCount++) {
            for (int sensorListCount = 0; sensorListCount < 10; sensorListCount++) {
                for (int telemetryValueCount = 0; telemetryValueCount < 5; telemetryValueCount++) {
                    for (Engine engine : airplane.getEnginesList()) {
                        parameter = engine.getSensorAreaList().get(sensorAreaCount).getSensorList().get(sensorListCount).getTelemetryValueList().get(telemetryValueCount);
                        if (parameter.getValue() > parameter.getMaximum()) {
                            parameter.setValue();
                        }
                    }
                }
            }
        }
    }
}