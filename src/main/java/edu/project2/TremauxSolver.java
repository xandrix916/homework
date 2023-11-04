package edu.project2;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class TremauxSolver implements Solver {
    private final List<Cell.Location> resultPath;
    private final Maze maze;

    private Cell lastJoint = null;

    private Cell lastJointBefore = null;

    public static Cell.WallSide oppositeDirection(Cell.WallSide direction) {
        return switch (direction) {
            case EAST -> Cell.WallSide.WEST;
            case WEST -> Cell.WallSide.EAST;
            case SOUTH -> Cell.WallSide.NORTH;
            case NORTH -> Cell.WallSide.SOUTH;
        };
    }

    @SuppressWarnings("ReturnCount")
    private CellType checkType(Cell examinedCell, Cell.WallSide initialDirection) {
        if (isJoint(examinedCell, initialDirection)) {
            return CellType.JOINT;
        }
        if (isDeadEnd(examinedCell, initialDirection)) {
            return CellType.DEAD_END;
        }
        if (isPassage(examinedCell, initialDirection)) {
            return CellType.PASSAGE;
        }
        if (isTurn(examinedCell, initialDirection)) {
            return CellType.TURN;
        }
        return null;
    }

    private int countUniqueWays(Cell examinedCell, Cell.WallSide initialDirection) {
        int uniqueWaysCounter = 0;
        for (var side: Cell.COMPASS_POINTS) {
            if (examinedCell.getEdgeBySide(side) != null && !examinedCell.getEdgeBySide(side).isActive()
                && side != oppositeDirection(initialDirection) && !getCellBySide(side, examinedCell).isVisited()) {
                    uniqueWaysCounter++;
            }
        }
        return uniqueWaysCounter;
    }

    private boolean isJoint(Cell examinedCell, Cell.WallSide initialDirection) {
        int uniqueWaysCounter = countUniqueWays(examinedCell, initialDirection);
        return uniqueWaysCounter > 1;
    }

    private boolean isDeadEnd(Cell examinedCell, Cell.WallSide initialDirection) {
        int uniqueWaysCounter = countUniqueWays(examinedCell, initialDirection);
        return uniqueWaysCounter == 0;
    }

    private boolean isTurn(Cell examinedCell, Cell.WallSide initialDirection) {
        int uniqueWaysCounter = countUniqueWays(examinedCell, initialDirection);
        boolean notVisited;
        try {
        notVisited = getCellBySide(initialDirection, examinedCell).isVisited();
        } catch (IndexOutOfBoundsException e) {
            notVisited = true;
        }
        return uniqueWaysCounter == 1 && (notVisited || examinedCell.getEdgeBySide(initialDirection).isActive());
    }

    private boolean isPassage(Cell examinedCell, Cell.WallSide initialDirection) {
        int uniqueWaysCounter = countUniqueWays(examinedCell, initialDirection);
        boolean notVisited;
        try {
            notVisited = !getCellBySide(initialDirection, examinedCell).isVisited();
        } catch (IndexOutOfBoundsException e) {
            notVisited = false;
        }
        return uniqueWaysCounter == 1 && notVisited && !examinedCell.getEdgeBySide(initialDirection).isActive();

    }

    private Cell getCellBySide(Cell.WallSide side, Cell examinedCell) throws IndexOutOfBoundsException {
        return switch (side) {
            case NORTH -> maze.getCellByCoordinates(examinedCell.getLocation().row() - 1,
                examinedCell.getLocation().col());
            case SOUTH -> maze.getCellByCoordinates(examinedCell.getLocation().row() + 1,
                examinedCell.getLocation().col());
            case WEST -> maze.getCellByCoordinates(examinedCell.getLocation().row(),
                examinedCell.getLocation().col() - 1);
            case EAST -> maze.getCellByCoordinates(examinedCell.getLocation().row(),
                examinedCell.getLocation().col() + 1);
        };
    }

    private EnumMap<Cell.WallSide, Cell> cellsToGo(Cell examinedCell, Cell.WallSide initialDirection) {
        EnumMap<Cell.WallSide, Cell> cellsAndSides = new EnumMap<>(Cell.WallSide.class);
        for (var side: Cell.COMPASS_POINTS) {
            if (examinedCell.getEdgeBySide(side) != null
                && !examinedCell.getEdgeBySide(side).isActive()
                && side != oppositeDirection(initialDirection)) {
                cellsAndSides.put(side, getCellBySide(side, examinedCell));
            }
        }
        return cellsAndSides;
    }

    private Cell.WallSide chooseDirection(EnumMap<Cell.WallSide, Cell> cellEnumMap) {
        List<Cell.WallSide> possibleDirections = new ArrayList<>();
        for (var side: cellEnumMap.keySet()) {
            if (!cellEnumMap.get(side).isVisited()) {
                possibleDirections.add(side);
            }
        }
        if (possibleDirections.size() > 0) {
            return possibleDirections.get(Maze.getRandomInt(0, possibleDirections.size()));
        } else {
            for (var side: cellEnumMap.keySet()) {
                if (!cellEnumMap.get(side).isDeadEnd()) {
                    possibleDirections.add(side);
                }
            }
            return possibleDirections.get(0);
        }
    }

    private Cell.WallSide deadEndSolver(Cell.Location location, Cell.WallSide direction) {
        Cell currentCell = maze.getCellByCoordinates(location.row(), location.col());
        if (currentCell.isDeadEnd()) {
            Cell.WallSide backwards = oppositeDirection(direction);
            if (getCellBySide(backwards, currentCell).isDeadEnd()) {
                for (var side : Cell.COMPASS_POINTS) {
                    try {
                        if (!getCellBySide(side, currentCell).isDeadEnd()
                            && !currentCell.getEdgeBySide(side).isActive()) {
                            return side;
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            } else {
                    return backwards;
                }
        } else {
            return chooseDirection(cellsToGo(currentCell, direction));
        }
        return null;
    }


    private List<Cell.Location> run() {
        Cell currentCell = maze.getCellByCoordinates(maze.getStart().row(), maze.getStart().col());
        resultPath.add(currentCell.getLocation());

        Cell exitCell = maze.getCellByCoordinates(maze.getEnd().row(), maze.getEnd().col());
        Cell.WallSide currentDirection = oppositeDirection(currentCell.getExitSide());

        while (currentCell != exitCell) {
            var backwards = oppositeDirection(currentDirection);
            CellType cellType = checkType(currentCell, currentDirection);
            assert cellType != null;
            switch (cellType) {
                case PASSAGE -> {
                    currentCell = getCellBySide(currentDirection, currentCell);
                    resultPath.add(currentCell.getLocation());
                }
                case JOINT -> {
                    if (resultPath.size() > 1) {
                        getCellBySide(backwards, currentCell).setVisited(true);
                    }
                    var sideCells = cellsToGo(currentCell, currentDirection);
                    currentDirection = chooseDirection(sideCells);
                    if (lastJoint != null) {
                        lastJointBefore = lastJoint;
                    }
                    lastJoint = currentCell;
                    currentCell = getCellBySide(currentDirection, currentCell);
                    resultPath.add(currentCell.getLocation());
                    currentCell.setVisited(true);
                }
                case TURN -> {
                    var sideCells = cellsToGo(currentCell, currentDirection);
                    currentDirection = chooseDirection(sideCells);
                    currentCell = getCellBySide(currentDirection, currentCell);
                    resultPath.add(currentCell.getLocation());
                }
                case DEAD_END -> {
                    currentCell.setDeadEnd(true);
                    currentDirection = deadEndSolver(currentCell.getLocation(), currentDirection);
                    assert currentDirection != null;
                    currentCell = getCellBySide(currentDirection, currentCell);
                    resultPath.remove(resultPath.get(resultPath.size() - 1));
                    while (!currentCell.getLocation().equals(lastJoint.getLocation())) {
                        if (!resultPath.contains(lastJoint.getLocation())) {
                            lastJoint = lastJointBefore;
                        } else {
                            currentCell.setDeadEnd(true);
                            var cells = cellsToGo(currentCell, currentDirection);
                            for (var k : cells.keySet()) {
                                currentDirection = k;
                                currentCell = cells.get(k);
                            }
                            resultPath.remove(resultPath.get(resultPath.size() - 1));
                        }
                    }
                }
                default -> { }
            }
        }
        return resultPath;
    }


    public TremauxSolver(Maze maze) {
        this.resultPath = new ArrayList<>();
        this.maze = maze;
    }


    @Override
    public List<Cell.Location> solve(Maze maze, Cell.Location start, Cell.Location end) {
        return run();
    }
}
