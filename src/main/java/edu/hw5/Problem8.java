package edu.hw5;

import java.util.regex.Pattern;

public class Problem8 {
    public static final String ODD_LENGTH_REGEX = "([01]{2})*[01]";
    public static final String TWO_OPTIONS = "(0([01]{2})*)|(1([01]{2})*[01])";
    public static final String MOD_THREE_ZERO = "(0(1)*0(1)*0)(1)*((0(1)*0(1)*0)(1)*)*";
    public static final String ANY_EXCEPT_ELEVENS = "(0[01]*)|(10[01]*)|(110[01]*)|(111[01][01]*)|(1)";
    public static final String ANY_ODD_SYMBOL_IS_ONE = "((1[01])(1[01])*)|(1([01]1)*)";  //10101111111110101
    public static final String MORE_TWO_ZEROS_NO_MORE_ONE_ONE = "(00(0)*)|((0)*(100|010|001)(0)*)"; //001000000000000000000000

    public boolean checkString(String toCheck, RegexCase regexCase) {
        return (switch (regexCase) {
            case ODD_LENGTH_REGEX -> Pattern.compile(ODD_LENGTH_REGEX);
            case TWO_OPTIONS -> Pattern.compile(TWO_OPTIONS);
            case MOD_THREE_ZERO -> Pattern.compile(MOD_THREE_ZERO);
            case ANY_EXCEPT_ELEVENS -> Pattern.compile(ANY_EXCEPT_ELEVENS);
            case ANY_ODD_SYMBOL_IS_ONE -> Pattern.compile(ANY_ODD_SYMBOL_IS_ONE);
            case MORE_TWO_ZEROS_NO_MORE_ONE_ONE -> Pattern.compile(MORE_TWO_ZEROS_NO_MORE_ONE_ONE);
        }).matcher(toCheck).matches();
    }

    public enum RegexCase {
        ODD_LENGTH_REGEX,
        TWO_OPTIONS,
        MOD_THREE_ZERO,
        ANY_EXCEPT_ELEVENS,
        ANY_ODD_SYMBOL_IS_ONE,
        MORE_TWO_ZEROS_NO_MORE_ONE_ONE
    }
}
