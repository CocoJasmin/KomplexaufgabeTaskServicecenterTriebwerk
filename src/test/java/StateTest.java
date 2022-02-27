import State.Employe;
import State.Off;
import State.On;
import State.Smartphone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StateTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    Smartphone smartphone;

    @BeforeEach
    public void setup() {
        this.smartphone = new Smartphone();
        //smartphone & smartphone owner exist
        assertNotNull(smartphone);
        assertNotNull(smartphone.getOwner());
        assertEquals(smartphone.getOwner().getClass(), Employe.class);
        System.setOut(new PrintStream(output));
    }

    @Test
    @Order(1)
    public void pressButtonInStateOn() {
        smartphone.setState(new On());
        assertEquals(smartphone.getState().getClass(), On.class);
        smartphone.pressButton();
        assertEquals(smartphone.getState().getClass(), Off.class);
    }

    @Test
    @Order(2)
    public void pressButtonInStateOff() {
        smartphone.setState(new Off());
        assertEquals(smartphone.getState().getClass(), Off.class);
        smartphone.pressButton();
        assertEquals(smartphone.getState().getClass(), On.class);
    }

    @Test
    @Order(3)
    public void meetingNotificationWhenSmartphoneIsOn() {
        smartphone.setState(new On());
        assertEquals(smartphone.getState().getClass(), On.class);
        smartphone.notifyAboutMeeting();
        assertEquals("We have a wonderful meeting here!\n", output.toString());
    }

    @Test
    @Order(4)
    public void meetingNotificationWhenSmartphoneIsOff() {
        smartphone.setState(new Off());
        assertEquals(smartphone.getState().getClass(), Off.class);
        smartphone.notifyAboutMeeting();
        assertEquals("This is the automatic answering machine. Please, leave your message after the signal.\n", output.toString());
    }
}