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

        for (var e: edges) {
            e.setConnectedCells(this);
        }


        this.renderer = new Renderer(this);
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
}
