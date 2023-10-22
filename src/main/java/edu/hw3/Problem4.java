package edu.hw3;

import java.util.HashMap;

public class Problem4 {
    private enum Roman {
        ZERO (""),
        ONE ("I"),
        FIVE ("V"),
        TEN ("X"),
        FIFTY ("L"),
        HUNDRED ("C"),
        FIVE_HUNDRED ("D"),
        THOUSAND ("M");
        private final String romanNumber;
        Roman(String romanNumber) {
            this.romanNumber = romanNumber;
        }

        @Override
        public String toString() {
            return this.romanNumber;
        }
    }

    private enum Arabian {
        TEN (10),
        HUNDRED (100),
        THOUSAND (1000);
        private final int arabianNumber;
        Arabian(int arabianNumber) {
            this.arabianNumber = arabianNumber;
        }

        public int getValue() {
            return arabianNumber;
        }

        @Override
        public String toString() {
            return String.valueOf(arabianNumber);
        }
    }

    private HashMap<Roman, Integer> amountsOfNumbers(int countedNumber) {
        HashMap<Roman, Integer> romanIntegerHashMap = new HashMap<>();
        int processedNumber = countedNumber;

        romanIntegerHashMap.put(Roman.THOUSAND, processedNumber / Arabian.THOUSAND.getValue());
        processedNumber %= Arabian.THOUSAND.getValue();

        romanIntegerHashMap.put(Roman.FIVE_HUNDRED, (processedNumber / Arabian.HUNDRED.getValue()) / 5);
        romanIntegerHashMap.put(Roman.HUNDRED, (processedNumber / Arabian.HUNDRED.getValue()) % 5);
        processedNumber %= Arabian.HUNDRED.getValue();

        romanIntegerHashMap.put(Roman.FIFTY, (processedNumber / Arabian.TEN.getValue()) / 5);
        romanIntegerHashMap.put(Roman.TEN, (processedNumber / Arabian.TEN.getValue()) % 5);
        processedNumber %= Arabian.TEN.getValue();

        romanIntegerHashMap.put(Roman.FIVE, processedNumber / 5);
        romanIntegerHashMap.put(Roman.ONE, processedNumber  % 5);

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
        if ((amounts.get(elderRoman) * 5 + amounts.get(roman)) % 5 != 4) {
            return elderRoman.toString().repeat(amounts.get(elderRoman)) + roman.toString().repeat(amounts.get(roman));
        }
        else {
            return additionalForFourOrNine(elderRoman).toString() + switchToFourOrNine(amounts.get(elderRoman) == 1
                ? elderRoman : roman).toString();
        }
    }

    private int preMod(int toConvert) throws IllegalArgumentException{
        if (!(toConvert >= 0 && toConvert < 4000)) {
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
