package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem4 {
    public static final String REGEX = ".*[~!@#$%^&*|].*";

    public boolean passwordMatch(String password) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
