package edu.project1;

public class Game {
    private final Player player;
    private final GameStatus gameStatus;
    private final StringProcessor stringProcessor;

    private final GameJournal gameJournal;
    private final Scaffold scaffold;

    public Game(String word) {
        Config config = new Config();
        this.gameJournal = new GameJournal();
        this.player = new Player(gameJournal);
        this.stringProcessor = new StringProcessor(word.equals("") ? config.getRandomWord() : word);
        this.gameStatus = new GameStatus(config.getTotalAttempts(),stringProcessor,player,gameJournal);
        player.updateGameStatus(gameStatus);
        this.scaffold = new Scaffold(gameStatus);
    }

    private void statusOutput() {
        System.out.printf("THERE %s LEFT\n", (gameStatus.getLettersLeft() == 1 ? "IS ONE LETTER" : "ARE %d LETTERS".formatted(gameStatus.getLettersLeft())));
        System.out.println(stringProcessor.getStringWithMask());
        System.out.println(scaffold.getFrame());
        System.out.println("USED: " + gameStatus.getUsedLettersString());
        System.out.println("WRONG: " + gameStatus.getWrongLettersString());
        System.out.printf("Attempts left: %d of %d\n", gameStatus.getAttemptsLeft(), gameStatus.getTotalAttempts());
    }

    private void defaultRun() {
        boolean isTerminal = false;
        while (!isTerminal) {
            statusOutput();
            player.makeMove(new Turn(gameStatus.getNumberOfMove()));
            gameStatus.checkStatusAndOutputInfo();
            isTerminal = gameStatus.isTerminal();
        }
    }

    private void runWithGivenTurns(Turn[] turns) {
        gameStatus.updateTurns(turns);
        for (var t: turns) {
            statusOutput();
            player.makeMove(t);
            gameStatus.checkStatusAndOutputInfo();
            if (gameStatus.isTerminal()) {
                break;
            }
        }
    }

    public GameJournal run(Turn[] turns) {
        if (turns.length == 0) {
            player.changeName();
            defaultRun();
        }
        else {
            runWithGivenTurns(turns);
        }
        if (gameStatus.getState() == State.WIN) {
            System.out.printf("Congratulations, %s, you win this game!\n", player.getPlayerName());
            System.out.printf("You used %d attempts and made %d turns\n",gameStatus.getTotalAttempts()-gameStatus.getAttemptsLeft(), gameStatus.getNumberOfMove()-1);
        }
        if (gameStatus.getState() == State.FAIL) {
            System.out.printf("THERE %s LEFT\n", (gameStatus.getLettersLeft() == 1 ? "IS ONE LETTER" : "ARE %d LETTERS".formatted(gameStatus.getLettersLeft())));
            System.out.println(stringProcessor.getStringWithMask());
            System.out.println(scaffold.getFrame());
            System.out.println("You wasted all attempts");
            System.out.println("GAME OVER");
        }
        System.out.println("Thanks for playing");
        return this.gameJournal;
    }

}
