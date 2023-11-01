package edu.hw3;

import java.util.HashMap;

public class Problem4 {

    public static final int DIVIDER_AND_ELDER_MULTIPLIER = 5;

    public static final int SPECIAL_CASE = 4;

    public static final int MAX_ROMAN_NUMBER = 3999;

    private HashMap<Roman, Integer> amountsOfNumbers(int countedNumber) {
        HashMap<Roman, Integer> romanIntegerHashMap = new HashMap<>();
        int processedNumber = countedNumber;

        romanIntegerHashMap.put(Roman.THOUSAND, processedNumber / Arabian.THOUSAND.getValue());
        processedNumber %= Arabian.THOUSAND.getValue();

        romanIntegerHashMap.put(Roman.FIVE_HUNDRED, (processedNumber / Arabian.HUNDRED.getValue())
            / DIVIDER_AND_ELDER_MULTIPLIER);
        romanIntegerHashMap.put(Roman.HUNDRED, (processedNumber / Arabian.HUNDRED.getValue())
            % DIVIDER_AND_ELDER_MULTIPLIER);
        processedNumber %= Arabian.HUNDRED.getValue();

        romanIntegerHashMap.put(Roman.FIFTY, (processedNumber / Arabian.TEN.getValue()) / DIVIDER_AND_ELDER_MULTIPLIER);
        romanIntegerHashMap.put(Roman.TEN, (processedNumber / Arabian.TEN.getValue()) % DIVIDER_AND_ELDER_MULTIPLIER);
        processedNumber %= Arabian.TEN.getValue();

        romanIntegerHashMap.put(Roman.FIVE, processedNumber / DIVIDER_AND_ELDER_MULTIPLIER);
        romanIntegerHashMap.put(Roman.ONE, processedNumber  % DIVIDER_AND_ELDER_MULTIPLIER);

        return romanIntegerHashMap;
    }

    private Roman additionalForFourOrNine(Roman toSwitch) {
        return switch (toSwitch) {
            case FIVE -> Roman.ONE;
            case FIFTY -> Roman.TEN;
            case FIVE_HUNDRED -> Roman.HUNDRED;
            default -> Roman.ZERO;
        };
    }

    private Roman switchToFourOrNine(Roman toSwitch) {
        return switch (toSwitch) {
            case ONE -> Roman.FIVE;
            case FIVE -> Roman.TEN;
            case TEN -> Roman.FIFTY;
            case FIFTY -> Roman.HUNDRED;
            case HUNDRED -> Roman.FIVE_HUNDRED;
            case FIVE_HUNDRED -> Roman.THOUSAND;
            default -> Roman.ZERO;
        };
    }

    private String fourNineHandler(Roman elderRoman, Roman roman, HashMap<Roman, Integer> amounts) {
        if ((amounts.get(elderRoman) * DIVIDER_AND_ELDER_MULTIPLIER + amounts.get(roman))
            % DIVIDER_AND_ELDER_MULTIPLIER != SPECIAL_CASE) {
            return elderRoman.toString().repeat(amounts.get(elderRoman)) + roman.toString().repeat(amounts.get(roman));
        } else {
            return additionalForFourOrNine(elderRoman).toString() + switchToFourOrNine(amounts.get(elderRoman) == 1
                ? elderRoman : roman).toString();
        }
    }

    private int preMod(int toConvert) throws IllegalArgumentException {
        if (!(toConvert >= 0 && toConvert <= MAX_ROMAN_NUMBER)) {
            throw new IllegalArgumentException(new Throwable("Romans didn't use such numbers"));
        }
        return toConvert;
    }

    public String convertToRoman(int toBeConverted) {
        try {
            HashMap<Roman, Integer> amountsMap = amountsOfNumbers(preMod(toBeConverted));
            return "%s%s%s%s".formatted(Roman.THOUSAND.toString().repeat(amountsMap.get(Roman.THOUSAND)),
                fourNineHandler(Roman.FIVE_HUNDRED, Roman.HUNDRED, amountsMap),
                fourNineHandler(Roman.FIFTY, Roman.TEN, amountsMap),
                fourNineHandler(Roman.FIVE, Roman.ONE, amountsMap)
            );
        } catch (IllegalArgumentException illegalArgumentException) {
            return illegalArgumentException.getCause().getMessage();
        }
    }
}
