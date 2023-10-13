package edu.project1;

public class InterfaceStrings {
    public static final String makeMoveChoice = "It's your turn, choose an action to perform: 1 - Try to guess a single letter," +
        " 2 - Try to guess a whole word, 3 - Give up";
    public static final String mismatchErrorHandling = "You seem to misunderstood us. You must write a " +
        "single digit from described above."
        + " If you understood this text, please, input 1 (single digit, neither with any brackets nor by word)." +
        " Otherwise game session will be stopped.";
    public static final String switchDefaultHandling = "It seems there is no such option. Press 1 to try again" +
        " or any other symbol to exit";
    public static final String reachedAttemptLimit = "It seemed you have already reached the limit of attempts," +
        " so you can't waste two attempts at once." +
        " You may use one char option or give up.";
    public static final String inputSingleLetter = "Ok, now input any letter you think is correct.";
    public static final String fromWordToLetter = "It seems you tried to input only a single char. We noticed it.";
    public static final String inputWord = "Risky plan, but input whole word now.";
    public static final String fromLetterToWord = "It seems you decided to input the whole word. We noticed it.";
    public static final String giveUpChoice = "Sure you wanna give up right now? yes/no";
    public static final String changeNameGreetings = "Hi, your default name is Player1. But you can change it right now, if you want to. yes/no";
    public static final String writeDownNickname = "Please, write down your nickname";
    public static final String changeSuccessful = "Changes committed";
    public static final String asYouWish = "As you wish.";
    public static final String undefinedNicknameAnswer = "We didn't understand you. Your nickname will" +
        " remain default, but you can change it during next session";

    public static final String giveUpGoodbye = "Try again, pal, I believe you will be lucky enough.";

    public static final String alreadyUsedLetter = "Sorry, you have already used this letter. Please, try another.";

    public static final String wrongSymbolFree = "Apparently, you wrote some symbol, which is not letter, so try again. This time you won't lose any attempts";

    public static final String wrongSymbolFine = "Apparently, you wrote some symbol, which is not letter, so try again. Now every time you write symbol that is not letter, you will lose 1 attempt.";

    public static final String noAttemptsSymbolWay = "You lost all your attempts. Bad luck, buddy";

    public static final String oneLetterOpened = "Nice work! You opened one letter";

    public static final String severalLettersOpened = "Well done, you opened %d letters!\n";

    public static final String noLettersOpenedSymbol = "No letters opened. That's a pity.";

    public static final String bravoOpened = "Bravo, you managed to open a whole word!";

    public static final String wrongSequenceFree = "Apparently, you wrote some char sequence, which is not word, so try again. This time you won't lose any attempts";

    public static final String wrongSequenceFine = "Apparently, you wrote some char sequence, which is" +
        " not word, so try again. This time and any further you will lose two attempts";

    public static final String noAttemptsSequenceWay = "You lost all your attempts. It seems you didn't need to risk so much";

    public static final String wrongWord = "Unfortunately, you didn't manage it. You'll lose two attempts";

    public static final String colderHead = "Try to play with colder head next time";
}
