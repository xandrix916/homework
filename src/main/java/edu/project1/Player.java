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

    @SuppressWarnings("RegexpSinglelineJava")
    private int getAnswerKeyboard() {
        System.out.println(InterfaceStrings.MAKE_MOVE_CHOICE);
        Scanner scanner = new Scanner(System.in);
        int answer;
        try {
            answer = scanner.nextInt();
        } catch (InputMismatchException ime) {
            scanner.nextLine();
            System.err.println(InterfaceStrings.MISMATCH_ERROR_HANDLING);
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
            gameJournal.makeLog(LogStrings.RECEIVE_MISMATCH, turn);
            return -1;
        } else {
            return Integer.parseInt(turn.getFirstChoice());
        }
    }

    @SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
    public void makeMove(Turn turn) throws InputMismatchException {
        int answer;
        if (turn.isKeyboardInput()) {
            answer = getAnswerKeyboard();
        } else {
            answer = getAnswerByTurn(turn);
        }
        switch (answer) {
            case 1 -> guessLetter(turn);
            case 2 -> guessWord(turn);
            case 3 -> giveUp(turn);
            case -1 -> {
                gameStatus.updateMessage(new Message(true, false, "",
                    false, false));
                gameJournal.makeLog(LogStrings.MISMATCH_AGAIN, turn);
            }
            default -> {
                if (turn.isKeyboardInput()) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println(InterfaceStrings.SWITCH_DEFAULT_HANDLING);
                    String stringAnswer = scanner.next();
                    if (!stringAnswer.equals("1")) {
                        gameStatus.updateMessage(new Message(true, false, "",
                            false, false));
                    }
                } else {
                    gameJournal.makeLog(LogStrings.RECEIVE_SWITCH_FAIL, turn);
                    if (!turn.getSecondChoice().equals("1")) {
                        gameStatus.updateMessage(new Message(true, false, "",
                            false, false));
                        gameJournal.makeLog(LogStrings.RAGE_QUIT, turn);
                    }
                }
            }
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private boolean checkAttemptLimit() {
        if (gameStatus.getAttemptsLeft() == 0) {
            System.out.println(InterfaceStrings.REACHED_ATTEMPT_LIMIT);
            return false;
        }
        return true;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void guessLetter(Turn turn) {
        String answer;
        if (turn.isKeyboardInput()) {
            System.out.println(InterfaceStrings.INPUT_SINGLE_LETTER);
            Scanner scanner = new Scanner(System.in);
            answer = scanner.next();
        } else {
            answer = turn.getInput();
        }
        gameJournal.makeLog(LogStrings.RECEIVE_MESSAGE.formatted(1), turn);
        gameJournal.makeLog(LogStrings.GAMEMODE_CHOICE.formatted(LogStrings.GAMEMODE_ONE), turn);
        if (answer.length() > 1) {
            if (checkAttemptLimit()) {
                gameJournal.makeLog(LogStrings.GAMEMODE_CHANGE.formatted(LogStrings.GAMEMODE_TWO), turn);
                guessWord(answer);
            } else {
                makeMove(new Turn(gameStatus.getNumberOfMove()));
            }
        } else {
            gameStatus.updateMessage(new Message(false, false, answer, true, false));
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void guessLetter(String symbol) {
        System.out.println(InterfaceStrings.FROM_WORD_TO_LETTER);
        gameStatus.updateMessage(new Message(false, false, symbol, true, false));
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void guessWord(Turn turn) {
        if (checkAttemptLimit()) {
            String answer;
            if (turn.isKeyboardInput()) {
                System.out.println(InterfaceStrings.INPUT_WORD);
                Scanner scanner = new Scanner(System.in);
                answer = scanner.next();
            } else {
                answer = turn.getInput();
            }
            gameJournal.makeLog(LogStrings.RECEIVE_MESSAGE.formatted(2), turn);
            gameJournal.makeLog(LogStrings.GAMEMODE_CHOICE.formatted(LogStrings.GAMEMODE_TWO), turn);
            if (answer.length() == 1) {
                gameJournal.makeLog(LogStrings.GAMEMODE_CHANGE.formatted(LogStrings.GAMEMODE_ONE), turn);
                guessLetter(answer);
            } else {
                gameStatus.updateMessage(new Message(false, false, answer, false, true));
            }
        } else {
            makeMove(new Turn(gameStatus.getNumberOfMove()));
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void guessWord(String word) {
        System.out.println(InterfaceStrings.FROM_LETTER_TO_WORD);
        gameStatus.updateMessage(new Message(false, false, word, false, true));
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void giveUp(Turn turn) {
        String answer;
        if (turn.isKeyboardInput()) {
            System.out.println(InterfaceStrings.GIVE_UP_CHOICE);
            Scanner scanner = new Scanner(System.in);
            answer = scanner.next();
        } else {
            answer = turn.getAnswerToGiveUpChoice();
        }
        gameJournal.makeLog(LogStrings.GIVE_UP_MESSAGE, turn);
        gameStatus.updateMessage(new Message(false, true, answer, false, false));
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void changeName() {
        System.out.println(InterfaceStrings.CHANGE_NAME_GREETINGS);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        scanner.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println(InterfaceStrings.WRITE_DOWN_NICKNAME);
            this.playerName = scanner.nextLine();
            System.out.println(InterfaceStrings.CHANGE_SUCCESSFUL);
        } else {
            if (answer.equalsIgnoreCase("no")) {
                System.out.println(InterfaceStrings.AS_YOU_WISH);
            } else {
                System.out.println(InterfaceStrings.UNDEFINED_NICKNAME_ANSWER);
            }
        }
    }

    public String getPlayerName() {
        return playerName;
    }
}
