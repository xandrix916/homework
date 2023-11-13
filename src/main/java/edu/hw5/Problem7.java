package edu.hw5;

import java.util.regex.Pattern;

public class Problem7 {
    public static final String THIRD_IS_ZERO = "[01]{2}0[01]*";
    public static final String FIRST_EQUALS_LAST = "(0[01]*0)|(1[01]*1)";
    public static final String BETWEEN_ONE_AND_THREE = "[01]{1,3}";

    public boolean checkString(String toCheck, RegexCase regexCase) {
        return (switch (regexCase) {
            case THIRD_IS_ZERO -> Pattern.compile(THIRD_IS_ZERO);
            case FIRST_EQUALS_LAST -> Pattern.compile(FIRST_EQUALS_LAST);
            case BETWEEN_ONE_AND_THREE -> Pattern.compile(BETWEEN_ONE_AND_THREE);
        }).matcher(toCheck).matches();
    }

    public enum RegexCase {
        THIRD_IS_ZERO,
        FIRST_EQUALS_LAST,
        BETWEEN_ONE_AND_THREE
    }
}
