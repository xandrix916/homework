package edu.hw1;

public class Problem5 {

    private boolean isPalindrome(int number) {
        if (number<10) {
            return false;
        }
        String stringNumber = Integer.toString(number);
        for (int i = 0, j = stringNumber.length() - 1; i <= (stringNumber.length() - 1) / 2
            && j > (stringNumber.length() - 1) / 2; i++, j--) {
            if (stringNumber.charAt(i) != stringNumber.charAt(j))
                return false;
        }
        return true;
    }

    private int formDescendant(int number) {
        String stringNumber = Integer.toString(number);
        int[] arrayOfPairSums = new int[(stringNumber.length() + stringNumber.length() % 2) / 2];
        int stopIndex = stringNumber.length() - stringNumber.length() % 2;
        for (int i = 0; i < stopIndex; i += 2) {
            arrayOfPairSums[i / 2] = Character.getNumericValue(stringNumber.charAt(i))
                + Character.getNumericValue(stringNumber.charAt(i + 1));
        }
        if (stringNumber.length() % 2 == 1) {
            arrayOfPairSums[(stopIndex / 2)] = stringNumber.charAt(stringNumber.length() - 1);
        }
        int descendant = 0;
        for (var i:
             arrayOfPairSums) {
            descendant = descendant * 10 + i;
        }
        return descendant;
    }

    public boolean isPalindromeDescendant(int number){
        if (isPalindrome(number)) {
            return true;
        }
        while (number >= 10) {
            number = formDescendant(number);
            if (isPalindrome(number)) {
                return true;
            }
        }
        return false;
    }
}
