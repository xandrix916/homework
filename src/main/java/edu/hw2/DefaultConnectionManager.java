package edu.hw2;

import lombok.extern.slf4j.Slf4j;
import static edu.hw2.LoggerStrings.FAULTY_ESTABLISHED;
import static edu.hw2.LoggerStrings.FAULTY_PROBABILITY;
import static edu.hw2.LoggerStrings.STABLE_ESTABLISHED;
import static edu.hw2.Problem3.GET_RANDOM_TEN_TO_TWENTY;
import static edu.hw2.Problem3.TO_NATURAL_PERCENTS;

@Slf4j
public class DefaultConnectionManager implements Problem3.ConnectionManager {
    private final double failProbability;

    public DefaultConnectionManager() {
        this.failProbability = Math.random();
    }

    @Override
    public Problem3.Connection getConnection() {
        log.info(FAULTY_PROBABILITY.formatted(failProbability * TO_NATURAL_PERCENTS));
        if (Math.random() < failProbability) {
            int threshold = (int) (Math.random() * GET_RANDOM_TEN_TO_TWENTY) + GET_RANDOM_TEN_TO_TWENTY;
            log.warn(FAULTY_ESTABLISHED.formatted(threshold));
            return new FaultyConnection(threshold);
        }
        log.info(STABLE_ESTABLISHED);
        return new Problem3.StableConnection();
    }
}
