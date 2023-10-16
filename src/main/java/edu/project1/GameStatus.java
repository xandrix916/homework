package edu.project1;

import java.util.ArrayList;
import java.util.Collections;

public class GameStatus {

    private final Player player;

    private final GameJournal gameJournal;
    private State state;
    private final StringProcessor stringProcessor;
    private String previousMove;

    private String currentStringWithMask;
    private int attemptsLeft;
    private Turn[] turns;

    private int inputFailures = 0;

    private int numberOfMove;
    private final int totalAttempts;
    private int lettersLeft;
    private Message currentMessage;

    private final ArrayList<Character> usedLetters = new ArrayList<>();
    private final ArrayList<Character> wrongLetters = new ArrayList<>();

    public GameStatus(int totalAttempts, StringProcessor stringProcessor, Player player, GameJournal gameJournal) {
        this.state = State.START;
        this.gameJournal = gameJournal;
        this.totalAttempts = totalAttempts;
        this.attemptsLeft = totalAttempts;
        this.currentMessage = new Message();
        this.numberOfMove = 1;
        this.stringProcessor = stringProcessor;
        this.player = player;
        this.previousMove = stringProcessor.getStringWithMask();
        this.lettersLeft = previousMove.length();
    }

    public void updateMessage(Message message) {
        currentMessage = message;
    }

    public void updateTurns(Turn[] turns) {
        this.turns = turns.clone();
    }

    private int countSymbols(String string) {
        return string.length() - (string.replace("_", "")).length();
    }

