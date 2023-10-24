package edu.hw2;

import lombok.extern.slf4j.Slf4j;
import static edu.hw2.LoggerStrings.ATTEMPTS_LEFT;
import static edu.hw2.LoggerStrings.NO_ATTEMPTS;
import static edu.hw2.LoggerStrings.UPDATE_COMMAND;

@Slf4j
public final class PopularCommandExecutor {
    private final Problem3.ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(Problem3.ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute(UPDATE_COMMAND);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    void tryExecute(String command) throws Problem3.ConnectionException {
        int attemptsLeft = maxAttempts;
        while (attemptsLeft != 0) {
            log.info(ATTEMPTS_LEFT.formatted(attemptsLeft));
            try (Problem3.Connection connection = manager.getConnection()) {
                connection.execute(command);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            attemptsLeft--;
        }
        if (attemptsLeft == 0) {
            throw new Problem3.ConnectionException(NO_ATTEMPTS);
        }
    }
}
