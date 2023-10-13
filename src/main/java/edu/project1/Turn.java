package edu.project1;

public class Turn {
    private int number;
    private String firstChoice;

    private String secondChoice;
    private String input;

    private String answerToGiveUpChoice;

    private final boolean keyboardInput;

    public Turn(int number, String firstChoice, String secondChoice, String input, String answerToGiveUpChoice) {
        this.number = number;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.input = input;
        this.answerToGiveUpChoice = answerToGiveUpChoice;
        this.keyboardInput = false;
    }

    public Turn() {
        keyboardInput = true;
    }

    public int getNumber() {
        return number;
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public String getInput() {
        return input;
    }

    public String getAnswerToGiveUpChoice() {
        return answerToGiveUpChoice;
    }

    public boolean isKeyboardInput() {
        return keyboardInput;
    }

    @Override
    public String toString() {
        return LogStrings.turnNumber.formatted(number) +
            LogStrings.answerToFirstChoice.formatted(firstChoice) +
            LogStrings.answerToSecondChoice.formatted(secondChoice) +
            LogStrings.inputMessage.formatted(input) +
            LogStrings.answerToGiveUpChoice.formatted(answerToGiveUpChoice) +
            (keyboardInput ? LogStrings.keyboardInput : LogStrings.turnInput);
    }
}
