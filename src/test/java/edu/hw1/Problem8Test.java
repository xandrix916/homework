package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problem8Test {
    @BeforeEach
    void setUp() {
        problem8 = new Problem8();
    }

    private Problem8 problem8;

    @Test
    void truePositions() {
        int[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
};
        boolean response = problem8.knightBoardCapture(board);
        Assertions.assertThat(response).isTrue();
    }

    @Test
    void falsePositionsFirst() {
        int[][] board = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };
        boolean response = problem8.knightBoardCapture(board);
        Assertions.assertThat(response).isFalse();
    }

    @Test
    void falsePositionsSecond() {
        int[][] board = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };
        boolean response = problem8.knightBoardCapture(board);
        Assertions.assertThat(response).isFalse();
    }

    @Test
    void testChevaliers() {
        int[][] board = {
            {1, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0}
        };
        boolean response = problem8.knightBoardCapture(board);
        Assertions.assertThat(response).isTrue();
    }
}
