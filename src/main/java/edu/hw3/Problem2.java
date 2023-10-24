package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Problem2 {
    public static final ArrayList<String> NULL_ARRAY = new ArrayList<>(0);

    private boolean isNormalCluster(String subString) {
        if (subString.charAt(0) == subString.charAt(subString.length() - 1)) {
            return false;
        }
        if (subString.length() == 2) {
            return subString.equals("()");
        }
        ArrayList<String> subCluster = clusterizeProcessor(subString.substring(1, subString.length() - 1));
        if (subCluster.equals(NULL_ARRAY)) {
            for (int i = 0, j = subString.length() - 1; i < subString.length() && j >= 0; i++, j--) {
                if (subString.charAt(i) == subString.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }


    private int findClosureIndex(String string, int index) {
        for (int i = index + 1; i < string.length(); i += 2) {
            if (isNormalCluster(string.substring(index, i + 1))) {
                return i;
            }
        }
        return -1;
    }


    private ArrayList<String> clusterizeProcessor(String string) {
        ArrayList<String> bracketsList = new ArrayList<>();
        int indexOfChar = 0;
        if (string.equals("()")) {
            return new ArrayList<>(List.of("()"));
        }
        if (string.charAt(0) == string.charAt(string.length() - 1)) {
            return NULL_ARRAY;
        }
        while (indexOfChar < string.length() - 1) {
            int closureIndex = findClosureIndex(string, indexOfChar);
            if (closureIndex != -1) {
                bracketsList.add(string.substring(indexOfChar, closureIndex + 1));
                indexOfChar = closureIndex + 1;
            } else {
                return NULL_ARRAY;
            }
        }
        return bracketsList;
    }

    private String preModeration(String string) throws IllegalArgumentException {
        if (string.length() - string.replace("(", "").length()
            - string.replace(")", "").length() != 0) {
            throw new IllegalArgumentException(new Throwable("odd symbols"));
        }
        if (string.charAt(0) == string.charAt(string.length() - 1) || string.length() % 2 == 1) {
            throw new IllegalArgumentException(new Throwable("an obviously unclusterized sequence"));
        }
        return string;
    }

    public ArrayList<String> clusterize(String string) {
        try {
            return clusterizeProcessor(preModeration(string));
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ArrayList<>(List.of(("Program will be stop due"
                + " to %s in given string").formatted(illegalArgumentException.getCause().getMessage())));
        }
    }
}
