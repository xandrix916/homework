package edu.hw1;

public class Problem8 {

    private int[][] allPossibleWays(int letter, int digit){
        int[][] allWays = new int[8][2];
        int counter = 0;
        int[][] possibleCombinations = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, 2}, {1, -2}, {2, 1}, {2, -1}};
        for (var comb:
             possibleCombinations) {
            int dig = comb[0], let = comb[1];
            if (0 <= letter+let && letter + let < 8 && 0 <= digit + dig && digit + dig < 8) {
                allWays[counter][1] = letter + let;
                allWays[counter][0] = digit + dig;
                counter++;
            }
        }
        if (counter < 8){
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
        for (int dig = 0; dig < 8; dig++) {
            for (int let = 0; let < 8; let++) {
                if (board[dig][let] == 1) {
                    int[][] waysToGo = allPossibleWays(let, dig);
                    for (var pos:
                         waysToGo) {
                        if (board[pos[0]][pos[1]] == 1)
                            return false;
                    }
                }
            }
        }
        return true;
    }
}
