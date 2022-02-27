package COR;

import State.Smartphone;

import java.util.ArrayList;

public class T03 extends Team {
    public T03(Team successor) {
        setSuccessor(successor);
        setSmartphonesTeamMembers();
    }

    public void handleCode(String codeID) {
        ArrayList<String> responsibleForCodes = new ArrayList<>();
        responsibleForCodes.add("C06");
        responsibleForCodes.add("C08");
        responsibleForCodes.add("C09");
        responsibleForCodes.add("C10");
        if (responsibleForCode(codeID, responsibleForCodes)) {
            System.out.println("All members of team T03 need to come immediately  because of the error code "+ codeID +"!");
            for (Smartphone smartphone : getSmartphonesTeamMembers()) {
                smartphone.notifyAboutMeeting();
            }
        } else super.handleCode(codeID);
    }
}