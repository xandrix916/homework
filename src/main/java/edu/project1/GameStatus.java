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

    private Turn getTurn() {
        if (turns == null || numberOfMove > turns.length)
            return new Turn();
        return turns[numberOfMove-1];
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

    private void whenGiveUp() {
        if (currentMessage.getInput().equalsIgnoreCase("yes")) {
            System.out.println(InterfaceStrings.giveUpGoodbye);
            gameJournal.makeLog(LogStrings.trueGiveUp, turns[numberOfMove-1]);
            state = State.GIVE_UP;
        }
        else {
            gameJournal.makeLog(LogStrings.falseGiveUp, turns[numberOfMove-1]);
        }
    }

    private void whenSingleChar() {
        if (usedLetters.contains(Character.toUpperCase(currentMessage.getInput().charAt(0))) || wrongLetters.contains(Character.toUpperCase(currentMessage.getInput().charAt(0)))) {
            System.out.println(InterfaceStrings.alreadyUsedLetter);
        } else {
            if (!Character.isLetter(currentMessage.getInput().charAt(0)) && inputFailures == 0) {
                System.out.println(InterfaceStrings.wrongSymbolFree);
                state = State.INPUT_FAILURE;
                gameJournal.makeLog(LogStrings.inputFailure, getTurn());
                inputFailures++;
                return;
            }
            if (!Character.isLetter(currentMessage.getInput().charAt(0)) && inputFailures > 0) {
                System.out.println(InterfaceStrings.wrongSymbolFine);
                state = State.INPUT_FAILURE;
                attemptsLeft--;
                inputFailures++;
                gameJournal.makeLog(LogStrings.inputFailureAgain.formatted(1, attemptsLeft), turns[numberOfMove-1]);
                if (isFail()) {
                    System.out.println(InterfaceStrings.noAttemptsSymbolWay);
                }
                return;
            }
            currentStringWithMask = stringProcessor.getStringWithMask(currentMessage.getInput());
            if (!previousMove.equalsIgnoreCase(currentStringWithMask)) {
                int changes = amountOfChanges(currentStringWithMask);
                if (changes == 1) {
                    System.out.println(InterfaceStrings.oneLetterOpened);
                    lettersLeft--;
                    gameJournal.makeLog(LogStrings.oneLetterOpened, getTurn());
                } else {
                    System.out.printf((InterfaceStrings.severalLettersOpened), changes);
                    lettersLeft -= changes;
                    gameJournal.makeLog(LogStrings.severalLettersOpened.formatted(changes), getTurn());
                }
                usedLetters.add(Character.toUpperCase(currentMessage.getInput().charAt(0)));
                gameJournal.makeLog(LogStrings.correctLetterUpdate.formatted(currentMessage.getInput().charAt(0)),getTurn());
                if (!isWin()) {
                    state = State.SUCCESSFUL_GUESS;
                }
            } else {
                System.out.println(InterfaceStrings.noLettersOpenedSymbol);
                attemptsLeft -= 1;
                gameJournal.makeLog(LogStrings.wrongGuess.formatted(1,attemptsLeft), getTurn());
                state = State.UNSUCCESSFUL_GUESS;
                wrongLetters.add(Character.toUpperCase(currentMessage.getInput().charAt(0)));
                gameJournal.makeLog(LogStrings.wrongLetterUpdate.formatted(currentMessage.getInput().charAt(0)), getTurn());
                if (isFail()) {
                    System.out.println(InterfaceStrings.noAttemptsSymbolWay);
                }
            }
        }
    }

    @SuppressWarnings({"all"})
    private boolean isWin() {
        if (countSymbols(currentStringWithMask) == 0) {
            System.out.println(InterfaceStrings.bravoOpened);
            state = State.WIN;
            lettersLeft = 0;
            gameJournal.makeLog(LogStrings.winOfGame, getTurn());
            return true;
        }
        return false;
    }

    private boolean isFail() {
        if (attemptsLeft < 0) {
            state = State.FAIL;
            gameJournal.makeLog(LogStrings.outOfAttempts, getTurn());
            return true;
        }
        return false;
    }
    private void whenWholeWord() {
        boolean anyOddSymbols = StringProcessor.anyOddDigits(currentMessage.getInput());
        if (anyOddSymbols && inputFailures == 0) {
            System.out.println(InterfaceStrings.wrongSequenceFree);
            inputFailures++;
            state = State.INPUT_FAILURE;
            gameJournal.makeLog(LogStrings.inputFailure, getTurn());
            return;
        }
        if (anyOddSymbols && inputFailures > 0) {
            System.out.println(InterfaceStrings.wrongSequenceFine);
            state = State.INPUT_FAILURE;
            inputFailures++;
            attemptsLeft -= 2;
            gameJournal.makeLog(LogStrings.inputFailureAgain, getTurn());
            if (isFail()) {
                System.out.println(InterfaceStrings.noAttemptsSequenceWay);
            }
            return;
        }
        currentStringWithMask = stringProcessor.getStringWithMask(currentMessage.getInput());
        if (!isWin()) {
            System.out.println(InterfaceStrings.wrongWord);
            attemptsLeft -= 2;
            gameJournal.makeLog(LogStrings.wrongGuess.formatted(2,attemptsLeft), getTurn());
            if (isFail()) {
                System.out.println(InterfaceStrings.noAttemptsSequenceWay);
            }
        }

    }

    public void checkStatusAndOutputInfo(){
        if (currentMessage.isDefault()) {
            player.makeMove(new Turn());
        }

        if (currentMessage.isRageQuit()) {
            System.out.println(InterfaceStrings.colderHead);
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
