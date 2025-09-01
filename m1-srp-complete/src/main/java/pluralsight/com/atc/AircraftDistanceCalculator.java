package pluralsight.com.atc;

public class AircraftDistanceCalculator {
    public int calculateDistanceBetween(AircraftTarget a1, AircraftTarget a2) {
        return (int) Math.sqrt(
                (a1.lat() - a2.lat()) * (a1.lat() - a2.lat()) +
                (a1.lon() - a2.lon()) * (a1.lon() - a2.lon())
        );
    }
}
