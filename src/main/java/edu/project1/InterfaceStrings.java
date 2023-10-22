package edu.project1;

public final class InterfaceStrings {
    private InterfaceStrings() {}

    public static final String MAKE_MOVE_CHOICE = "It's your turn, choose an action to perform: 1 - Try to guess"
        + " a single letter,"
        + " 2 - Try to guess a whole word, 3 - Give up";
    public static final String MISMATCH_ERROR_HANDLING = "You seem to misunderstood us. You must write a "
        + "single digit from described above."
        + " If you understood this text, please, input 1 (single digit, neither with any brackets nor by word)."
        + " Otherwise game session will be stopped.";
    public static final String SWITCH_DEFAULT_HANDLING = "It seems there is no such option. Press 1 to try again"
        + " or any other symbol to exit";
    public static final String REACHED_ATTEMPT_LIMIT = "It seemed you have already reached the limit of attempts,"
        + " so you can't waste two attempts at once."
        + " You may use one char option or give up.";
    public static final String INPUT_SINGLE_LETTER = "Ok, now input any letter you think is correct.";
    public static final String FROM_WORD_TO_LETTER = "It seems you tried to input only a single char. We noticed it.";
    public static final String INPUT_WORD = "Risky plan, but input whole word now.";
    public static final String FROM_LETTER_TO_WORD = "It seems you decided to input the whole word. We noticed it.";
    public static final String GIVE_UP_CHOICE = "Sure you wanna give up right now? yes/no";
    public static final String CHANGE_NAME_GREETINGS = "Hi, your default name is Player1. But you can change it"
        + " right now, if you want to. yes/no";
    public static final String WRITE_DOWN_NICKNAME = "Please, write down your nickname";
    public static final String CHANGE_SUCCESSFUL = "Changes committed";
    public static final String AS_YOU_WISH = "As you wish.";
    public static final String UNDEFINED_NICKNAME_ANSWER = "We didn't understand you. Your nickname will"
        + " remain default, but you can change it during next session";

    public static final String GIVE_UP_GOODBYE = "Try again, pal, I believe you will be lucky enough.";

    public static final String ALREADY_USED_LETTER = "Sorry, you have already used this letter. Please, try another.";

    public static final String WRONG_SYMBOL_FREE = "Apparently, you wrote some symbol, which is not letter,"
        + " so try again. This time you won't lose any attempts";

    public static final String WRONG_SYMBOL_FINE = "Apparently, you wrote some symbol, which is not letter, so try"
        + " again. Now every time you write symbol that is not letter, you will lose 1 attempt.";

    public static final String NO_ATTEMPTS_SYMBOL_WAY = "You lost all your attempts. Bad luck, buddy";

    public static final String ONE_LETTER_OPENED = "Nice work! You opened one letter";

    public static final String SEVERAL_LETTERS_OPENED = "Well done, you opened %d letters!\n";

    public static final String NO_LETTERS_OPENED_SYMBOL = "No letters opened. That's a pity.";

    public static final String BRAVO_OPENED = "Bravo, you managed to open a whole word!";

    public static final String WRONG_SEQUENCE_FREE = "Apparently, you wrote some char sequence, which"
        + " is not word, so try again. This time you won't lose any attempts";

    public static final String WRONG_SEQUENCE_FINE = "Apparently, you wrote some char sequence, which is"
        + " not word, so try again. This time and any further you will lose two attempts";

    public static final String NO_ATTEMPTS_SEQUENCE_WAY = "You lost all your attempts. It seems"
        + " you didn't need to risk so much";

    public static final String WRONG_WORD = "Unfortunately, you didn't manage it. You'll lose two attempts";

    public static final String COLDER_HEAD = "Try to play with colder head next time";

    public static final String LEFT_THERE = "THERE %s LEFT\n";
    public static final String ONE_LETTER = "IS ONE LETTER";
    public static final String SEVERAL_LETTERS = "ARE %d LETTERS";
}
