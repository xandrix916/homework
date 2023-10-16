package edu.project1;

import java.util.ArrayList;
import java.util.HashMap;

public class StringProcessor {
    private final String originString;
    private String stringWithMask;
    private final HashMap<Character, ArrayList<Integer>> indexMap = new HashMap<>();

    private void createMap(String string) {
        for (int i = 0; i < string.length(); i++) {
            Character symbol = string.charAt(i);
            if (indexMap.containsKey(symbol)) {
                indexMap.get(symbol).add(i);
            } else {
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(i);
                indexMap.put(symbol, arrayList);
            }
        }
    }

    public StringProcessor(String originString) {
        this.originString = originString.toLowerCase();
        createMap(originString);
        stringWithMask = "_".repeat(originString.length());
    }

    public static boolean anyOddDigits(String string) {
        for (var i: string.toCharArray()) {
            if (!Character.isLetter(i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean anyOddSymbols(String string) {
        for (var i: string.toCharArray()) {
            if (Character.isLetter(i)) {
                return true;
            }
        }
        return false;
    }

    private void wheelOfFortune(Character symbol) {
        Character processedSymbol = symbol;
        if (Character.isTitleCase(symbol)) {
            processedSymbol = Character.toLowerCase(symbol);
        }
        if (indexMap.containsKey(processedSymbol)) {
            StringBuilder tempString = new StringBuilder(stringWithMask);
            for (var i: indexMap.get(processedSymbol)) {
                tempString.setCharAt(i, originString.charAt(i));
            }
            stringWithMask = tempString.toString();
        }
    }

    private void vaBank(String word) {
        if (originString.equalsIgnoreCase(word)) {
            stringWithMask = originString;
        }
    }

    private void updateMask(String toUpdate) {
        if (toUpdate.length() == 1) {
            wheelOfFortune(toUpdate.charAt(0));
        } else {
            vaBank(toUpdate);
        }
    }

    public String getStringWithMask(String toUpdate) {
        updateMask(toUpdate);
        return stringWithMask;
    }

    public String getStringWithMask() {
        return stringWithMask;
    }


}
