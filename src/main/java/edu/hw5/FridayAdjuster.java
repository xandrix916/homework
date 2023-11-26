package edu.hw5;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.List;

public class FridayAdjuster implements TemporalAdjuster {


    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate localDate = LocalDate.from(temporal);
        LocalDate closestFriday13 = null;
        Problem2 problem2 = new Problem2();
        int year = localDate.getYear();
        List<LocalDate> fridays13 = problem2.getFridaysByYear(year).stream()
            .filter(date -> date.isAfter(localDate))
            .toList();
        while (closestFriday13 == null) {
            for (var friday: fridays13) {
                closestFriday13 = friday;
                break;
            }
            year += 1;
            fridays13 = problem2.getFridaysByYear(year);
        }
        return closestFriday13;
    }
}
