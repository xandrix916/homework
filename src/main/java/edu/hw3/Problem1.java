package edu.hw3;

public class Problem1 {
    private static final int LOWER_CASE_CONSTANT = 219;

    private static final int UPPER_CASE_CONSTANT = 155;

    private char mirrorValue(char c) {
        if (Character.isLowerCase(c)) {
            return (char) (LOWER_CASE_CONSTANT - (int) c);
        }
        if (Character.isUpperCase(c)) {
            return (char) (UPPER_CASE_CONSTANT - (int) c);
        } else {
            return c;
        }
    }


    public String atbash(String originalString) {
        char[] charArray = originalString.toCharArray();
        StringBuilder cypheredString = new StringBuilder();
        for (var c: charArray) {
            cypheredString.append(mirrorValue(c));
        }
        return cypheredString.toString();
    }
}
