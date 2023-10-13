package edu.project1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private GameStatus gameStatus;
    private final GameJournal gameJournal;
    private String playerName;

    public Player(GameJournal gameJournal) {
        playerName = "Player1";
        this.gameJournal = gameJournal;
    }

    public void updateGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    private int getAnswerKeyboard() {
        System.out.println(InterfaceStrings.makeMoveChoice);
        Scanner scanner = new Scanner(System.in);
        int answer;
        try {
            answer = scanner.nextInt();
        } catch (InputMismatchException ime) {
            scanner.nextLine();
            System.err.println(InterfaceStrings.mismatchErrorHandling);
            String answerString = scanner.next();
            if (!answerString.equals("1")) {
                return -1;
            }
            return 1;
        }
        return answer;
    }

    private int getAnswerByTurn(Turn turn) {
        if (StringProcessor.anyOddSymbols(turn.getFirstChoice())) {
            gameJournal.makeLog(LogStrings.receiveMismatch, turn);
            return -1;
        }
        else {
            return Integer.parseInt(turn.getFirstChoice());
        }
    }
    public void makeMove(Turn turn) throws InputMismatchException {
        int answer;
        if (turn.isKeyboardInput()) {
            answer = getAnswerKeyboard();
        }
        else {
            answer = getAnswerByTurn(turn);
        }
        switch (answer) {
            case 1 -> guessLetter(turn);
            case 2 -> guessWord(turn);
            case 3 -> giveUp(turn);
            case -1 -> {
                gameStatus.updateMessage(new Message(true, false, "",
                    false, false));
                gameJournal.makeLog(LogStrings.mismatchAgain, turn);
            }
            default -> {
                if (turn.isKeyboardInput()) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println(InterfaceStrings.switchDefaultHandling);
                    String stringAnswer = scanner.next();
                    if (!stringAnswer.equals("1")) {
                        gameStatus.updateMessage(new Message(true, false, "",
                            false, false));
                    }
                }
                else {
                    gameJournal.makeLog(LogStrings.receiveSwitchFail, turn);
                    if (!turn.getSecondChoice().equals("1")) {
                        gameStatus.updateMessage(new Message(true, false, "",
                            false, false));
                        gameJournal.makeLog(LogStrings.rageQuit, turn);
                    }
                }
            }
        }
    }

    private boolean checkAttemptLimit() {
        if (gameStatus.getAttemptsLeft() == 0) {
            System.out.println(InterfaceStrings.reachedAttemptLimit);
            return false;
        }
        return true;
    }

    private void guessLetter(Turn turn) {
        String answer;
        if (turn.isKeyboardInput()) {
            System.out.println(InterfaceStrings.inputSingleLetter);
            Scanner scanner = new Scanner(System.in);
            answer = scanner.next();
        }
        else{
            gameJournal.makeLog(LogStrings.receiveMessage.formatted(1), turn);
            gameJournal.makeLog(LogStrings.gamemodeChoice.formatted(LogStrings.gamemodeOne), turn);
            answer = turn.getInput();
        }
        if (answer.length() > 1) {
            if (checkAttemptLimit()) {
                gameJournal.makeLog(LogStrings.gamemodeChange.formatted(LogStrings.gamemodeTwo), turn);
                guessWord(answer);
            }
            else {
                makeMove(new Turn());
            }
        }
        else {
            gameStatus.updateMessage(new Message(false, false, answer, true, false));
        }
    }

    private void guessLetter(String symbol) {
        System.out.println(InterfaceStrings.fromWordToLetter);
        gameStatus.updateMessage(new Message(false, false, symbol, true, false));
    }
    private void guessWord(Turn turn) {
        if (checkAttemptLimit()) {
            String answer;
            if (turn.isKeyboardInput()) {
                System.out.println(InterfaceStrings.inputWord);
                Scanner scanner = new Scanner(System.in);
                answer = scanner.next();
            }
            else {
                gameJournal.makeLog(LogStrings.receiveMessage.formatted(2),turn);
                gameJournal.makeLog(LogStrings.gamemodeChoice.formatted(LogStrings.gamemodeTwo), turn);
                answer = turn.getInput();
            }
            if (answer.length() == 1) {
                gameJournal.makeLog(LogStrings.gamemodeChange.formatted(LogStrings.gamemodeOne), turn);
                guessLetter(answer);
            } else {
                gameStatus.updateMessage(new Message(false, false, answer, false, true));
            }
        }
        else {
            makeMove(new Turn());
        }
    }

    private void guessWord(String word) {
        System.out.println(InterfaceStrings.fromLetterToWord);
        gameStatus.updateMessage(new Message(false, false, word, false, true));
    }

    private void giveUp(Turn turn) {
        String answer;
        if (turn.isKeyboardInput()) {
            System.out.println(InterfaceStrings.giveUpChoice);
            Scanner scanner = new Scanner(System.in);
            answer = scanner.next();
        }
        else {
            gameJournal.makeLog(LogStrings.giveUpMessage, turn);
            answer = turn.getAnswerToGiveUpChoice();
        }
        gameStatus.updateMessage(new Message(false, true, answer, false, false));
    }

    public void changeName() {
        System.out.println(InterfaceStrings.changeNameGreetings);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        scanner.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println(InterfaceStrings.writeDownNickname);
            this.playerName = scanner.nextLine();
            System.out.println(InterfaceStrings.changeSuccessful);
        } else {
            if (answer.equalsIgnoreCase("no")) {
                System.out.println(InterfaceStrings.asYouWish);
            } else {
                System.out.println(InterfaceStrings.undefinedNicknameAnswer);
            }
        }
    }

    public String getPlayerName() {
        return playerName;
    }
}
