package edu.hw1;

import static java.lang.Character.getNumericValue;

public class Problem1 {
    static final int TEN = 10;
    static final int FIVE = 5;
    static final int THREE = 3;
    static final int AMOUNT_OF_SECONDS_IN_MINUTE = 60;

    public int minutesToSeconds(String time) {
        int minutes = 0;
        int seconds = 0;
        if (time.length() < FIVE || time.indexOf(':') == -1 || time.length() - time.indexOf(':') != THREE) {
            return -1;
        }
        for (int i = 0; i < time.indexOf(':'); i++) {
            if (!Character.isDigit(time.charAt(i))) {
                return -1;
            }
            minutes += getNumericValue(time.charAt(i)) * (int) Math.pow(TEN, time.indexOf(':') - i - 1);
        }
        for (int i = time.indexOf(':') + 1; i < time.length(); i++) {
            if (!Character.isDigit(time.charAt(i))) {
                return -1;
            }
            seconds += getNumericValue(time.charAt(i)) * (int) Math.pow(TEN, time.length() - i - 1);
        }
        return seconds >= AMOUNT_OF_SECONDS_IN_MINUTE ? -1 : minutes * AMOUNT_OF_SECONDS_IN_MINUTE + seconds;
    }
}

