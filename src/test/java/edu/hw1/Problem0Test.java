package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.*;

class Problem0Test {
    private Problem0 problem0;

    @Test
    void inputOutputTest() throws Exception {
        problem0 = new Problem0();
        String helloWorld = tapSystemErr(() -> problem0.helloWorld());
        String infoString = helloWorld.substring(helloWorld.indexOf("\n")+1, helloWorld.indexOf("\n")+19);
        Assertions.assertEquals("INFO: Привет, мир!", infoString);
    }
}
