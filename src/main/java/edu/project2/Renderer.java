package edu.project2;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Renderer {

    public static final int DEFAULT_CELL_HOR_SIZE = 9;
    public static final int DEFAULT_CELL_VERT_SIZE = 3;
    private static final int GATE_SIZE = 6;
    private final Maze maze;
    private final int cellHorSize;
    private final int cellVertSize;
    private boolean exitsAdded = false;
    private Solver solver = null;

    private int[] startIndex;
    private int[] endIndex;

    private boolean noMarks = false;

    private final RenderIcons[][] args;
    private final EnumMap<RenderIcons, String> iconMap = new EnumMap<>(RenderIcons.class) {{
        put(RenderIcons.VERTEX, "[]");
        put(RenderIcons.VERT_PART_EDGE, "||");
        put(RenderIcons.VERT_HOLLOW, "  ");
        put(RenderIcons.HOR_TRANSITION, "--");
        put(RenderIcons.VERT_TRANSITION, "|");
        put(RenderIcons.VISITED, "■");
        put(RenderIcons.DEAD_END, "X");
    }};

    private String[] templateString() {
        String[] strings = new String[(cellVertSize + 1) * maze.getMazeHeight() + 1];
        Arrays.fill(strings, "%s".repeat(2 * maze.getMazeWidth() + 1) + "\n");
        return strings;
    }

    private String gateString(String avatar) {
        return String.format("%s%s%s%s%s", "==|", " ".repeat((cellHorSize - GATE_SIZE) / 2),
            avatar, " ".repeat((cellHorSize - GATE_SIZE) / 2), "|==");
    }

    private String fromSide(Cell.WallSide side, String visit) {
        return switch (side) {
            case EAST -> " ".repeat((cellHorSize / 2)) + visit + "-".repeat((cellHorSize / 2));
            case WEST -> "-".repeat((cellHorSize / 2)) + visit + " ".repeat((cellHorSize / 2));
            default -> iconMap.get(RenderIcons.VISITED);
        };
    }

    public Renderer(Maze maze, int horSize, int vertSize) {
        this.maze = maze;
        this.cellHorSize = horSize;
        this.cellVertSize = vertSize;

        args = new RenderIcons[(cellVertSize + 1) * maze.getMazeHeight() + 1][2 * maze.getMazeWidth() + 1];

        iconMap.put(RenderIcons.HOR_EDGE, "=".repeat(cellHorSize));
        iconMap.put(RenderIcons.HOR_HOLLOW, " ".repeat(cellHorSize));
        iconMap.put(RenderIcons.HOR_GATE, gateString(" "));
        iconMap.put(RenderIcons.HOR_GATE_WALKED, gateString(iconMap.get(RenderIcons.VERT_TRANSITION)));
        iconMap.put(RenderIcons.PATH_SEGMENT_HOR_FROM_WEST, fromSide(Cell.WallSide.WEST, "|"));
        iconMap.put(RenderIcons.PATH_SEGMENT_HOR_FROM_EAST, fromSide(Cell.WallSide.EAST, "|"));
        iconMap.put(RenderIcons.PATH_SEGMENT_HOR_FULL, "-".repeat(horSize));
        iconMap.put(RenderIcons.PATH_HOR_VISITED, "-".repeat(horSize / 2) + iconMap.get(RenderIcons.VISITED)
            + "-".repeat(horSize / 2));
        iconMap.put(RenderIcons.FROM_EAST_VISITED, fromSide(Cell.WallSide.EAST, iconMap.get(RenderIcons.VISITED)));
        iconMap.put(RenderIcons.FROM_WEST_VISITED, fromSide(Cell.WallSide.WEST, iconMap.get(RenderIcons.VISITED)));
        iconMap.put(RenderIcons.PATH_SEGMENT_VERT, " ".repeat(cellHorSize / 2) + "|" + " ".repeat(cellHorSize / 2));

        iconMap.put(RenderIcons.VISITED, " ".repeat(cellHorSize / 2)
            + iconMap.get(RenderIcons.VISITED) + " ".repeat(cellHorSize / 2));
        iconMap.put(RenderIcons.DEAD_END, " ".repeat(cellHorSize / 2)
            + iconMap.get(RenderIcons.DEAD_END) + " ".repeat(cellHorSize / 2));
    }

    @SuppressWarnings("ReturnCount")
    private Cell.WallSide calculateRelativeSide(Cell firstCell, Cell secondCell) {
        if (firstCell.equals(secondCell)
            || (Math.abs(firstCell.getLocation().row() - secondCell.getLocation().row()) > 1
            && (Math.abs(firstCell.getLocation().col() - secondCell.getLocation().col()) > 1))) {
            return null;
        }
        if (firstCell.getLocation().row() > secondCell.getLocation().row()) {
            return Cell.WallSide.NORTH;
        }
        if (firstCell.getLocation().row() < secondCell.getLocation().row()) {
            return Cell.WallSide.SOUTH;
        }
        if (firstCell.getLocation().col() < secondCell.getLocation().col()) {
            return Cell.WallSide.EAST;
        }
        if (firstCell.getLocation().col() > secondCell.getLocation().col()) {
            return Cell.WallSide.WEST;
        }
        return null;
    }

    @SuppressWarnings({"InnerAssignment", "MissingSwitchDefault"})
    private void drawExitPath(Cell.WallSide wallSide, int[] index) {
        switch (wallSide) {
            case NORTH, SOUTH -> args[index[0]][index[1]] = RenderIcons.HOR_GATE_WALKED;
            case WEST, EAST -> args[index[0]][index[1]] = RenderIcons.HOR_TRANSITION;
        }
    }

    @SuppressWarnings({"MissingSwitchDefault"})
    private void fillHalfCell(Cell.Location location, Cell.WallSide direction) {
        int[] index = getCenterIndex(location);
        switch (direction) {
            case NORTH -> {
                for (int i = 0; i <= (cellVertSize + 1) / 2; i++) {
                    if (args[index[0] - i][index[1]] == RenderIcons.HOR_HOLLOW) {
                        args[index[0] - i][index[1]] = RenderIcons.PATH_SEGMENT_VERT;
                    }
                }
            }
            case SOUTH -> {
                for (int i = 0; i <= (cellVertSize + 1) / 2; i++) {
                    if (args[index[0] + (cellVertSize + 1) / 2 - i][index[1]] == RenderIcons.HOR_HOLLOW) {
                        args[index[0] + (cellVertSize + 1) / 2 - i][index[1]] = RenderIcons.PATH_SEGMENT_VERT;
                    }
                }
            }
            case WEST -> {
                args[index[0]][index[1]] = RenderIcons.PATH_SEGMENT_HOR_FROM_EAST;
                if (!(location.equals(maze.getStart()) || location.equals(maze.getEnd()))) {
                    args[index[0]][index[1] + 1] = RenderIcons.HOR_TRANSITION;
                }
            }
            case EAST -> {
                args[index[0]][index[1]] = RenderIcons.PATH_SEGMENT_HOR_FROM_WEST;
                if (!(location.equals(maze.getStart()) || location.equals(maze.getEnd()))) {
                    args[index[0]][index[1] - 1] = RenderIcons.HOR_TRANSITION;
                }
            }
        }
    }

    private int[] getCenterIndex(Cell.Location location) {
        return new int[]{(cellVertSize + 1) * location.row() + (cellVertSize + 1) / 2, 2 * location.col() + 1};
    }

    private void fillFullCell(Cell.Location location, Cell.WallSide direction) {
        int[] index = new int[]{(cellVertSize + 1) * location.row() + 1, 2 * location.col() + 1};
        switch (direction) {
            case NORTH, SOUTH -> {
                for (int i = 0; i < cellVertSize; i++) {
                    if (args[index[0] + i][index[1]] == RenderIcons.HOR_HOLLOW) {
                        args[index[0] + i][index[1]] = RenderIcons.PATH_SEGMENT_VERT;
                    }
                }
            }
            case WEST, EAST -> {
                args[index[0] + 1][index[1]] = RenderIcons.PATH_SEGMENT_HOR_FULL;
                args[index[0] + 1][index[1] + (direction == Cell.WallSide.WEST ? 1 : -1)] = RenderIcons.HOR_TRANSITION;
            }
            default -> {

            }
        }
    }

    private void pickIcon(Cell.Location location, Cell.WallSide initialDirection, Cell.WallSide relativeDirection) {
        if (initialDirection.equals(relativeDirection)) {
            fillFullCell(location, initialDirection);
        } else {
            fillHalfCell(location, (initialDirection == Cell.WallSide.NORTH
                || initialDirection == Cell.WallSide.SOUTH
                ? TremauxSolver.oppositeDirection(initialDirection)
                : initialDirection));
            fillHalfCell(location, (relativeDirection == Cell.WallSide.EAST
                || relativeDirection == Cell.WallSide.WEST
                ? TremauxSolver.oppositeDirection(relativeDirection)
                : relativeDirection));
        }
    }

    private void markVisit(int[] index) {
        args[index[0]][index[1]] = switch (args[index[0]][index[1]]) {
            case PATH_SEGMENT_HOR_FULL -> RenderIcons.PATH_HOR_VISITED;
            case PATH_SEGMENT_HOR_FROM_EAST -> RenderIcons.FROM_EAST_VISITED;
            case PATH_SEGMENT_HOR_FROM_WEST -> RenderIcons.FROM_WEST_VISITED;
            default -> RenderIcons.VISITED;
        };
    }

    private void drawMarks() {
        for (int i = 0; i < maze.getMazeHeight(); i++) {
            for (int j = 0; j < maze.getMazeWidth(); j++) {
                Cell cell = maze.getCellByCoordinates(i, j);
                int[] index = getCenterIndex(cell.getLocation());
                if (cell.isDeadEnd()) {
                    args[index[0]][index[1]]
                        = RenderIcons.DEAD_END;
                } else {
                    if (cell.isVisited()) {
                        markVisit(index);
                    }
                }
            }
        }
    }


    private void drawPath(List<Cell.Location> path) {
        drawExitPath(maze.getStartSide(), startIndex);
        Cell.WallSide currentDirection = TremauxSolver.oppositeDirection(maze.getStartSide());

        for (int i = 0; i < path.size(); i++) {
            Cell.WallSide relativeSide;
            if (i < path.size() - 1) {
                relativeSide = calculateRelativeSide(maze.getCellByCoordinates(path.get(i).row(), path.get(i).col()),
                    maze.getCellByCoordinates(path.get(i + 1).row(), path.get(i + 1).col()));
            } else {
                relativeSide = maze.getEndSide();
            }

            if (relativeSide != null) {
                pickIcon(path.get(i), currentDirection, relativeSide);
                currentDirection = relativeSide;
            }
        }

        if (!noMarks) {
            drawMarks();
        }

        assert maze.getEndSide() != null;
        drawExitPath(maze.getEndSide(), endIndex);
    }


    private String mazeToString() {
        Vertex currentVertex;
        Edge currentEdge;

        for (int i = 0; i <= maze.getMazeHeight(); i++) { //NB: indexes are for maze vertex array. For args I use 2*col,
            for (int j = 0; j <= maze.getMazeWidth(); j++) {  // 5*row
                currentVertex = maze.getVertex(i, j);
                args[(cellVertSize + 1) * i][2 * j] = RenderIcons.VERTEX;

                if (currentVertex != null) {
                    currentEdge = currentVertex.getEdgeBySide(Cell.WallSide.SOUTH);
                    if (currentEdge != null) {
                        for (int k = 1; k <= cellVertSize; k++) {
                            args[(cellVertSize + 1) * i + k][2 * j] = currentEdge.isActive()
                                ? RenderIcons.VERT_PART_EDGE : RenderIcons.VERT_HOLLOW;
                        }
                    }

                    currentEdge = currentVertex.getEdgeBySide(Cell.WallSide.EAST);
                    if (currentEdge != null) {
                        args[(cellVertSize + 1) * i][2 * j + 1] = currentEdge.isActive()
                            ? RenderIcons.HOR_EDGE : RenderIcons.HOR_HOLLOW;
                    }
                }
                if (j != 0 && i != maze.getMazeHeight()) {
                    for (int k = 1; k <= cellVertSize; k++) {
                        args[(cellVertSize + 1) * i + k][2 * j - 1] = RenderIcons.HOR_HOLLOW;
                    }
                }
            }
        }

        if (!exitsAdded) {
            addExits();
            exitsAdded = true;
        }

        if (solver != null) {
            drawPath(solver.solve(maze, maze.getStart(), maze.getEnd()));
        }

        return toString();
    }

    @Override
    public String toString() {
        String[] finalStringArray = templateString();
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < finalStringArray.length; i++) {
            String[] appliedArgs = new String[2 * maze.getMazeWidth() + 1];
            for (int j = 0; j < appliedArgs.length; j++) {
                appliedArgs[j] = iconMap.get(args[i][j]);
            }
            resultString.append(String.format(finalStringArray[i], (Object[]) appliedArgs));
        }
        return resultString.toString();
    }

    private int[] getGateIndex(Cell.Location location, Cell.WallSide wallSide) {
        Cell wantedCell = maze.getCellByCoordinates(location.row(), location.col());
        Edge wantedEdge = wantedCell.getEdgeBySide(wallSide);
        Vertex firstVertex = wantedEdge.getFirstVertex();
        Vertex secondVertex = wantedEdge.getSecondVertex();
        return switch (wallSide) {
            case NORTH, SOUTH -> new int[]{(cellVertSize + 1) * firstVertex.getVertexRow(),
                firstVertex.getVertexCol() + secondVertex.getVertexCol()};
            case WEST, EAST -> new int[]{
                ((cellVertSize + 1) / 2) * (firstVertex.getVertexRow() + secondVertex.getVertexRow()),
                2 * firstVertex.getVertexCol()
            };
        };
    }

    private RenderIcons getProperIcon(Cell.WallSide wallSide) {
        return switch (wallSide) {
            case NORTH, SOUTH -> RenderIcons.HOR_GATE;
            case EAST, WEST -> RenderIcons.VERT_HOLLOW;
        };
    }

    public void addExits() {
        Cell.Location startCell = maze.getStart();
        Cell.Location endCell = maze.getEnd();

        Cell.WallSide entranceSide = maze.getStartSide();
        Cell.WallSide exitSide = maze.getEndSide();

        startIndex = getGateIndex(startCell, entranceSide);
        endIndex = getGateIndex(endCell, exitSide);

        args[startIndex[0]][startIndex[1]] = getProperIcon(entranceSide);
        args[endIndex[0]][endIndex[1]] = getProperIcon(exitSide);
    }

    public void render() {
        log.info(Arrays.toString(maze.getNumbersArray()) + "\n" + mazeToString());
    }

    public void setSolver(Solver solver) {
        this.solver = solver;
    }

    public void setNoMarks(boolean noMarks) {
        this.noMarks = noMarks;
    }
}
