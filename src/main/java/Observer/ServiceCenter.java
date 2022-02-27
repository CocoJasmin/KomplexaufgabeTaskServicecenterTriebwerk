package Observer;

import Basis.Engine;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceCenter implements ISensorChangedListener {
    private ArrayList<Engine> engineExceeded;
    private HashMap<String, ArrayList<Double>> parameterExceeded;

    public ServiceCenter() {
        parameterExceeded = new HashMap<>();
        engineExceeded = new ArrayList<>();
    }

    public ArrayList<Engine> getEngineExceeded() {
        return engineExceeded;
    }

    public HashMap<String, ArrayList<Double>> getParameterExceeded() {
        return parameterExceeded;
    }

    public void updateSensorStatus(Engine engine, TelemetryValue parameter) {
        double percentage = (100.0 / parameter.getMaximum() * parameter.getValue()) - 100;
        if (!(parameterExceeded.containsKey(parameter.getID()))) {
            ArrayList<Double> exceedancesInPercent = new ArrayList<>();
            exceedancesInPercent.add(percentage);
            parameterExceeded.put(parameter.getID(), exceedancesInPercent);
        } else {
            parameterExceeded.get(parameter.getID()).add(percentage);
        }
        if (!(engineExceeded.contains(engine))) {
            engineExceeded.add(engine);
        }
    }

    public String determineCodeID() {
        int engineExceededCount = engineExceeded.size();
        int parameterExceededCount = parameterExceeded.size();
        double highestPercentage = 0;
        double secondHighestPercentage = 0;
        for (String parameterID : parameterExceeded.keySet()) {
            for (double percentage : parameterExceeded.get(parameterID)) {
                if (highestPercentage <= percentage) {
                    secondHighestPercentage = highestPercentage;
                    highestPercentage = percentage;
                }
            }
        }
        engineExceeded.clear();
        parameterExceeded.clear();
        if (engineExceededCount == 1) {
            return determineCodeForOneEngine(parameterExceededCount, highestPercentage, secondHighestPercentage);
        } else return determineCodeForMoreEngines(parameterExceededCount, highestPercentage, secondHighestPercentage);
    }

    public String determineCodeForOneEngine(int parameterExceededCount, double highestPercentage, double secondHighestPercentage) {
        if (parameterExceededCount == 1) {
            if (highestPercentage >= 10) {
                if (highestPercentage >= 20) {
                    return "C09";
                }
                return "C05";
            } else return "C01";
        } else {
            if (highestPercentage >= 10 && secondHighestPercentage >= 10) {
                return "C07";
            }
            return "C03";
        }
    }

    public String determineCodeForMoreEngines(int parameterExceededCount, double highestPercentage, double secondHighestPercentage) {
        if (parameterExceededCount == 1) {
            if (highestPercentage >= 10) {
                if (highestPercentage >= 20) {
                    return "C10";
                }
                return "C06";
            } else return "C02";
        } else {
            if (highestPercentage >= 10 && secondHighestPercentage >= 10) {
                if (highestPercentage >= 20 && secondHighestPercentage >= 20) {
                    return "C11";
                }
                return "C08";
            }
            return "C04";
        }
    }
}