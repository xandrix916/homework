package edu.project2;

public final class Maze {
    private final int mazeHeight;
    private final int mazeWidth;
    private final Cell[][] maze;

    public Maze(int mazeHeight, int mazeWidth) {
        this.mazeHeight = mazeHeight;
        this.mazeWidth = mazeWidth;
        this.maze = new Cell[mazeHeight][mazeWidth];
    }

    public int getMazeHeight() {
        return mazeHeight;
    }

    public int getMazeWidth() {
        return mazeWidth;
    }
}
