package edu.project2;

public final class Maze {
    private final int mazeHeight;
    private final int mazeWidth;
    private final Cell[][] maze;

    private final Vertex[][] vertices;

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


    public Maze(int mazeHeight, int mazeWidth) {
        this.mazeHeight = mazeHeight;
        this.mazeWidth = mazeWidth;

        this.maze = new Cell[mazeHeight][mazeWidth];
        initMaze();

        this.vertices = new Vertex[mazeHeight + 1][mazeWidth + 1];
        initVertices();
        arrangeEdges();

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

    public void render() {
        renderer.render();
    }
}
