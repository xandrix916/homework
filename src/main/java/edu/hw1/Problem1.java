package edu.hw1;

import static java.lang.Character.getNumericValue;

public class Problem1 {
    private final int ten = 10;
    private final int five = 5;
    private final int three = 3;
    private final int amountOfSecondsInMinute = 60;

    public int minutesToSeconds(String time) {
        int minutes = 0;
        int seconds = 0;
        if (time.length() < five || time.indexOf(':') == -1 || time.length() - time.indexOf(':') != three) {
            return -1;
        }
        for (int i = 0; i < time.indexOf(':'); i++) {
            if (!Character.isDigit(time.charAt(i))) {
                return -1;
            }
            minutes += getNumericValue(time.charAt(i)) * (int) Math.pow(ten, time.indexOf(':') - i - 1);
        }
        for (int i = time.indexOf(':') + 1; i < time.length(); i++) {
            if (!Character.isDigit(time.charAt(i))) {
                return -1;
            }
            seconds += getNumericValue(time.charAt(i)) * (int) Math.pow(ten, time.length() - i - 1);
        }
        return seconds >= amountOfSecondsInMinute ? -1 : minutes * amountOfSecondsInMinute + seconds;
    }
}

