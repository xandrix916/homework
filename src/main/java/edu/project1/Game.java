package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private final Player player;
    private final GameStatus gameStatus;
    private final StringProcessor stringProcessor;
    private final static Logger LOGGER = LogManager.getLogger();

    private final GameJournal gameJournal;
    private final Scaffold scaffold;

    public Game(String word) {
        Config config = new Config();
        this.gameJournal = new GameJournal();
        this.player = new Player(gameJournal);
        this.stringProcessor = new StringProcessor(word.equals("") ? config.getRandomWord() : word);
        this.gameStatus = new GameStatus(config.getTotalAttempts(), stringProcessor, player, gameJournal);
        player.updateGameStatus(gameStatus);
        this.scaffold = new Scaffold(gameStatus);
    }


    private void statusOutput() {
        LOGGER.info(InterfaceStrings.LEFT_THERE.formatted((gameStatus.getLettersLeft() == 1
            ? InterfaceStrings.ONE_LETTER
            : InterfaceStrings.SEVERAL_LETTERS.formatted(gameStatus.getLettersLeft()))));
        LOGGER.info(stringProcessor.getStringWithMask());
        LOGGER.info("\n" + scaffold.getFrame());
        LOGGER.info("USED: " + gameStatus.getUsedLettersString());
        LOGGER.info("WRONG: " + gameStatus.getWrongLettersString());
        LOGGER.info("Attempts left: %d of %d\n".formatted(gameStatus.getAttemptsLeft(), gameStatus.getTotalAttempts()));
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
        } else {
            runWithGivenTurns(turns);
        }
        if (gameStatus.getState() == State.WIN) {
            LOGGER.info("Congratulations, %s, you win this game!\n".formatted(player.getPlayerName()));
            LOGGER.info("You used %d attempts and made %d turns\n".formatted(gameStatus.getTotalAttempts()
                - gameStatus.getAttemptsLeft(), gameStatus.getNumberOfMove() - 1));
        }
        if (gameStatus.getState() == State.FAIL) {
            LOGGER.info(InterfaceStrings.LEFT_THERE.formatted((gameStatus.getLettersLeft() == 1
                ? InterfaceStrings.ONE_LETTER
                : InterfaceStrings.SEVERAL_LETTERS.formatted(gameStatus.getLettersLeft()))));
            LOGGER.info(stringProcessor.getStringWithMask());
            LOGGER.info("\n" + scaffold.getFrame());
            LOGGER.info("You wasted all attempts");
            LOGGER.info("GAME OVER");
        }
        LOGGER.info("Thanks for playing");
        return this.gameJournal;
    }

}
