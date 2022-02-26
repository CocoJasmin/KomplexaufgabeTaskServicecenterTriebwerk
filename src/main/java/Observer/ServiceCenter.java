package Observer;

import Basis.Engine;
import State.Team;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceCenter implements ISensorChangedListener {
    private ArrayList<Engine> engineExceeded;
    private HashMap<String, ArrayList<Integer>> parameterExceeded;
    private ArrayList<Team> teamList;

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    public void addTeam(Team team) {
        this.teamList.add(team);
    }

    public void removeTeam(Team team) {
        this.teamList.remove(team);
    }

    public void updateSensorStatus(Engine engine, TelemetryValue parameter) {
        int percentage = (100 / parameter.getMaximum()) * parameter.getValue();
        if (!(parameterExceeded.containsKey(parameter.getID()))) {
            ArrayList<Integer> exceedancesInPercent = new ArrayList<>();
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
        String codeID;
        int engineExceededCount = engineExceeded.size();
        int parameterExceededCount = parameterExceeded.size();
        int highestPercentage = 0;
        for (String id : parameterExceeded.keySet()) {
            for (int percentage : parameterExceeded.get(id)) {
                if (highestPercentage < percentage) {
                    highestPercentage = percentage;
                }
            }
        }
        if (engineExceededCount == 1) {
            return codeID = determineCodeForOneEngine(parameterExceededCount, highestPercentage);
        } else return codeID = determineCodeForMoreEngines(parameterExceededCount, highestPercentage);
    }

    public String determineCodeForOneEngine(int parameterExceededCount, int highestPercentage) {
        String codeID;
        if (parameterExceededCount == 1) {
            if (highestPercentage >= 10) {
                if (highestPercentage >= 20) {
                    return codeID = "C09";
                }
                return codeID = "C05";
            } else return codeID = "C01";
        } else {
            if (highestPercentage >= 10) {
                return codeID = "C07";
            }
            return codeID = "C02";
        }
    }

    public String determineCodeForMoreEngines(int parameterExceededCount, int highestPercentage) {
        String codeID;
        if (parameterExceededCount == 1) {
            if (highestPercentage >= 10) {
                if (highestPercentage >= 20) {
                    return codeID = "C10";
                }
                return codeID = "C06";
            } else return codeID = "C01";
        } else {
            if (highestPercentage >= 10) {
                if (highestPercentage >= 20) {
                    return codeID = "C11";
                }
                return codeID = "C08";
            }
            return codeID = "C04";
        }
    }
}