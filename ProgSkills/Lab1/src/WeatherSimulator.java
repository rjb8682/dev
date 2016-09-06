/**
 * @author Robert Bond III <rjb8682@rit.edu>
 */
public class WeatherSimulator {
    // The main method ran when running the WeatherSimulator.
    // This creates a Subject, attaches one Observer to it, updates it's contents,
    // attaches another Observer to it, updates again, removes the first Observer,
    // then updates for a final time.
    public static void main(String[] args) {
        Weather weatherMaker = new Weather();
        WeatherClient client1 = new WeatherClient(1, weatherMaker);

        weatherMaker.changeWeather();

        WeatherClient client2 = new WeatherClient(2, weatherMaker);

        weatherMaker.changeWeather();

        System.out.println("Removing Client1");
        weatherMaker.stopObserving(client1);

        weatherMaker.changeWeather();
    }
}
