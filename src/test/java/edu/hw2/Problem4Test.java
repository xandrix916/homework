package edu.hw2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;

class Problem4Test {
    @BeforeEach
    void setUp() {
        problem4 = new Problem4();
    }

    private Problem4 problem4;

    @Test
    void firstTest() throws Exception {
        String result = tapSystemOut(() -> problem4.problem4());
        Assertions.assertThat(result).isEqualTo("""
            Class name: edu.hw2.Problem4
            Method name: problem4\r
            """);
    }

    @Test
    void doubleTest() throws Exception {
        String result = tapSystemOut(() -> problem4.doubleProblem4());
        Assertions.assertThat(result).isEqualTo("""
            Class name: edu.hw2.Problem4
            Method name: problem4\r
            Class name: edu.hw2.Problem4
            Method name: doubleProblem4\r
            """);
    }

    @Test
    void mainTest() throws Exception {
        String result = tapSystemOut(() -> Main.main(new String[] {}));
        Assertions.assertThat(result).isEqualTo("""
            Class name: edu.hw2.Main
            Method name: main\r
            """);
    }
}
