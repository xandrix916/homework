package edu.hw3;

import java.util.Comparator;
import java.util.TreeMap;

@SuppressWarnings("InnerTypeLast")
public class Problem7 {
    public static class NullComparator<T extends Comparable<T>> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            if (o1 == null && o2 != null) {
                return -1;
            }
            if (o1 != null && o2 == null) {
                return 1;
            }
            if (o1 != null) {
                return o1.compareTo(o2);
            }
            return 0;
        }
    }

    public <K extends Comparable<K>, V> TreeMap<K, V> makingCrutch(K[] keysArray, V[] valuesArray, V testValue) {
        TreeMap<K, V> treeMap = new TreeMap<>(new NullComparator<>());
        if (keysArray.length != valuesArray.length) {
            return treeMap;
        }
        for (int i = 0; i < keysArray.length; i++) {
            treeMap.put(keysArray[i], valuesArray[i]);
        }
        treeMap.put(null, testValue);
        return treeMap;
    }
}
