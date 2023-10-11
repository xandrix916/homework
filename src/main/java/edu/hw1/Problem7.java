package edu.hw1;

public class Problem7 {
    private int countOnes(String string) {
        return string.length() - string.replace("1", "").length();
    }

    private int[] formIndexes(int n) {
        String binaryNumber = Integer.toBinaryString(n);
        int[] indexes = new int[countOnes(binaryNumber)];
        int counter = 0;
        int iterator = 0;
        while (counter < indexes.length && iterator < binaryNumber.length()) {
            if (binaryNumber.charAt(iterator) == '1') {
                indexes[counter] = iterator;
                counter++;
            }
            iterator++;
        }
        return indexes;
    }

    private int formInt(int[] indexes, int n) {
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = n - indexes[i] - 1;
        }
        int resultSum = 0;
        for (int index : indexes) {
            resultSum += (int) Math.pow(2, index);
        }
        return resultSum;
    }

    int rotateLeft(int n, int shift) {
        int[] indexes = formIndexes(n).clone();
        int binLengthMod = Integer.toBinaryString(n).length();
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] - shift < 0) {
                indexes[i] = (indexes[i] - shift) + binLengthMod;
            }
            else {
                indexes[i] -= shift;
            }
        }
        return formInt(indexes, binLengthMod);
    }

    int rotateRight(int n, int shift) {
        int[] indexes = formIndexes(n).clone();
        int binLengthMod = Integer.toBinaryString(n).length();
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] + shift >= binLengthMod) {
                indexes[i] = (indexes[i] + shift) - binLengthMod;
            }
            else {
                indexes[i] += shift;
            }
        }
        return formInt(indexes, binLengthMod);
    }
}
