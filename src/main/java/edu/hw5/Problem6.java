package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem6 {
    public static final String REGEX_TEMPLATE = ".*%s.*";

    public boolean isSubsequence(String sequence, String potentialSubSequence) {
        Pattern pattern = Pattern.compile(REGEX_TEMPLATE.formatted(potentialSubSequence));
        Matcher matcher = pattern.matcher(sequence);
        return matcher.matches();
    }
}
