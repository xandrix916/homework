package edu.project2;

public class Main {
    private Main() {
    }

    public static String testGenerate(int[] edgeOrder, int n, int m, int[] startIndex, int[] endIndex, Cell.WallSide startWallSide,
        Cell.WallSide endWallSide, boolean solve) {
        Generator kruskal = new Kruskal(edgeOrder);
        Maze maze = kruskal.generate(n, m);
        Cell.Location startCell = maze.getCellByCoordinates(startIndex[0], startIndex[1]).getLocation();
        Cell.Location endCell = maze.getCellByCoordinates(endIndex[0], endIndex[1]).getLocation();
        maze.pickExitsManually(startCell, endCell, startWallSide, endWallSide);
        maze.render();
        if (solve) {
            Solver tremaux = new TremauxSolver(maze);
            maze.setSolver(tremaux);
            maze.getRenderer().setNoMarks(true);
            maze.render();
        }
        return maze.getRenderer().toString();
    }

    public static void main(String[] args) {}
}
