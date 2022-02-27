import COR.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CORTest {
    Team t05;
    Team t04;
    Team t03;
    Team t02;
    Team t01;
    ArrayList<String> responsibleForCodes;
    ArrayList<String> notResponsibleForCodes;

    @BeforeEach
    public void setup() {
        responsibleForCodes = new ArrayList<>();
        notResponsibleForCodes = new ArrayList<>();
        //all Teams exist
        t05 = new T05();
        t04 = new T04(t05);
        t03 = new T03(t04);
        t02 = new T02(t03);
        t01 = new T01(t02);
        assertNotNull(t01);
        assertNotNull(t02);
        assertNotNull(t03);
        assertNotNull(t04);
        assertNotNull(t05);
    }

    @Test
    @Order(1)
    public void correctSuccessors() {
        assertEquals(t01.getSuccessor().getClass(), t02.getClass());
        assertEquals(t02.getSuccessor().getClass(), t03.getClass());
        assertEquals(t03.getSuccessor().getClass(), t04.getClass());
        assertEquals(t04.getSuccessor().getClass(), t05.getClass());
        assertNull(t05.getSuccessor());
    }

    @Test
    @Order(2)
    public void checkIfT01HandlesCorrectCodes() {
        notResponsibleForCodes.add("C02");
        notResponsibleForCodes.add("C04");
        notResponsibleForCodes.add("C09");
        notResponsibleForCodes.add("C10");
        notResponsibleForCodes.add("C11");
        notResponsibleForCodes.add("C12");
        for (String codeID : notResponsibleForCodes) {
            t01.handleCode(codeID);
            assertFalse(t01.isTeamIsResponsible());
        }
        responsibleForCodes.add("C01");
        responsibleForCodes.add("C03");
        responsibleForCodes.add("C05");
        responsibleForCodes.add("C06");
        responsibleForCodes.add("C07");
        responsibleForCodes.add("C08");
        for (String codeID : responsibleForCodes) {
            t01.handleCode(codeID);
            assertTrue(t01.isTeamIsResponsible());
        }
    }

    @Test
    @Order(3)
    public void checkIfT02HandlesCorrectCodes() {
        notResponsibleForCodes.add("C01");
        notResponsibleForCodes.add("C03");
        notResponsibleForCodes.add("C09");
        notResponsibleForCodes.add("C10");
        notResponsibleForCodes.add("C11");
        notResponsibleForCodes.add("C12");
        for (String codeID : notResponsibleForCodes) {
            t01.handleCode(codeID);
            assertFalse(t02.isTeamIsResponsible());
        }
        responsibleForCodes.add("C02");
        responsibleForCodes.add("C04");
        responsibleForCodes.add("C05");
        responsibleForCodes.add("C06");
        responsibleForCodes.add("C07");
        responsibleForCodes.add("C08");
        for (String codeID : responsibleForCodes) {
            t01.handleCode(codeID);
            assertTrue(t02.isTeamIsResponsible());
        }
    }

    @Test
    @Order(4)
    public void checkIfT03HandlesCorrectCodes() {
        notResponsibleForCodes.add("C01");
        notResponsibleForCodes.add("C02");
        notResponsibleForCodes.add("C03");
        notResponsibleForCodes.add("C04");
        notResponsibleForCodes.add("C05");
        notResponsibleForCodes.add("C07");
        notResponsibleForCodes.add("C11");
        notResponsibleForCodes.add("C12");
        for (String codeID : notResponsibleForCodes) {
            t01.handleCode(codeID);
            assertFalse(t03.isTeamIsResponsible());
        }
        responsibleForCodes.add("C06");
        responsibleForCodes.add("C08");
        responsibleForCodes.add("C09");
        responsibleForCodes.add("C10");
        for (String codeID : responsibleForCodes) {
            t01.handleCode(codeID);
            assertTrue(t03.isTeamIsResponsible());
        }
    }

    @Test
    @Order(5)
    public void checkIfT04HandlesCorrectCodes() {
        notResponsibleForCodes.add("C01");
        notResponsibleForCodes.add("C02");
        notResponsibleForCodes.add("C03");
        notResponsibleForCodes.add("C04");
        notResponsibleForCodes.add("C05");
        notResponsibleForCodes.add("C06");
        notResponsibleForCodes.add("C07");
        notResponsibleForCodes.add("C08");
        notResponsibleForCodes.add("C09");
        notResponsibleForCodes.add("C10");
        notResponsibleForCodes.add("C12");
        for (String codeID : notResponsibleForCodes) {
            t01.handleCode(codeID);
            assertFalse(t04.isTeamIsResponsible());
        }
        t01.handleCode("C11");
        assertTrue(t04.isTeamIsResponsible());
    }

    @Test
    @Order(6)
    public void checkIfT05HandlesCorrectCodes() {
        notResponsibleForCodes.add("C01");
        notResponsibleForCodes.add("C02");
        notResponsibleForCodes.add("C03");
        notResponsibleForCodes.add("C04");
        notResponsibleForCodes.add("C05");
        notResponsibleForCodes.add("C06");
        notResponsibleForCodes.add("C07");
        notResponsibleForCodes.add("C08");
        notResponsibleForCodes.add("C09");
        notResponsibleForCodes.add("C10");
        notResponsibleForCodes.add("C11");
        for (String codeID : notResponsibleForCodes) {
            t01.handleCode(codeID);
            assertFalse(t05.isTeamIsResponsible());
        }
        t01.handleCode("C12");
        assertTrue(t05.isTeamIsResponsible());
    }


}