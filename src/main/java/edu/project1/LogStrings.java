package edu.project1;

public final class LogStrings {
    private LogStrings() {}

    public static final String RECEIVE_MISMATCH = "RECEIVED MISMATCH\n";

    public static final String RECEIVE_SWITCH_FAIL = "RECEIVED INCORRECT OPTION\n";

    public static final String RAGE_QUIT = "PLAYER IGNORES ASK TO INPUT 1 AND RAGE QUITS\n";
    public static final String MISMATCH_AGAIN = "PLAYER IGNORES ASK TO INPUT 1 AND LEAVES GAME\n";

    public static final String RECEIVE_MESSAGE = "RECEIVED CORRECT MESSAGE: %s\n";
    public static final String GAMEMODE_ONE = "GUESS LETTER";
    public static final String GAMEMODE_TWO = "GUESS WORD";
    public static final String GAMEMODE_CHOICE = "PLAYER PICKED: %S\n";
    public static final String GIVE_UP_MESSAGE = "PLAYER DECIDED TO GIVE UP\n";
    public static final String GAMEMODE_CHANGE = "PLAYER CHANGES HIS MIND AND PICKS: %s\n";

    public static final String TRUE_GIVE_UP = "PLAYER REALLY GIVES UP AND QUITS THE GAME\n";
    public static final String FALSE_GIVE_UP = "PLAYER CHANGES HIS MIND AND CONTINUES\n";

    public static final String TURN_NUMBER = "\nTURN #%d\n";
    public static final String ANSWER_TO_FIRST_CHOICE = "ANSWER TO FIRST CHOICE: %s\n";
    public static final String ANSWER_TO_SECOND_CHOICE = "ANSWER TO SECOND CHOICE: %s\n";
    public static final String INPUT_MESSAGE = "INPUT: %s\n";
    public static final String ANSWER_TO_GIVE_UP_CHOICE = "ANSWER TO GIVE UP CHOICE: %s\n";
    public static final String KEYBOARD_INPUT = "INPUT OPTION: KEYBOARD\n";
    public static final String TURN_INPUT = "INPUT OPTION: TEST TURNS\n";

    public static final String INPUT_FAILURE = "INPUT FAILURE: NO FINES\n";

    public static final String INPUT_FAILURE_AGAIN = "INPUT FAILURE: -%d ATTEMPTS %d ATTEMPTS LEFT\n";

    public static final String OUT_OF_ATTEMPTS = "\nFAIL: NO ATTEMPTS LEFT\n";

    public static final String WIN_OF_GAME = "\nWIN: WORD IS FULLY OPENED\n";

    public static final String ONE_LETTER_OPENED = "ONE LETTER OPENED\n";

    public static final String SEVERAL_LETTERS_OPENED = "%d LETTERS OPENED\n";

    public static final String CORRECT_LETTER_UPDATE = "ANOTHER LETTER USED: %c\n";

    public static final String WRONG_GUESS = "WRONG GUESS: -%d ATTEMPTS %d ATTEMPTS LEFT\n";

    public static final String WRONG_LETTER_UPDATE = "WRONG LETTER USED: %c\n";
}
