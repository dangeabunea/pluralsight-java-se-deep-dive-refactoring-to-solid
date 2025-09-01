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
                                     boolean latFirst) {
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

    public int distanceBetween(AircraftTarget a1, AircraftTarget a2) {
        return (int) Math.sqrt(
                (a1.lat() - a2.lat()) * (a1.lat() - a2.lat()) +
                (a1.lon() - a2.lon()) * (a1.lon() - a2.lon())
        );
    }
}
