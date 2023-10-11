package edu.hw1;

public class Problem6 {
    private int Kaprekar(int number) {
        char[] arrayOfDigits = Integer.toString(number).toCharArray();
        char[] ascendingSort = arrayOfDigits.clone(), descendingSort = arrayOfDigits.clone();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < ascendingSort.length - 1; i++) {
                if (ascendingSort[i] > ascendingSort[i+1]) {
                    char temp = ascendingSort[i];
                    ascendingSort[i] = ascendingSort[i+1];
                    ascendingSort[i+1] = temp;
                }
            }
        }
        for (int j = 0; j < 3; j++) {
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
            ascendingSortNumber = ascendingSortNumber * 10 + Character.getNumericValue(i);
        }
        for (var i:
            descendingSort) {
            descendingSortNumber = descendingSortNumber * 10 + Character.getNumericValue(i);
        }
        return descendingSortNumber - ascendingSortNumber;
    }

    public int countK(int number) {
        if (number == 6174)
            return 0;
        if (Kaprekar(number) == 6174)
            return 1;
        return 1 + countK(Kaprekar(number));
    }
}
