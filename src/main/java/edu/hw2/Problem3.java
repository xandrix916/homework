package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw2.LoggerStrings.ATTEMPTS_LEFT;
import static edu.hw2.LoggerStrings.CLOSE_CONNECTION;
import static edu.hw2.LoggerStrings.CONNECTION_FAIL;
import static edu.hw2.LoggerStrings.DELIVERY_FAIL;
import static edu.hw2.LoggerStrings.EXECUTE_SUCCESS;
import static edu.hw2.LoggerStrings.FAILED_COMMAND;
import static edu.hw2.LoggerStrings.FAIL_CHANCE;
import static edu.hw2.LoggerStrings.FAULTY_ESTABLISHED;
import static edu.hw2.LoggerStrings.FAULTY_PROBABILITY;
import static edu.hw2.LoggerStrings.NO_ATTEMPTS;
import static edu.hw2.LoggerStrings.PING_PONG_COMMAND;
import static edu.hw2.LoggerStrings.PING_PONG_MESSAGE;
import static edu.hw2.LoggerStrings.STABLE_ESTABLISHED;
import static edu.hw2.LoggerStrings.UPDATE_COMMAND;
import static edu.hw2.LoggerStrings.UPDATE_PACKAGES;

@SuppressWarnings("InnerTypeLast")
public class Problem3 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static double TWO_THIRDS = 0.66;
    private final static int HUNDRED = 100;
    private static final int TEN = 10;
    private static final int SEVEN = 7;
    private static final int THREE = 3;


    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class StableConnection implements Connection {

        @Override
        public void execute(String command) {
            LOGGER.info(EXECUTE_SUCCESS);
        }

        @Override
        public void close() {
            LOGGER.info(CLOSE_CONNECTION);
        }
    }

    public static class FaultyConnection implements Connection {

        private double failChance = 0.0;
        private final int noiseThreshold;

        public FaultyConnection(int noiseThreshold) {
            this.noiseThreshold = noiseThreshold;
        }

        private double isFailChance(int length) {
            if (length < noiseThreshold) {
                return (double) length / (double) noiseThreshold;
            }
            int count = length / noiseThreshold;
            for (int i = 0; i < count; i++) {
                if (Math.random() < TWO_THIRDS) {
                    failChance += 1.0 / (double) count;
                }
            }
            return failChance;
        }

        @Override
        public void execute(String command) {
            failChance = isFailChance(command.length());
            LOGGER.info(FAIL_CHANCE.formatted(failChance * HUNDRED));
            if (Math.random() < failChance) {
                LOGGER.info(DELIVERY_FAIL);
                throw new ConnectionException(CONNECTION_FAIL);
            }
            LOGGER.info(EXECUTE_SUCCESS);
        }

        @Override
        public void close() {
            LOGGER.info(CLOSE_CONNECTION);
        }
    }

    public static class DefaultConnectionManager implements ConnectionManager {
        private final double failProbability;

        public DefaultConnectionManager() {
            this.failProbability = Math.random();
        }

        @Override
        public Connection getConnection() {
            LOGGER.info(FAULTY_PROBABILITY.formatted(failProbability * HUNDRED));
            if (Math.random() < failProbability) {
                int threshold = (int) (Math.random() * TEN) + TEN;
                LOGGER.info(FAULTY_ESTABLISHED.formatted(threshold));
                return new FaultyConnection(threshold);
            }
            LOGGER.info(STABLE_ESTABLISHED);
            return new StableConnection();
        }
    }

    public static class FaultyConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            LOGGER.info(FAULTY_ESTABLISHED.formatted(SEVEN));
            return new FaultyConnection(SEVEN);
        }
    }

    public static class ConnectionException extends RuntimeException {
        private final String causeOfException;

        public ConnectionException(String cause) {
            this.causeOfException = cause;
        }

        @Override
        public void printStackTrace() {
            LOGGER.info(FAILED_COMMAND.formatted(causeOfException));
        }
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;

        public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
            this.manager = manager;
            this.maxAttempts = maxAttempts;
        }

        public void updatePackages() {
            tryExecute(UPDATE_COMMAND);
        }

        @SuppressWarnings("RegexpSinglelineJava")
        void tryExecute(String command) throws ConnectionException {
            int attemptsLeft = maxAttempts;
            while (attemptsLeft != 0) {
                LOGGER.info(ATTEMPTS_LEFT.formatted(attemptsLeft));
                try (Connection connection = manager.getConnection()) {
                    connection.execute(command);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                attemptsLeft--;
            }
            if (attemptsLeft == 0) {
                throw new ConnectionException(NO_ATTEMPTS);
            }
        }
    }

    public void exampleLaunch() {
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(), THREE);
        LOGGER.info(PING_PONG_MESSAGE);
        popularCommandExecutor.tryExecute(PING_PONG_COMMAND);
        LOGGER.info(UPDATE_PACKAGES);
        popularCommandExecutor.updatePackages();
    }

    public void faultyLaunch() {
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new FaultyConnectionManager(), THREE);
        LOGGER.info(PING_PONG_MESSAGE);
        popularCommandExecutor.tryExecute(PING_PONG_COMMAND);
        LOGGER.info(UPDATE_PACKAGES);
        popularCommandExecutor.updatePackages();
    }
}
