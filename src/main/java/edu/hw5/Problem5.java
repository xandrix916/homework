package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem5 {
    public static final String REGEX = "[А-Я]\\d{3}[А-Я]{2}\\d{3}";

    public boolean isValidated(String number) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
