package COR;

import State.On;
import State.Smartphone;

import java.util.ArrayList;

public class Team {
    private Team successorTeam;
    private ArrayList<Smartphone> smartphonesTeamMembers;

    public boolean isTeamIsResponsible() {
        return teamIsResponsible;
    }

    public void setTeamIsResponsible(boolean teamIsResponsible) {
        this.teamIsResponsible = teamIsResponsible;
    }

    private boolean teamIsResponsible;

    public void handleCode(String codeID) {
        if (getSuccessor() != null) {
            getSuccessor().handleCode(codeID);
        } else {
            System.out.println("Unable to find responsible Team for Code " + codeID);
        }
    }

    protected boolean responsibleForCode(String codeID, ArrayList<String> codes) {
        if(codes.contains(codeID))
        return teamIsResponsible=true;
        else return teamIsResponsible=false;
    }

    public Team getSuccessor() {
        return successorTeam;
    }

    public void setSuccessor(Team successor) {
        this.successorTeam = successor;
    }

    public void setSmartphonesTeamMembers() {
        smartphonesTeamMembers = new ArrayList<>();
        for (int member = 0; member < 3; member++) {
            Smartphone smartphone = new Smartphone();
            smartphone.setState(new On());
            smartphonesTeamMembers.add(smartphone);
        }
    }

    public ArrayList<Smartphone> getSmartphonesTeamMembers() {
        return smartphonesTeamMembers;
    }
}