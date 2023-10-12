package edu.project1;

import java.util.ArrayList;
import java.util.Scanner;

public class GameStatus {

    private final Player player;
    private State state;
    private final StringProcessor stringProcessor;
    private String previousMove;

    private String currentStringWithMask;
    private int attemptsLeft;

    private int inputFailures = 0;
    private final int totalAttempts;
    private int lettersLeft;
    private Message currentMessage;

    private ArrayList<Character> usedLetters = new ArrayList<>();

    public GameStatus(int totalAttempts, StringProcessor stringProcessor, Player player) {
        this.state = State.START;
        this.totalAttempts = totalAttempts;
        this.attemptsLeft = totalAttempts;
        this.currentMessage = new Message();
        this.stringProcessor = stringProcessor;
        this.player = player;
        this.previousMove = stringProcessor.getStringWithMask();
        this.lettersLeft = previousMove.length();
    }

    public void updateMessage(Message message) {
        currentMessage = message;
    }


    private int countSymbols(String string) {
        return string.length() - (string.replace("_", "")).length();
    }

    private int amountOfChanges(String current) {
        return countSymbols(previousMove) - countSymbols(current);
    }

    private void whenGiveUp() {
        System.out.println("You sure to give up right now? I guess, you still have some chances... yes/no");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("Try again, pal, I believe you will be lucky enough.");
            state = State.GIVE_UP;
        }
    }

    private void whenSingleChar() {
        if (!Character.isLetter(currentMessage.getInput().charAt(0)) && inputFailures == 0) {
            System.out.println("Apparently, you wrote some symbol, which is not letter, so try again. This time you won't lose any attempts");
            state = State.INPUT_FAILURE;
            inputFailures++;
            return;
        }
        if (!Character.isLetter(currentMessage.getInput().charAt(0)) && inputFailures > 0) {
            System.out.println("Apparently, you wrote some symbol, which is not letter, so try again. Now every time you write symbol that is not letter, you will lose 1 attempt.");
            state = State.INPUT_FAILURE;
            attemptsLeft--;
            inputFailures++;
            if (isFail()) {
                System.out.println("You lost all your attempts. Bad luck, buddy");
            }
            return;
        }
        currentStringWithMask = stringProcessor.getStringWithMask(currentMessage.getInput());
        if (!previousMove.equalsIgnoreCase(currentStringWithMask)) {
            int changes = amountOfChanges(currentStringWithMask);
            if (changes == 1) {
                System.out.println("Nice work! You opened one letter");
                lettersLeft--;
            }
            else {
                System.out.printf("Well done, you opened %d letters!\n", changes);
                lettersLeft -= changes;
            }
            if (!isWin()) {
                state = State.SUCCESSFUL_GUESS;
            }
        }
        else {
            System.out.println("No letters opened. That's a pity.");
            attemptsLeft -= 1;
            state = State.UNSUCCESSFUL_GUESS;
            if (isFail()) {
                System.out.println("You lost all your attempts. Bad luck, buddy");
            }
        }
    }

    @SuppressWarnings({"all"})
    private boolean isWin() {
        if (countSymbols(currentStringWithMask) == 0) {
            System.out.println("Congratulations, you managed to open a whole word!");
            state = State.WIN;
            lettersLeft = 0;
            return true;
        }
        return false;
    }

    private boolean isFail() {
        if (attemptsLeft < 0) {
            state = State.FAIL;
            return true;
        }
        return false;
    }
    private void whenWholeWord() {
        boolean anyOddSymbols = stringProcessor.anyOddSymbols(currentMessage.getInput());
        if (anyOddSymbols && inputFailures == 0) {
            System.out.println("Apparently, you wrote some char sequence, which is not word, so try again. This time you won't lose any attempts");
            inputFailures++;
            state = State.INPUT_FAILURE;
            return;
        }
        if (anyOddSymbols && inputFailures > 0) {
            System.out.println("Apparently, you wrote some char sequence, which is not word, so try again. This time and any further you will lose two attempts");
            state = State.INPUT_FAILURE;
            inputFailures++;
            attemptsLeft -= 2;
            if (isFail()) {
                System.out.println("You lost all your attempts. It seems you didn't need to risk so much");
            }
            return;
        }
        currentStringWithMask = stringProcessor.getStringWithMask(currentMessage.getInput());
        if (!isWin()) {
            System.out.println("Unfortunately, you didn't manage it. You'll lose two attempts");
            attemptsLeft -= 2;
            if (isFail()) {
                System.out.println("You lost all your attempts. It seems you didn't need to risk so much");
            }
        }

    }

    public void checkStatusAndOutputInfo(){
        if (currentMessage.isDefault()) {
            player.makeMove();
        }

        if (currentMessage.isRageQuit()) {
            System.out.println("Try to play with colder head next time");
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
}
