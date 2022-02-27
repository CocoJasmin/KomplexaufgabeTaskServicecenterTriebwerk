package COR;

import State.Smartphone;

import java.util.ArrayList;

public class T05 extends Team {
    public T05() {
        setSmartphonesTeamMembers();
    }

    public void handleCode(String codeID) {
        ArrayList<String> responsibleForCodes = new ArrayList<>();
        responsibleForCodes.add("C12");
        if (responsibleForCode(codeID, responsibleForCodes)) {
            System.out.println("All members of team T05 need to come immediately  because of the error code "+ codeID +"!");
            for (Smartphone smartphone : getSmartphonesTeamMembers()) {
                smartphone.notifyAboutMeeting();
            }
        } else super.handleCode(codeID);
    }
}