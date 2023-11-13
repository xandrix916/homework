package edu.hw5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {
    @BeforeEach
    void setUp() {
        problem1 = new Problem1();
    }

    private Problem1 problem1;

    @Test
    void likeInTask() {
        List<String> inputStrings = new ArrayList<>(List.of("2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"));
        String response = problem1.getMediumDuration(inputStrings);
        assertEquals("3 hours 40 minutes", response);
    }

}
