package edu.hw2;

public class LoggerStrings {
    private LoggerStrings() {}

    public static final String EXECUTE_SUCCESS = "Executed successfully";
    public static final String CLOSE_CONNECTION = "Connection closed";
    public static final String FAIL_CHANCE = "Chance of fail is %f%%";
    public static final String DELIVERY_FAIL = "Message delivery failed";
    public static final String CONNECTION_FAIL = "Failed connection";
    public static final String FAULTY_PROBABILITY = "Faulty connection probability is %f%%";
    public static final String FAULTY_ESTABLISHED = "Faulty connection established. Noise threshold is %d";
    public static final String STABLE_ESTABLISHED = "Stable connection established";
    public static final String FAILED_COMMAND = "Failed command execute because of %s";
    public static final String UPDATE_COMMAND = "apt update && apt upgrade -y";
    public static final String ATTEMPTS_LEFT = "Attempts left: %d";
    public static final String NO_ATTEMPTS = "no attempts left";
    public static final String PING_PONG_MESSAGE = "PING PONG MESSAGE";
    public static final String UPDATE_PACKAGES = "UPDATE PACKAGES";

    public static final String PING_PONG_COMMAND = "ping && pong -t";
}
