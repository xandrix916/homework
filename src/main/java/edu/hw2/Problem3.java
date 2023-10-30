package edu.hw2;

import lombok.extern.slf4j.Slf4j;
import static edu.hw2.LoggerStrings.CLOSE_CONNECTION;
import static edu.hw2.LoggerStrings.EXECUTE_SUCCESS;
import static edu.hw2.LoggerStrings.FAILED_COMMAND;
import static edu.hw2.LoggerStrings.FAULTY_ESTABLISHED;
import static edu.hw2.LoggerStrings.PING_PONG_COMMAND;
import static edu.hw2.LoggerStrings.PING_PONG_MESSAGE;
import static edu.hw2.LoggerStrings.UPDATE_PACKAGES;

@Slf4j
@SuppressWarnings("InnerTypeLast")
public class Problem3 {
    public static final double FAULTY_CONNECTION_SINGLE_STEP_FAIL_CHANCE = 0.66;
    public static final int TO_NATURAL_PERCENTS = 100;
    public static final int GET_RANDOM_TEN_TO_TWENTY = 10;
    private static final int FAULTY_CONNECTION_MANAGER_DEFAULT_THRESHOLD_VALUE = 7;
    private static final int DEFAULT_RETRY_AMOUNT = 3;


    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class StableConnection implements Connection {

        @Override
        public void execute(String command) {
            log.info(EXECUTE_SUCCESS);
        }

        @Override
        public void close() {
            log.info(CLOSE_CONNECTION);
        }
    }



    public static class FaultyConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            log.warn(FAULTY_ESTABLISHED.formatted(FAULTY_CONNECTION_MANAGER_DEFAULT_THRESHOLD_VALUE));
            return new FaultyConnection(FAULTY_CONNECTION_MANAGER_DEFAULT_THRESHOLD_VALUE);
        }
    }

    public static class ConnectionException extends RuntimeException {
        private final String causeOfException;

        public ConnectionException(String cause) {
            this.causeOfException = cause;
        }

        @Override
        public void printStackTrace() {
            log.error(FAILED_COMMAND.formatted(causeOfException));
        }
    }



    public void exampleLaunch() {
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(), DEFAULT_RETRY_AMOUNT);
        log.info(PING_PONG_MESSAGE);
        popularCommandExecutor.tryExecute(PING_PONG_COMMAND);
        log.info(UPDATE_PACKAGES);
        popularCommandExecutor.updatePackages();
    }

    public void faultyLaunch() {
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new FaultyConnectionManager(), DEFAULT_RETRY_AMOUNT);
        log.info(PING_PONG_MESSAGE);
        popularCommandExecutor.tryExecute(PING_PONG_COMMAND);
        log.info(UPDATE_PACKAGES);
        popularCommandExecutor.updatePackages();
    }

}
