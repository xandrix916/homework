package edu.project2;

import java.util.ArrayList;
import java.util.List;

public final class Maze {
    private final int mazeHeight;
    private final int mazeWidth;
    private final Cell[][] maze;

    private int[] numbersArray;

    private final int edgeClusterSize;
    private final int edgeAmount;


    private final Vertex[][] vertices;

    private final List<Edge> edges;

    private final Renderer renderer;

    private Cell.Location start;

    private Cell.Location end;

    private Cell.WallSide startSide;
    private Cell.WallSide endSide;

    private void initMaze() {
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                maze[i][j] = new Cell(new Cell.Location(i, j));
            }
        }
    }

    private void initVertices() {
        for (int i = 0; i <= mazeHeight; i++) {
            for (int j = 0; j <= mazeWidth; j++) {
                vertices[i][j] = new Vertex(i, j);
            }
        }
    }

    private void arrangeEdges() {
        for (int i = 0; i <= mazeHeight; i++) {
            for (int j = 0; j <= mazeWidth; j++) {
                vertices[i][j].updateEdges(vertices);
            }
        }
    }

    private void stickCellsWithEdges() {
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                maze[i][j].updateWallStatus(vertices);
            }
        }
    }

    private int clusterSize() {
        return 2 * mazeWidth - 1;
    }

    private void initEdges() {
        int orientation;
        int row;
        for (int i = 0; i < edgeAmount; i++) {
            orientation = i % edgeClusterSize;
            row = i / edgeClusterSize;
            if (orientation < mazeWidth - 1) {
                edges.add(vertices[row][orientation + 1].getEdgeBySide(Cell.WallSide.SOUTH));
            } else {
                edges.add(vertices[row + 1][orientation - mazeWidth + 1].getEdgeBySide(Cell.WallSide.EAST));
            }
            edges.get(i).setNumber(i);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private int getRandomInt(int startRange, int closeRange) {
        return startRange + (int) (Math.random() * closeRange);
    }

    private Cell.Location pickRandomEdgeCell(Cell.WallSide wallSide) {
        return switch (wallSide) {
            case NORTH -> new Cell.Location(0, getRandomInt(0, mazeWidth));
            case SOUTH -> new Cell.Location(mazeHeight - 1, getRandomInt(0, mazeWidth));
            case WEST -> new Cell.Location(getRandomInt(0, mazeHeight), 0);
            case EAST -> new Cell.Location(getRandomInt(0, mazeHeight), mazeWidth - 1);
        };
    }
    public void pickRandomEntranceExit() {
        Cell.WallSide entranceSide;
        Cell.WallSide exitSide;
        Cell.WallSide[] wallSides = new Cell.WallSide[]{Cell.WallSide.NORTH, Cell.WallSide.SOUTH, Cell.WallSide.WEST, Cell.WallSide.EAST};
        entranceSide = wallSides[getRandomInt(0, wallSides.length)];
        exitSide = wallSides[getRandomInt(0, wallSides.length)];
        if (entranceSide.equals(exitSide)) {
            this.start = pickRandomEdgeCell(entranceSide);
            this.end = switch (entranceSide) {
                case NORTH, SOUTH  -> (start.col() * 2 + 1 == mazeWidth ? new Cell.Location(start.row(), 0)
                    : new Cell.Location(start.row(), mazeWidth - 1 - start.col())) ;
                case WEST, EAST -> (start.row() * 2 + 1 == mazeHeight ? new Cell.Location(0, start.col())
                    : new Cell.Location(mazeHeight - 1 - start.row(), start.col()));
            };
            this.startSide = entranceSide;
            this.endSide = this.startSide;
        } else {
            this.start = pickRandomEdgeCell(entranceSide);
            this.end = pickRandomEdgeCell(exitSide);
            this.startSide = entranceSide;
            this.endSide = exitSide;
        }
    }


    public Maze(int mazeHeight, int mazeWidth) {
        this.mazeHeight = mazeHeight;
        this.mazeWidth = mazeWidth;

        this.maze = new Cell[mazeHeight][mazeWidth];
        initMaze();

        this.vertices = new Vertex[mazeHeight + 1][mazeWidth + 1];
        initVertices();
        arrangeEdges();

        this.edges = new ArrayList<>();
        edgeAmount = (mazeWidth - 1) * mazeHeight + (mazeHeight - 1) * mazeWidth;
        edgeClusterSize = clusterSize();
        initEdges();

        stickCellsWithEdges();

        for (var e: edges) {
            e.setConnectedCells(this);
        }

        pickRandomEntranceExit();


        this.renderer = new Renderer(this, 9, 3);
    }



    public int getMazeHeight() {
        return mazeHeight;
    }

    public int getMazeWidth() {
        return mazeWidth;
    }

    public Vertex getVertex(int row, int col) {
        if (0 <= row && row <= mazeHeight && 0 <= col && col <= mazeWidth) {
            return vertices[row][col];
        }
        return null;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Cell[][] getCells() {
        return maze;
    }

    public void setNumbersArray(int[] numbersArray) {
        this.numbersArray = numbersArray.clone();
    }

    public void render() {
        renderer.render();
    }

    public int[] getNumbersArray() {
        return numbersArray;
    }

    public Cell getCellByCoordinates(int row, int col) {
        return maze[row][col];
    }

    public Cell.Location getStart() {
        return start;
    }

    public Cell.Location getEnd() {
        return end;
    }

    public Cell.WallSide getEndSide() {
        return endSide;
    }

    public Cell.WallSide getStartSide() {
        return startSide;
    }
}
