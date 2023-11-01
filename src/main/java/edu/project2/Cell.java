package edu.project2;

//import java.util.EnumMap;

public class Cell {
    private final Location location;
    //private final EnumMap<WallSide, Boolean> wallStatus = new EnumMap<>(WallSide.class);

    public Cell(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public record Location(int row, int col) {}

    public enum WallSide {
        NORTH,
        SOUTH,
        WEST,
        EAST
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
}
