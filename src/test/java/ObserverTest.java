import Basis.Airplane;
import Basis.Engine;
import Observer.Sensor;
import Observer.ServiceCenter;
import Observer.TelemetryValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObserverTest {
    Airplane airplane;
    ServiceCenter serviceCenter;
    Sensor sensor;

    @BeforeEach
    public void setup() {
        //Create Observer (= service center)
        serviceCenter = new ServiceCenter();
        //Create the Observables (= sensors)
        airplane = new Airplane();
        //Add the listener/Observer (=serviceCenter) to the Observables (=sensors)
        for (int sensorAreaCount = 0; sensorAreaCount < 5; sensorAreaCount++) {
            for (int sensorListCount = 0; sensorListCount < 10; sensorListCount++) {
                for (Engine engine : airplane.getEnginesList()) {
                    sensor = engine.getSensorAreaList().get(sensorAreaCount).getSensorList().get(sensorListCount);
                    sensor.addServiceCenter(serviceCenter);
                }
            }
        }
        //At the beginning no exceeding of the values was noticed.
        //Therefore both lists are still empty.
        assertEquals(0, serviceCenter.getEngineExceeded().size());
        assertEquals(0, serviceCenter.getParameterExceeded().size());
    }

    @Test
    @Order(1)
    public void set1EngineAnd1Parameter5P() {
        //Set parameter P001 5 percent above the maximum.
        TelemetryValue p001 = airplane.getEnginesList().get(0).getSensorAreaList().get(0).getSensorList().get(0).getTelemetryValueList().get(0);
        p001.valueRangeExceeded(1.05);
        //The Observer has noticed tha a Parameter in a Sensor changed and records the changes in the lists
        assertEquals(1, serviceCenter.getEngineExceeded().size());
        assertEquals(1, serviceCenter.getParameterExceeded().size());
        assertEquals(5.0, serviceCenter.getParameterExceeded().get("P001").get(0).intValue());
    }

    @Test
    @Order(2)
    public void set2EnginesAnd1Parameter5P() {
        //Set parameter P001 5 percent above the maximum in two different engines
        TelemetryValue p001engine1 = airplane.getEnginesList().get(0).getSensorAreaList().get(0).getSensorList().get(0).getTelemetryValueList().get(0);
        TelemetryValue p001engine2 = airplane.getEnginesList().get(1).getSensorAreaList().get(0).getSensorList().get(0).getTelemetryValueList().get(0);
        p001engine1.valueRangeExceeded(1.05);
        p001engine2.valueRangeExceeded(1.05);
        //The Observer has noticed tha a Parameter in a Sensor changed and records the changes in the lists
        assertEquals(2, serviceCenter.getEngineExceeded().size());
        assertEquals(1, serviceCenter.getParameterExceeded().size());
        //recorded two different percentages for Parameter P001 (one per engine)
        assertEquals(5, serviceCenter.getParameterExceeded().get("P001").get(0).intValue());
        assertEquals(5, serviceCenter.getParameterExceeded().get("P001").get(1).intValue());
    }

    @Test
    @Order(3)
    public void set1EnginesAnd2Parameter5P() {
        //Set parameter P001 and p002 5 percent above the maximum in the same engine
        TelemetryValue p001 = airplane.getEnginesList().get(0).getSensorAreaList().get(0).getSensorList().get(0).getTelemetryValueList().get(0);
        TelemetryValue p002 = airplane.getEnginesList().get(0).getSensorAreaList().get(0).getSensorList().get(0).getTelemetryValueList().get(1);
        p001.valueRangeExceeded(1.05);
        p002.valueRangeExceeded(1.05);
        //The Observer has noticed tha a Parameter in a Sensor changed and records the changes in the lists
        assertEquals(1, serviceCenter.getEngineExceeded().size());
        assertEquals(2, serviceCenter.getParameterExceeded().size());
        //recorded two percentages, one for Parameter P001 and one fpr P002
        assertEquals(5, serviceCenter.getParameterExceeded().get("P001").get(0).intValue());
        assertEquals(5, serviceCenter.getParameterExceeded().get("P002").get(0).intValue());
    }

    @Test
    @Order(4)
    public void set2EnginesAnd2ParameterDifferentP() {
        //Set parameter P001 and p002 5 percent above the maximum in the same engine
        TelemetryValue p004engine3 = airplane.getEnginesList().get(2).getSensorAreaList().get(0).getSensorList().get(0).getTelemetryValueList().get(3);
        TelemetryValue p005engine1 = airplane.getEnginesList().get(0).getSensorAreaList().get(0).getSensorList().get(0).getTelemetryValueList().get(4);
        p004engine3.valueRangeExceeded(1.2);
        p005engine1.valueRangeExceeded(1.1);
        //The Observer has noticed tha a Parameter in a Sensor changed and records the changes in the lists
        assertEquals(2, serviceCenter.getEngineExceeded().size());
        assertEquals(2, serviceCenter.getParameterExceeded().size());
        //recorded two percentages, one for Parameter P001 and one fpr P002
        assertEquals(20, serviceCenter.getParameterExceeded().get("P004").get(0).intValue());
        assertEquals(10, serviceCenter.getParameterExceeded().get("P005").get(0).intValue());
    }

    @Test
    @Order(5)
    public void setCorrectCode() {
        set1EngineAnd1Parameter5P();
        assertEquals("C01", serviceCenter.determineCodeID());
        set2EnginesAnd1Parameter5P();
        assertEquals("C02", serviceCenter.determineCodeID());
        set1EnginesAnd2Parameter5P();
        assertEquals("C03", serviceCenter.determineCodeID());
        set2EnginesAnd2ParameterDifferentP();
        assertEquals("C08", serviceCenter.determineCodeID());
    }


}