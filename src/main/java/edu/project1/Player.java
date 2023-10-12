package edu.project1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private GameStatus gameStatus;
    private String playerName;

    public Player() {
        playerName = "Player1";
    }

    public void updateGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void makeMove() throws InputMismatchException {
        System.out.println("It's your turn, choose an action to perform: 1 - Try to guess a single letter," +
            " 2 - Try to guess a whole word, 3 - Give up");
        Scanner scanner = new Scanner(System.in);
        int answer = 0;
        try {
            answer = scanner.nextInt();
        } catch (InputMismatchException ime) {
            scanner.nextLine();
            System.err.println("You seem to misunderstood us. You must write a single digit from described above."
            + " If you understood this text, please, input 1 (single digit, neither with any brackets nor by word)." +
                " Otherwise game session will be stopped.");
            String answerString = scanner.next();
            if (answerString.equals("1")) {
                makeMove();
            }
            else {
                gameStatus.updateMessage(new Message(true, false, "", false, false));
                return;
            }
        }
        switch (answer) {
            case 1 -> guessLetter();
            case 2 -> guessWord();
            case 3 -> giveUp();
            default -> {
                System.out.println("It seems there is no such option. Press 1 to try again or any other symbol to exit");
                String stringAnswer = scanner.next();
                if (stringAnswer.equals("1")) {
                    makeMove();
                }
                else {
                    gameStatus.updateMessage(new Message(true, false, "", false, false));
                }
            }
        }
    }

    private void guessLetter() {
        System.out.println("Ok, now input any letter you think is correct.");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        if (answer.length() > 1) {
            guessWord(answer);
        }
        else {
            gameStatus.updateMessage(new Message(false, false, answer, true, false));
        }
    }

    private void guessLetter(String symbol) {
        System.out.println("It seems you tried to input only a single char. We noticed it.");
        gameStatus.updateMessage(new Message(false, false, symbol, true, false));
    }
    private void guessWord() {
        System.out.println("Risky plan, but input whole word now.");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        if (answer.length() == 1) {
            guessLetter(answer);
        }
        else {
            gameStatus.updateMessage(new Message(false, false, answer, false, true));
        }
    }

    private void guessWord(String word) {
        System.out.println("It seems you decided to input the whole word. We noticed it.");
        gameStatus.updateMessage(new Message(false, false, word, false, true));
    }

    private void giveUp() {
        System.out.println("Sure you wanna give up right now? yes/no");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        gameStatus.updateMessage(new Message(false, true, answer, false, false));
    }

    public void changeName() {
        System.out.println("Hi, your default name is Player1. But you can change it right now, if you want to. yes/no");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("Please, write down your nickname");
            this.playerName = scanner.nextLine();
            System.out.println("Changes committed");
        } else {
            if (answer.equalsIgnoreCase("no")) {
                System.out.println("As you wish.");
            } else {
                System.out.println("We didn't understand you. Your nickname will remain default, but you can change it during next session");
            }
        }
    }
}
