package edu.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem8Test {
    @BeforeEach
    void setUp() {
        problem8 = new Problem8();
    }

    private Problem8 problem8;

    @Test
    void exampleTest() {
        String response = problem8.backwardsCheck(List.of(1, 2, 3));
        assertEquals("3\n2\n1\n", response);
    }

    @Test
    void masterYoda() {
        String response = problem8.backwardsCheck(List.of("padawan", "young", "you", "be with", "the force", "may"));
        assertEquals("""
            may
            the force
            be with
            you
            young
            padawan
            """, response);
    }

    @Test
    void hollowCollection() {
        String response = problem8.backwardsCheck(new ArrayList<>());
        assertEquals("", response);
    }
}
