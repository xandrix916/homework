package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

@SuppressWarnings("InnerTypeLast")
public class Problem8 {
    public static class BackwardIterator<K> implements Iterator<K> {

        private final Stack<K> backwardStack = new Stack<>();

        @SuppressWarnings("WhileLoopReplaceableByForEach")
        public BackwardIterator(Collection<K> collection) {
            Iterator<K> iterator = collection.iterator();
            while (iterator.hasNext()) {
                backwardStack.push(iterator.next());
            }
        }

        @Override
        public boolean hasNext() {
            return backwardStack.size() != 0;
        }

        @Override
        public K next() {
            return backwardStack.pop();
        }

        @Override
        public void remove() {
            backwardStack.pop();
        }
    }

    public <K> String backwardsCheck(Collection<K> collection) {
        BackwardIterator<K> backwardIterator = new BackwardIterator<>(collection);
        StringBuilder stringBuilder = new StringBuilder();
        while (backwardIterator.hasNext()) {
            stringBuilder.append(backwardIterator.next()).append("\n");
        }
        return stringBuilder.toString();
    }
}
