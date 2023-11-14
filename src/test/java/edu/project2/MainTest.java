package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void generateOnlyFirst() {
        int[] edgeOrder = new int[]{8, 5, 10, 9, 0, 4, 1, 7, 11, 6, 3, 2};
        String response = Main.testGenerate(edgeOrder, 3, 3, new int[]{0, 0}, new int[]{2, 0},
            Cell.WallSide.NORTH, Cell.WallSide.WEST, false);
        assertEquals(Responses.responseGenerateFirst, response);
    }

    @Test
    void generateOnlySecond() {
        int[] edgeOrder = new int[]{11, 7, 1, 4, 3, 8, 0, 10, 9, 2, 6, 5};
        String response = Main.testGenerate(edgeOrder, 3, 3, new int[]{0, 0}, new int[]{1, 0},
            Cell.WallSide.NORTH, Cell.WallSide.WEST, false);
        assertEquals(Responses.responseGenerateSecond, response);
    }

    @Test
    void generateOnlyThird() {
        int[] edgeOrder = new int[]{6, 5, 9, 3, 8, 0, 7, 1, 11, 10, 4, 2};
        String response = Main.testGenerate(edgeOrder, 3, 3, new int[]{1, 0}, new int[]{1, 2},
            Cell.WallSide.WEST, Cell.WallSide.EAST, false);
        assertEquals(Responses.responseGenerateThird, response);
    }

    @Test
    void generateAndSolveFirst() {
        int[] edgeOrder = new int[]{8, 5, 10, 9, 0, 4, 1, 7, 11, 6, 3, 2};
        String response = Main.testGenerate(edgeOrder, 3, 3, new int[]{0, 0}, new int[]{2, 0},
            Cell.WallSide.NORTH, Cell.WallSide.WEST, true);
        assertEquals(Responses.responseGenerateSolveFirst, response);
    }

    @Test
    void generateAndSolveSecond() {
        int[] edgeOrder = new int[]{11, 7, 1, 4, 3, 8, 0, 10, 9, 2, 6, 5};
        String response = Main.testGenerate(edgeOrder, 3, 3, new int[]{0, 0}, new int[]{1, 0},
            Cell.WallSide.NORTH, Cell.WallSide.WEST, true);
        assertEquals(Responses.responseGenerateSolveSecond, response);
    }

    @Test
    void generateAndSolveThird() {
        int[] edgeOrder = new int[]{6, 5, 9, 3, 8, 0, 7, 1, 11, 10, 4, 2};
        String response = Main.testGenerate(edgeOrder, 3, 3, new int[]{1, 0}, new int[]{1, 2},
            Cell.WallSide.WEST, Cell.WallSide.EAST, true);
        assertEquals(Responses.responseGenerateSolveThird, response);
    }

    @Test
    void definitiveTest() {
        int[] edgeOrder = new int[]{16, 9, 0, 23, 30, 25, 27, 2, 32, 39, 10, 29, 24, 19, 12, 8, 17, 14, 35,
            28, 34, 1, 3, 37, 5, 21, 31, 36, 15, 38, 18, 33, 13, 7, 4, 26, 11, 6, 22, 20};
        String response = Main.testGenerate(edgeOrder, 5, 5, new int[]{0, 0}, new int[]{2, 0},
            Cell.WallSide.NORTH, Cell.WallSide.WEST, true);
        assertEquals(Responses.responseDefinitive, response);
    }
}
