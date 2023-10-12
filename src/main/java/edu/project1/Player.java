package edu.project1;

import java.util.Scanner;

public class Player {
    private GameStatus gameStatus;

    public Player(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player() {

    }

    public void updateGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void makeMove() {
        System.out.println("It's your turn, choose an action to perform: 1 - Try to guess a single letter," +
            " 2 - Try to guess a whole word, 3 - Give up");
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();
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
}
