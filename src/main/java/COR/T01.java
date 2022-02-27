package COR;

import State.Smartphone;

import java.util.ArrayList;

public class T01 extends Team {
    public T01(Team successor) {
        setSuccessor(successor);
        setSmartphonesTeamMembers();
    }

    public void handleCode(String codeID) {
        ArrayList<String> responsibleForCodes = new ArrayList<>();
        responsibleForCodes.add("C01");
        responsibleForCodes.add("C03");
        responsibleForCodes.add("C05");
        responsibleForCodes.add("C06");
        responsibleForCodes.add("C07");
        responsibleForCodes.add("C08");
        if (responsibleForCode(codeID, responsibleForCodes)) {
            System.out.println("All members of team T01 need to come immediately  because of the error code "+ codeID +"!");
            for (Smartphone smartphone : getSmartphonesTeamMembers()) {
                smartphone.notifyAboutMeeting();
            }
            if (!(codeID.equals("C01")|| codeID.equals("C03"))) {
                super.handleCode(codeID);
            }
        }else super.handleCode(codeID);
    }
}