package pluralsight.com.atc;
import java.util.List;

public class Radar {
    private final int originLat;
    private final int originLon;
    private final int maxRange;

    public Radar(int originLat, int originLon, int maxRange) {
        this.originLat = originLat;
        this.originLon = originLon;
        this.maxRange = maxRange;
    }

    public String getAircraftInRange(int range,
                                     List<AircraftTarget> allAircraft,
                                     boolean latFirst) {
        // Cannot exceed max range
        int effectiveRange = Math.min(range, maxRange);

        var aircraftInRange = allAircraft
                .stream()
                .filter(a -> {
                    var distance = (int) Math.sqrt(
                            (originLat - a.lat()) * (originLat - a.lat()) +
                                    (originLon - a.lon()) * (originLon - a.lon()));
                    return distance <= effectiveRange;
                })
                .toList();

        var sb = new StringBuilder();
        if (latFirst) {
            aircraftInRange.forEach(a -> sb
                    .append("[")
                    .append(a.lat())
                    .append(" ")
                    .append(a.lon())
                    .append("] "));
        } else {
            aircraftInRange.forEach(a -> sb
                    .append("[")
                    .append(a.lon())
                    .append(" ")
                    .append(a.lat())
                    .append("] "));
        }

        return sb.toString();
    }

    public int distanceBetween(AircraftTarget firstTarget, AircraftTarget secondTarget) {
        return (int) Math.sqrt(
                (firstTarget.lat() - secondTarget.lat()) * (firstTarget.lat() - secondTarget.lat()) +
                        (firstTarget.lon() - secondTarget.lon()) * (firstTarget.lon() - secondTarget.lon())
        );
    }
}