    private int amountOfChanges(String current) {
        return countSymbols(previousMove) - countSymbols(current);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void whenGiveUp() {
        if (currentMessage.getInput().equalsIgnoreCase("yes")) {
            System.out.println(InterfaceStrings.GIVE_UP_GOODBYE);
            gameJournal.makeLog(LogStrings.TRUE_GIVE_UP, turns[numberOfMove - 1]);
            state = State.GIVE_UP;
        } else {
            gameJournal.makeLog(LogStrings.FALSE_GIVE_UP, turns[numberOfMove - 1]);
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void whenSingleChar() {
        if (usedLetters.contains(Character.toUpperCase(currentMessage.getInput().charAt(0)))
            || wrongLetters.contains(Character.toUpperCase(currentMessage.getInput().charAt(0)))) {
            System.out.println(InterfaceStrings.ALREADY_USED_LETTER);
        } else {
            if (!Character.isLetter(currentMessage.getInput().charAt(0)) && inputFailures == 0) {
                System.out.println(InterfaceStrings.WRONG_SYMBOL_FREE);
                state = State.INPUT_FAILURE;
                gameJournal.makeLog(LogStrings.INPUT_FAILURE, gameJournal.getTurn(numberOfMove - 1));
                inputFailures++;
            } else {
                if (!Character.isLetter(currentMessage.getInput().charAt(0)) && inputFailures > 0) {
                    System.out.println(InterfaceStrings.WRONG_SYMBOL_FINE);
                    state = State.INPUT_FAILURE;
                    attemptsLeft--;
                    inputFailures++;
                    gameJournal.makeLog(
                        LogStrings.INPUT_FAILURE_AGAIN.formatted(1, attemptsLeft),
                        turns[numberOfMove - 1]
                    );
                    if (isFail()) {
                        System.out.println(InterfaceStrings.NO_ATTEMPTS_SYMBOL_WAY);
                    }
                    return;
                }
                currentStringWithMask = stringProcessor.getStringWithMask(currentMessage.getInput());
                if (!previousMove.equalsIgnoreCase(currentStringWithMask)) {
                    int changes = amountOfChanges(currentStringWithMask);
                    if (changes == 1) {
                        System.out.println(InterfaceStrings.ONE_LETTER_OPENED);
                        lettersLeft--;
                        gameJournal.makeLog(LogStrings.ONE_LETTER_OPENED, gameJournal.getTurn(numberOfMove - 1));
                    } else {
                        System.out.printf((InterfaceStrings.SEVERAL_LETTERS_OPENED), changes);
                        lettersLeft -= changes;
                        gameJournal.makeLog(
                            LogStrings.SEVERAL_LETTERS_OPENED.formatted(changes),
                            gameJournal.getTurn(numberOfMove - 1)
                        );
                    }
                    usedLetters.add(Character.toUpperCase(currentMessage.getInput().charAt(0)));
                    gameJournal.makeLog(
                        LogStrings.CORRECT_LETTER_UPDATE.formatted(currentMessage.getInput().charAt(0)),
                        gameJournal.getTurn(numberOfMove - 1)
                    );
                    if (!isWin()) {
                        state = State.SUCCESSFUL_GUESS;
                    }
                } else {
                    System.out.println(InterfaceStrings.NO_LETTERS_OPENED_SYMBOL);
                    attemptsLeft -= 1;
                    gameJournal.makeLog(
                        LogStrings.WRONG_GUESS.formatted(1, attemptsLeft),
                        gameJournal.getTurn(numberOfMove - 1)
                    );
                    state = State.UNSUCCESSFUL_GUESS;
                    wrongLetters.add(Character.toUpperCase(currentMessage.getInput().charAt(0)));
                    gameJournal.makeLog(
                        LogStrings.WRONG_LETTER_UPDATE.formatted(currentMessage.getInput().charAt(0)),
                        gameJournal.getTurn(numberOfMove - 1)
                    );
                    if (isFail()) {
                        System.out.println(InterfaceStrings.NO_ATTEMPTS_SYMBOL_WAY);
                    }
                }
            }
        }
    }

    @SuppressWarnings({"all"})
    private boolean isWin() {
        if (countSymbols(currentStringWithMask) == 0) {
            System.out.println(InterfaceStrings.BRAVO_OPENED);
            state = State.WIN;
            lettersLeft = 0;
            gameJournal.makeLog(LogStrings.WIN_OF_GAME, gameJournal.getTurn(numberOfMove-1));
            return true;
        }
        return false;
    }

    private boolean isFail() {
        if (attemptsLeft < 0) {
            state = State.FAIL;
            gameJournal.makeLog(LogStrings.OUT_OF_ATTEMPTS, gameJournal.getTurn(numberOfMove - 1));
        }
        return attemptsLeft < 0;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void whenWholeWord() {
        boolean anyOddSymbols = StringProcessor.anyOddDigits(currentMessage.getInput());
        if (anyOddSymbols && inputFailures == 0) {
            System.out.println(InterfaceStrings.WRONG_SEQUENCE_FREE);
            inputFailures++;
            state = State.INPUT_FAILURE;
            gameJournal.makeLog(LogStrings.INPUT_FAILURE, gameJournal.getTurn(numberOfMove - 1));
        } else {
            if (anyOddSymbols && inputFailures > 0) {
                System.out.println(InterfaceStrings.WRONG_SEQUENCE_FINE);
                state = State.INPUT_FAILURE;
                inputFailures++;
                attemptsLeft -= 2;
                gameJournal.makeLog(LogStrings.INPUT_FAILURE_AGAIN, gameJournal.getTurn(numberOfMove - 1));
                if (isFail()) {
                    System.out.println(InterfaceStrings.NO_ATTEMPTS_SEQUENCE_WAY);
                }
                return;
            }
            currentStringWithMask = stringProcessor.getStringWithMask(currentMessage.getInput());
            if (!isWin()) {
                System.out.println(InterfaceStrings.WRONG_WORD);
                attemptsLeft -= 2;
                gameJournal.makeLog(
                    LogStrings.WRONG_GUESS.formatted(2, attemptsLeft),
                    gameJournal.getTurn(numberOfMove - 1)
                );
                if (isFail()) {
                    System.out.println(InterfaceStrings.NO_ATTEMPTS_SEQUENCE_WAY);
                }
            }
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void checkStatusAndOutputInfo() {
        if (currentMessage.isDefault()) {
            player.makeMove(new Turn(numberOfMove));
        }

        if (currentMessage.isRageQuit()) {
            System.out.println(InterfaceStrings.COLDER_HEAD);
            state = State.RAGE_QUIT;
        }

        if (currentMessage.isGiveUp()) {
            whenGiveUp();
        }

        if (currentMessage.isSingleChar()) {
            whenSingleChar();
        }

        if (currentMessage.isWholeWord()) {
            whenWholeWord();
        }
        previousMove = currentStringWithMask;
        numberOfMove++;
    }

    public boolean isTerminal() {
        if (state == State.RAGE_QUIT || state == State.GIVE_UP) {
            return true;
        }
        return state == State.WIN || state == State.FAIL;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public int getLettersLeft() {
        return lettersLeft;
    }

    public State getState() {
        return state;
    }

    private String charListToString(ArrayList<Character> arrayList) {
        Collections.sort(arrayList);
        StringBuilder letterString = new StringBuilder();
        for (var c: arrayList) {
            letterString.append(c);
        }
        return letterString.toString();
    }

    public String getUsedLettersString() {
        return charListToString(usedLetters);
    }

    public String getWrongLettersString() {
        return charListToString(wrongLetters);
    }

    public int getNumberOfMove() {
        return numberOfMove;
    }

}
