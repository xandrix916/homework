package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;

public class Problem3 {
    public static final double PHI = 1.6180339887498948482;

    public <T> HashMap<T, Integer> freqDict(ArrayList<T> arrayList) {
        HashMap<T, Integer> hashMap = new HashMap<>();
        for (var s: arrayList) {
            if (hashMap.containsKey(s)) {
                hashMap.replace(s, hashMap.get(s) + 1);
            } else {
                hashMap.put(s, 1);
            }
        }
        return hashMap;
    }
}
