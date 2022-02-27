package COR;

import State.Smartphone;

import java.util.ArrayList;

public class T04 extends Team {
    public T04(Team successor) {
        setSuccessor(successor);
        setSmartphonesTeamMembers();
    }

    public void handleCode(String codeID) {
        ArrayList<String> responsibleForCodes = new ArrayList<>();
        responsibleForCodes.add("C11");
        if (responsibleForCode(codeID, responsibleForCodes)) {
            System.out.println("All members of team T04 need to come immediately  because of the error code "+ codeID +"!");
            for (Smartphone smartphone : getSmartphonesTeamMembers()) {
                smartphone.notifyAboutMeeting();
            }
        } else {
            super.handleCode(codeID);
        }
    }
}