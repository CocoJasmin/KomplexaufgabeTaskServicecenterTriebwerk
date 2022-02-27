package State;

public class Smartphone {
    private ISmartphoneState state;

    public Employe getOwner() {
        return owner;
    }

    private Employe owner;

    public Smartphone() {
        this.owner = new Employe();
    }

    public ISmartphoneState getState() {
        return state;
    }

    public void setState(ISmartphoneState state) {
        this.state = state;
    }

    public void pressButton() {
        state.pressButton(this);
    }

    public void notifyAboutMeeting() {
        if (state.toString().equals("On")) {
            owner.getMeetingNotification();
        } else
            System.out.print("This is the automatic answering machine. Please, leave your message after the signal.\n");
    }
}