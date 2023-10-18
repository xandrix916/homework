package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Problem3 {
    private final static Logger LOGGER = LogManager.getLogger();
    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class StableConnection implements Connection {

        @Override
        public void execute(String command) {
            LOGGER.info("Executed successfully");
        }

        @Override
        public void close() {
            LOGGER.info("Connection closed");
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
                return (double) length/(double) noiseThreshold;
            }
            int count = length / noiseThreshold;
            for (int i = 0; i < count; i++) {
                if (Math.random() < 0.66) {
                    failChance += 1.0/(double) count;
                }
            }
            return failChance;
        }

        @Override
        public void execute(String command) {
            failChance = isFailChance(command.length());
            LOGGER.info("Chance of fail is %f%%".formatted(failChance*100));
            if (Math.random() < failChance) {
                LOGGER.info("Message delivery failed");
                throw new ConnectionException("failed connection");
            }
            LOGGER.info("Executed successfully");
        }

        @Override
        public void close() {
            LOGGER.info("Connection closed");
        }
    }

    public static class DefaultConnectionManager implements ConnectionManager {
        private final double failProbability;

        public DefaultConnectionManager() {
            this.failProbability = Math.random();
        }

        @Override
        public Connection getConnection() {
            LOGGER.info("Faulty connection probability is %f%%".formatted(failProbability*100));
            if (Math.random() < failProbability) {
                int threshold = (int) (Math.random() * 10) + 10;
                LOGGER.info("Faulty connection established. Noise threshold is %d".formatted(threshold));
                return new FaultyConnection(threshold);
            }
            LOGGER.info("Stable connection established");
            return new StableConnection();
        }
    }

    public static class FaultyConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            LOGGER.info("Faulty connection established. Noise threshold is %d".formatted(7));
            return new FaultyConnection(7);
        }
    }

    public static class ConnectionException extends RuntimeException {
        private final String causeOfException;

        public ConnectionException(String cause) {
            this.causeOfException = cause;
        }
        @Override
        public void printStackTrace() {
            LOGGER.info("Failed command execute because of %s".formatted(causeOfException));
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
            tryExecute("apt update && apt upgrade -y");
        }

        void tryExecute(String command) throws ConnectionException{
            int attemptsLeft = maxAttempts;
            while (attemptsLeft != 0) {
                LOGGER.info("Attempts left: %d".formatted(attemptsLeft));
                try (Connection connection = manager.getConnection()){
                    connection.execute(command);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                attemptsLeft--;
            }
            if (attemptsLeft == 0) {
                throw new ConnectionException("no attempts left");
            }
        }
    }

    public void exampleLaunch() {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new DefaultConnectionManager(), 3);
        LOGGER.info("PING PONG MESSAGE");
        popularCommandExecutor.tryExecute("ping && pong -t");
        LOGGER.info("UPDATE PACKAGES");
        popularCommandExecutor.updatePackages();
    }

    public void faultyLaunch() {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new FaultyConnectionManager(), 3);
        LOGGER.info("PING PONG MESSAGE");
        popularCommandExecutor.tryExecute("ping && pong -t");
        LOGGER.info("UPDATE PACKAGES");
        popularCommandExecutor.updatePackages();
    }
}
