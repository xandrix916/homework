package edu.project1;

public class Game {
    private final Player player;
    private final GameStatus gameStatus;
    private final StringProcessor stringProcessor;

    private final Scaffold scaffold;

    public Game() {
        Config config = new Config();
        this.player = new Player();
        this.stringProcessor = new StringProcessor(config.getRandomWord());
        this.gameStatus = new GameStatus(config.getTotalAttempts(),stringProcessor,player);
        player.updateGameStatus(gameStatus);
        this.scaffold = new Scaffold(gameStatus);
    }

    public void run() {
        boolean isTerminal = false;
        player.changeName();
        while (!isTerminal) {
            System.out.printf("THERE %s LEFT\n", (gameStatus.getLettersLeft() == 1 ? "IS ONE LETTER" : "ARE %d LETTERS".formatted(gameStatus.getLettersLeft())));
            System.out.println(stringProcessor.getStringWithMask());
            System.out.println(scaffold.getFrame());
            System.out.printf("Attempts left: %d of %d\n", gameStatus.getAttemptsLeft(), gameStatus.getTotalAttempts());
            player.makeMove();
            gameStatus.checkStatusAndOutputInfo();
            isTerminal = gameStatus.isTerminal();
        }
        if (gameStatus.getState() == State.WIN) {
            System.out.println("YOU WIN");
        }
        if (gameStatus.getState() == State.FAIL) {
            System.out.printf("THERE %s LEFT\n", (gameStatus.getLettersLeft() == 1 ? "IS ONE LETTER" : "ARE %d LETTERS".formatted(gameStatus.getLettersLeft())));
            System.out.println(stringProcessor.getStringWithMask());
            System.out.println(scaffold.getFrame());
            System.out.println("You wasted all attempts");
            System.out.println("GAME OVER");
        }
        System.out.println("Thanks for playing");
    }
}
