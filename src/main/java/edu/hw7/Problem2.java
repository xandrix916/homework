package edu.hw7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Problem2 {
    public int inputWrapper(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Argument below zero");
        }
        return n;
    }

    public BigInteger getFactorial(int n) {
        try {
            n = inputWrapper(n);
        } catch (IllegalArgumentException e) {
            return null;
        }
        List<BigInteger> bigIntegerRange = new ArrayList<>();
        IntStream.range(2, n + 1)
            .forEach(i -> bigIntegerRange.add(new BigInteger(String.valueOf(i))));
        var factorial = bigIntegerRange
            .parallelStream()
            .reduce(BigInteger::multiply);
        return factorial.orElse(new BigInteger("1"));
    }
}
