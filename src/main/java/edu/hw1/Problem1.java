package edu.hw1;

import static java.lang.Character.getNumericValue;

public class Problem1 {
    public int minutesToSeconds(String time) {
        int minutes = 0, seconds = 0;
        if (time.length() < 5 || time.indexOf(':') == -1 || time.length() - time.indexOf(':') != 3)
            return -1;
        for (int i = 0; i < time.indexOf(':'); i++) {
            if (!Character.isDigit(time.charAt(i)))
                return -1;
            minutes += getNumericValue(time.charAt(i)) * (int)Math.pow(10, time.indexOf(':') - i - 1);
        }
        for (int i = time.indexOf(':') + 1; i < time.length(); i++) {
            if (!Character.isDigit(time.charAt(i)))
                return -1;
            seconds += getNumericValue(time.charAt(i)) * (int)Math.pow(10, time.length() - i - 1);
        }
        if (seconds >= 60)
            return -1;
        else
            return minutes * 60 + seconds;
    }
}

