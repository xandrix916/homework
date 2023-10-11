package edu.hw1;

public class Problem2 {
    private final int ten = 10;

    public int countDigits(int number) {
        if (number == 0) {
            return 1;
        }
        int counter = 0;
        int n = number;
        while (n != 0) {
            n /= ten;
            counter++;
        }
        return counter;
    }
}
