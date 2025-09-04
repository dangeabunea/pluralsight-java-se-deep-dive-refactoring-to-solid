package com.pluralsight.atc;

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
                                     CoordinateOutputFormatter outputFormatter) {
        // Cannot exceed max range
        int effectiveRange = Math.min(range, maxRange);

        var aircraftInRange = allAircraft
                .stream()
                .filter(a -> {
                    var distance = (int) Math.sqrt(
                            (this.originLat - a.lat()) * (this.originLat - a.lat()) +
                            (this.originLon - a.lon()) * (this.originLon - a.lon()));
                    return distance <= effectiveRange;
                })
                .toList();

        return outputFormatter.parse(aircraftInRange);
    }
}
