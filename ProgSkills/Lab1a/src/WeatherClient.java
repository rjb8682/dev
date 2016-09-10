/**
 * @author Robert Bond III <rjb8682@rit.edu>
 */
public class WeatherClient extends Observer<String> {
    // An identifier for this Observer. This is purely for cosmetic use to showcase
    // the client being removed when it stops observing the Subject
    private int id;

    // The constructor for the WeatherClient.
    //      int id: An identifier for the Client
    //      Weather w: The subject that you want to be notified about
    // When constructed, this announces that it has been created in System.out
    public WeatherClient(int id, Weather w) {
        this.id = id;
        this.subject = w;
        this.subject.startObserving(this);
        System.out.println("New Client(" + this.id + ")! Looking for weather changes...");
    }

    // Method called when the Weather subject has updated it's state.
    //      String state: the new state that the subject has changed to
    // Prints to System.out that the subject has changed it's state and adds what the state now is
    public void notifyObserver() {
        System.out.println("(" + this.id + ") Weather changed to: " + this.subject.getCurrentState());
    }
}
