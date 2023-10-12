package edu.project1;

public class Game {
    private final Player player;
    private final GameStatus gameStatus;
    private final StringProcessor stringProcessor;

    public Game() {
        Config config = new Config();
        this.player = new Player();
        this.stringProcessor = new StringProcessor(config.getRandomWord());
        this.gameStatus = new GameStatus(config.getTotalAttempts(),stringProcessor,player);
        player.updateGameStatus(gameStatus);
    }

    public void run() {
        boolean isTerminal = false;
        player.changeName();
        while (!isTerminal) {
            System.out.println("GUESS WHAT REMAINS:\n"+stringProcessor.getStringWithMask());
            player.makeMove();
            gameStatus.checkStatusAndOutputInfo();
            isTerminal = gameStatus.isTerminal();
            System.out.printf("Attempts left: %d of %d\n", gameStatus.getAttemptsLeft(), gameStatus.getTotalAttempts());
        }
        System.out.println("Thanks for playing");
    }
}
