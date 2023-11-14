package edu.project2;

import java.util.EnumMap;

public class Edge {
    private final Vertex firstVertex;
    private final Vertex secondVertex;

    private int number;

    private final EnumMap<Cell.WallSide, Cell> connectedCells = new EnumMap<>(Cell.WallSide.class) {{
        put(Cell.WallSide.NORTH, null);
        put(Cell.WallSide.SOUTH, null);
        put(Cell.WallSide.WEST, null);
        put(Cell.WallSide.EAST, null);
    }};

    private boolean isActive;

    private boolean checkMazeSide(Maze maze) {
        if (firstVertex.getVertexCol() == secondVertex.getVertexCol() && (firstVertex.getVertexCol() == 0
            || firstVertex.getVertexCol() == maze.getMazeWidth())) {
            return true;
        }
        return firstVertex.getVertexRow() == secondVertex.getVertexRow() && (firstVertex.getVertexRow() == 0
            || secondVertex.getVertexRow() == maze.getMazeHeight());
    }

    public Edge(Vertex firstVertex, Vertex secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        isActive = true;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

    public void setConnectedCells(Maze maze) {
        if (!checkMazeSide(maze)) {
            if (firstVertex.getVertexCol() == secondVertex.getVertexCol()) {
                int row = Math.min(firstVertex.getVertexRow(),
                    secondVertex.getVertexRow());
                connectedCells.put(Cell.WallSide.WEST, maze.getCells()[row][firstVertex.getVertexCol() - 1]);
                connectedCells.put(Cell.WallSide.EAST, maze.getCells()[row][firstVertex.getVertexCol()]);
            } else {
                int col = Math.min(
                    firstVertex.getVertexCol(), secondVertex.getVertexCol());
                connectedCells.put(Cell.WallSide.NORTH, maze.getCells()[firstVertex.getVertexRow() - 1][col]);
                connectedCells.put(Cell.WallSide.SOUTH, maze.getCells()[firstVertex.getVertexRow()][col]);
            }
        }
    }

    public EnumMap<Cell.WallSide, Cell> getConnectedCells() {
        return connectedCells;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
