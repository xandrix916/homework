package edu.project1;

public class Turn {
    private final int number;
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

    public Turn(int number) {
        this.number = number;
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
        return LogStrings.TURN_NUMBER.formatted(number)
            + LogStrings.ANSWER_TO_FIRST_CHOICE.formatted(firstChoice)
            + LogStrings.ANSWER_TO_SECOND_CHOICE.formatted(secondChoice)
            + LogStrings.INPUT_MESSAGE.formatted(input)
            + LogStrings.ANSWER_TO_GIVE_UP_CHOICE.formatted(answerToGiveUpChoice)
            + (keyboardInput ? LogStrings.KEYBOARD_INPUT : LogStrings.TURN_INPUT);
    }
}
