package edu.hw1;

public class Problem8 {
    private final int boardBound = 8;
    private final int twoBlocksDown = -2;

    private int[][] allPossibleWays(int letter, int digit) {
        int[][] allWays = new int[boardBound][2];
        int counter = 0;
        int[][] possibleCombinations = {{twoBlocksDown, -1}, {twoBlocksDown, 1}, {-1, twoBlocksDown}, {-1, 2}, {1, 2},
            {1, twoBlocksDown}, {2, 1}, {2, -1}};
        for (var comb: possibleCombinations) {
            int dig = comb[0];
            int let = comb[1];
            if (0 <= letter + let && letter + let < boardBound && 0 <= digit + dig && digit + dig < boardBound) {
                allWays[counter][1] = letter + let;
                allWays[counter][0] = digit + dig;
                counter++;
            }
        }
        if (counter < boardBound) {
            int[][] finalAllWays = new int[counter][2];
            for (int i = 0; i < counter; i++) {
                finalAllWays[i][0] = allWays[i][0];
                finalAllWays[i][1] = allWays[i][1];
            }
            return finalAllWays;
        }
        return allWays;
    }

    public boolean knightBoardCapture(int[][] board) {
        for (int dig = 0; dig < boardBound; dig++) {
            for (int let = 0; let < boardBound; let++) {
                if (board[dig][let] == 1) {
                    int[][] waysToGo = allPossibleWays(let, dig);
                    for (var pos: waysToGo) {
                        if (board[pos[0]][pos[1]] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
