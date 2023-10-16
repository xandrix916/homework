package edu.project1;

import java.util.HashMap;

public class GameJournal {
    private final HashMap<Turn, String> turnResponseHashMap = new HashMap<>();

    private Turn[] orderedTurns() {
        Turn[] setToOrder = new Turn[turnResponseHashMap.keySet().size()];
        for (var k: turnResponseHashMap.keySet()) {
            setToOrder[k.getNumber()-1] = k;
        }
        return setToOrder;
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (var k: orderedTurns()) {
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

    public Turn getTurn(int number) {
        Turn[] orderedSet = orderedTurns();
        if (number >= orderedSet.length) {
            return orderedSet[orderedSet.length-1];
        }
        return orderedSet[number];
    }

}
