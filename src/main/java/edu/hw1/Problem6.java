package edu.hw1;

public class Problem6 {
    private final int three = 3;
    private final int ten = 10;
    private final int kaprekarNumber = 6174;

    private int kaprekar(int number) {
        char[] arrayOfDigits = Integer.toString(number).toCharArray();
        char[] ascendingSort = arrayOfDigits.clone();
        char[] descendingSort = arrayOfDigits.clone();
        for (int j = 0; j < three; j++) {
            for (int i = 0; i < ascendingSort.length - 1; i++) {
                if (ascendingSort[i] > ascendingSort[i+1]) {
                    char temp = ascendingSort[i];
                    ascendingSort[i] = ascendingSort[i+1];
                    ascendingSort[i+1] = temp;
                }
            }
        }
        for (int j = 0; j < three; j++) {
            for (int i = 0; i < descendingSort.length - 1; i++) {
                if (descendingSort[i] < descendingSort[i+1]) {
                    char temp = descendingSort[i];
                    descendingSort[i] = descendingSort[i+1];
                    descendingSort[i+1] = temp;
                }
            }
        }
        int ascendingSortNumber = 0, descendingSortNumber = 0;
        for (var i:
            ascendingSort) {
            ascendingSortNumber = ascendingSortNumber * ten + Character.getNumericValue(i);
        }
        for (var i:
            descendingSort) {
            descendingSortNumber = descendingSortNumber * ten + Character.getNumericValue(i);
        }
        return descendingSortNumber - ascendingSortNumber;
    }

    public int countK(int number) {
        if (number == kaprekarNumber)
            return 0;
        if (kaprekar(number) == kaprekarNumber)
            return 1;
        return 1 + countK(kaprekar(number));
    }
}
