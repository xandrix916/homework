package edu.hw1;

public class Problem3 {
    public boolean isNestable(int[] a1, int[] a2) {
        if (a2.length == 0 || a1.length == 0)
            return false;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        for (var i: a1) {
            min1 = Math.min(min1, i);
            max1 = Math.max(max1, i);
        }
        for (var i: a2) {
            min2 = Math.min(min2, i);
            max2 = Math.max(max2, i);
        }
        return (min1 > min2 && max1 < max2);
    }
}
