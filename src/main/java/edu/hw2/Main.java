package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void thirdProblemTest() {
        Problem3 problem3 = new Problem3();
        try {
            LOGGER.info("EXAMPLE LAUNCH");
            problem3.exampleLaunch();
        } catch (Problem3.ConnectionException connectionException) {
            connectionException.printStackTrace();
        }
        try {
            LOGGER.info("FAULTY LAUNCH");
            problem3.faultyLaunch();
        } catch (Problem3.ConnectionException connectionException) {
            connectionException.printStackTrace();
        }
    }

    public static String mainFourTest() {
        return Problem4.callingInfo().toString();
    }

    public static void main(String[] args) {
        thirdProblemTest();
    }
}
