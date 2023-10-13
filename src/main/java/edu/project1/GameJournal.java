package edu.project1;

import java.util.HashMap;

public class GameJournal {
    private final HashMap<Turn, String> turnResponseHashMap = new HashMap<>();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Turn[] setToOrder = new Turn[turnResponseHashMap.keySet().size()];
        for (var k: turnResponseHashMap.keySet()) {
            setToOrder[k.getNumber()-1] = k;
        }
        for (var k: setToOrder) {
            result.append(k.toString()).append(turnResponseHashMap.get(k));
        }
        return result.toString();
    }

    public void makeLog(String string, Turn turn) {
        if (turnResponseHashMap.containsKey(turn)) {
            turnResponseHashMap.replace(turn, turnResponseHashMap.get(turn) + string);
        } else {
            turnResponseHashMap.put(turn, string);
        }
    }

}
