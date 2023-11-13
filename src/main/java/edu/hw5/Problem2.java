package edu.hw5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Problem2 {

    private static final int THIRTEENTH = 13;
    private static final int MONTH_AMOUNT = 12;

    @SuppressWarnings("MagicNumber")
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
        for (int i = 1; i <= MONTH_AMOUNT; i++) {
            fridays.add(LocalDate.of(year, i, THIRTEENTH));
        }
        return fridays.stream().filter(this::isFriday).toList();
    }

    LocalDate closestFriday13(LocalDate localDate) {
        return localDate.with(new FridayAdjuster());
    }


}
