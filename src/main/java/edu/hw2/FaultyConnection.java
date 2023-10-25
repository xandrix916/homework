package edu.hw2;

import lombok.extern.slf4j.Slf4j;
import static edu.hw2.LoggerStrings.CLOSE_CONNECTION;
import static edu.hw2.LoggerStrings.CONNECTION_FAIL;
import static edu.hw2.LoggerStrings.DELIVERY_FAIL;
import static edu.hw2.LoggerStrings.EXECUTE_SUCCESS;
import static edu.hw2.LoggerStrings.FAIL_CHANCE;
import static edu.hw2.Problem3.FAULTY_CONNECTION_SINGLE_STEP_FAIL_CHANCE;
import static edu.hw2.Problem3.TO_NATURAL_PERCENTS;

@Slf4j
public class FaultyConnection implements Problem3.Connection {

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
            if (Math.random() < FAULTY_CONNECTION_SINGLE_STEP_FAIL_CHANCE) {
                failChance += 1.0 / (double) count;
            }
        }
        return failChance;
    }

    @Override
    public void execute(String command) {
        failChance = isFailChance(command.length());
        log.info(FAIL_CHANCE.formatted(failChance * TO_NATURAL_PERCENTS));
        if (Math.random() < failChance) {
            log.info(DELIVERY_FAIL);
            throw new Problem3.ConnectionException(CONNECTION_FAIL);
        }
        log.info(EXECUTE_SUCCESS);
    }

    @Override
    public void close() {
        log.info(CLOSE_CONNECTION);
    }
}
