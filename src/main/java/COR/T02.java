package COR;

import State.Smartphone;

import java.util.ArrayList;

public class T02 extends Team {
    public T02(Team successor) {
        setSuccessor(successor);
        setSmartphonesTeamMembers();
    }

    public void handleCode(String codeID) {
        ArrayList<String> responsibleForCodes = new ArrayList<>();
        responsibleForCodes.add("C02");
        responsibleForCodes.add("C04");
        responsibleForCodes.add("C05");
        responsibleForCodes.add("C06");
        responsibleForCodes.add("C07");
        responsibleForCodes.add("C08");
        if (responsibleForCode(codeID, responsibleForCodes)) {
            System.out.println("All members of team T02 need to come immediately  because of the error code "+ codeID +"!");
            for (Smartphone smartphone : getSmartphonesTeamMembers()) {
                smartphone.notifyAboutMeeting();
            }
            if ((codeID.equals("C06") || codeID.equals("C08"))) {
                super.handleCode(codeID);
            }
        }else super.handleCode(codeID);
    }
}