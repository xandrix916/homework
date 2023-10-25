package edu.project2;

import java.util.EnumMap;

public class Cell {
    private final Location location;
    private final EnumMap<WallSide, Boolean> wallStatus = new EnumMap<>(WallSide.class);

    public static final String cellTemplate = """
        []%s[]
        %s  %s
        %s  %s
        []%s[]
        """;

    public Cell(Location location) {
        this.location = location;
        wallStatus.put(WallSide.NORTH, true);
        wallStatus.put(WallSide.SOUTH, true);
        wallStatus.put(WallSide.EAST, true);
        wallStatus.put(WallSide.WEST, true);
    }

    public void updateWall(WallSide side, Boolean value) {
        wallStatus.put(side, value);
    }

    public Location getLocation() {
        return location;
    }

    public boolean isWallThere(WallSide side) {
        return wallStatus.get(side);
    }

    public record Location(int row, int col) {}

    public enum WallSide {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }
}
