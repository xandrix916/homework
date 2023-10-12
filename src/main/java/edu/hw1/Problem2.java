package edu.hw1;

import static edu.hw1.Problem1.TEN;

public class Problem2 {


    public int countDigits(int number) {
        if (number == 0) {
            return 1;
        }
        int counter = 0;
        int n = number;
        while (n != 0) {
            n /= TEN;
            counter++;
        }
        return counter;
    }
}
