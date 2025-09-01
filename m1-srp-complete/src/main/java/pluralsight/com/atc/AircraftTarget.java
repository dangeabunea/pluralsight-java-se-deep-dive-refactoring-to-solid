package pluralsight.com.atc;

/*
Lat and lon are expressed in nautical miles from center of radar
 */
public record AircraftTarget(String id, int lat, int lon) {
}
