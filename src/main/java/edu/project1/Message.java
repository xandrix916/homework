package edu.project1;

public class Message {
    private final static String DEFAULT = "DEFAULT";
    private final boolean rageQuit;
    private final boolean giveUp;
    private final String input;
    private final boolean singleChar;
    private final boolean wholeWord;

    public Message(boolean rageQuit, boolean giveUp, String input, boolean singleChar, boolean wholeWord) {
        this.rageQuit = rageQuit;
        this.giveUp = giveUp;
        this.input = input;
        this.singleChar = singleChar;
        this.wholeWord = wholeWord;
    }

    public Message() {
        this.rageQuit = false;
        this.giveUp = false;
        this.input = DEFAULT;
        this.singleChar = false;
        this.wholeWord = false;
    }

    public boolean isRageQuit() {
        return rageQuit;
    }

    public boolean isGiveUp() {
        return giveUp;
    }

    public String getInput() {
        return input;
    }

    public boolean isSingleChar() {
        return singleChar;
    }

    public boolean isWholeWord() {
        return wholeWord;
    }

    public boolean isDefault() {
        return rageQuit && giveUp && input.equals(DEFAULT);
    }

}
