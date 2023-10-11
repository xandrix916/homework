package edu.hw1;

public class Problem2 {
    public int countDigits(int number) {
        if (number == 0)
            return 1;
        int counter = 0;
        while (number != 0) {
            number /= 10;
            counter++;
        }
        return counter;
    }
}
