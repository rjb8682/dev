import java.util.Random;

/**
 * @author Robert Bond III <rjb8682@rit.edu>
 */
public class Weather extends Subject<String> {
    // A list of weather strings to be used as the state for this Subject.
    private String[] weather = new String[] {"Rain", "Snow", "Overcast", "Clear"};

    // The constructor for the Weather subject. Calls the changeWeather function
    // at the beginning to set the initial state
    public Weather() {
        this.changeWeather();
    }

    // Changes the state of this subject. Randomly selects a new string from the weather
    // list and calls the changeState method with that string.
    public void changeWeather() {
        this.changeState(weather[new Random().nextInt(weather.length)]);
    }
}
