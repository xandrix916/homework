package edu.project2;

//import java.util.EnumMap;

import java.util.EnumMap;

public class Cell {
    private final Location location;

    private boolean visited;
    private boolean deadEnd;
    private final EnumMap<WallSide, Edge> wallStatus = new EnumMap<>(WallSide.class);

    private WallSide exitSide;

    public static final WallSide[] COMPASS_POINTS = new WallSide[]{WallSide.NORTH, WallSide.SOUTH, WallSide.WEST, WallSide.EAST};

    public Cell(Location location) {
        this.location = location;
        wallStatus.put(Cell.WallSide.NORTH, null);
        wallStatus.put(Cell.WallSide.SOUTH, null);
        wallStatus.put(Cell.WallSide.EAST, null);
        wallStatus.put(Cell.WallSide.WEST, null);
        visited = false;
        deadEnd = false;
        exitSide = null;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean isVisited) {
        this.visited = isVisited;
    }

    public boolean isDeadEnd() {
        return deadEnd;
    }

    public void setDeadEnd(boolean deadEnd) {
        this.deadEnd = deadEnd;
    }

    public WallSide getExitSide() {
        return exitSide;
    }

    public void setExitSide(WallSide exitSide) {
        this.exitSide = exitSide;
    }

    public record Location(int row, int col) {}

    public enum WallSide {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }

    private Vertex[] getGroupOfVertices(Vertex[][] vertices) {
        int row = location.row();
        int col = location.col();
        return new Vertex[]{vertices[row][col], vertices[row][col + 1], vertices[row + 1][col], vertices[row + 1][col + 1]};
    }

    public void updateWallStatus(Vertex[][] vertices) {
        Vertex[] cellVertices = getGroupOfVertices(vertices);
        wallStatus.put(WallSide.NORTH, cellVertices[0].getEdgeBySide(WallSide.EAST));
        wallStatus.put(WallSide.WEST, cellVertices[0].getEdgeBySide(WallSide.SOUTH));
        wallStatus.put(WallSide.SOUTH, cellVertices[2].getEdgeBySide(WallSide.EAST));
        wallStatus.put(WallSide.EAST, cellVertices[1].getEdgeBySide(WallSide.SOUTH));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Cell otherCell = (Cell) o;
        return otherCell.location.row == this.location.row && otherCell.location.col == this.location.col;
    }

    public Edge getEdgeBySide(WallSide side) {
        return wallStatus.get(side);
    }
}
