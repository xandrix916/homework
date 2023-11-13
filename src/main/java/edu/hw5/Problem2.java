package edu.hw5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Problem2 {
    boolean isFriday(LocalDate localDate) {
        int day = localDate.getDayOfMonth();
        int month = (localDate.getMonthValue() <= 2 ? 10 + localDate.getMonthValue()
            : (localDate.getMonthValue() - 2) % 12);
        int year = (month >= 11 ? localDate.getYear() - 1 : localDate.getYear());

        int rawDay = day + ((13 * month - 1) / 5) + year + (year / 4) - (year / 100) + (year / 400);
        return rawDay % 7 == 5;
    }


    List<LocalDate> getFridaysByYear(int year) {
        List<LocalDate> fridays = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            fridays.add(LocalDate.of(year, i, 13));
        }
        return fridays.stream().filter(this::isFriday).toList();
    }

    LocalDate closestFriday13(LocalDate localDate) {
        return localDate.with(new FridayAdjuster());
    }


}
