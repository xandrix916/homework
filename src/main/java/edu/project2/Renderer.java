package edu.project2;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.EnumMap;

@Slf4j
public class Renderer {
    private final Maze maze;

    private final int cellHorSize;

    private final int cellVertSize;

    private RenderIcons[][] args;
    private final EnumMap<RenderIcons, String> iconMap = new EnumMap<>(RenderIcons.class) {{
        put(RenderIcons.VERTEX, "[]");
        put(RenderIcons.VERT_PART_EDGE, "||");
        put(RenderIcons.VERT_HOLLOW, "  ");
        put(RenderIcons.VERT_UPPER_GATE, "⊔⊔");
        put(RenderIcons.VERT_LOWER_GATE, "⊓⊓");
    }};

    private String[] templateString() {
        String[] strings = new String[(cellVertSize + 1) * maze.getMazeHeight() + 1];
        Arrays.fill(strings, "%s".repeat(2 * maze.getMazeWidth() + 1) + "\n");
        return strings;
    }

    /*public Renderer(Maze maze) {
        this.maze = maze;
        this.cellHorSize = 10;
        this.cellVertSize = 4;

    }*/

    public Renderer(Maze maze, int horSize, int vertSize) {
        this.maze = maze;
        this.cellHorSize = horSize;
        this.cellVertSize = vertSize;
    }

    private String mazeToString() {
        Vertex currentVertex;
        Edge currentEdge;
        String[] finalStringArray = templateString();
        StringBuilder resultString = new StringBuilder();
        args = new RenderIcons[(cellVertSize + 1) * maze.getMazeHeight() + 1][2 * maze.getMazeWidth() + 1];
        iconMap.put(RenderIcons.HOR_EDGE, "=".repeat(cellHorSize));
        iconMap.put(RenderIcons.HOR_HOLLOW, " ".repeat(cellHorSize));
        iconMap.put(RenderIcons.HOR_GATE, String.format("%s%s%s", "==|", " ".repeat(cellHorSize - 6), "|=="));


        for (int i = 0; i <= maze.getMazeHeight(); i++) { //NB: indexes are for maze vertex array. For args I use 2*col,
            for (int j = 0; j <= maze.getMazeWidth(); j++) {  // 5*row
                currentVertex = maze.getVertex(i, j);
                args[(cellVertSize + 1) * i][2 * j] = RenderIcons.VERTEX;

                if (currentVertex != null) {
                    currentEdge = currentVertex.getEdgeBySide(Cell.WallSide.SOUTH);
                    if (currentEdge != null) {
                        for (int k = 1; k <= cellVertSize; k++) {
                            args[(cellVertSize + 1) * i + k][2 * j] = currentEdge.isActive() ? RenderIcons.VERT_PART_EDGE : RenderIcons.VERT_HOLLOW;
                        }
                    }

                    currentEdge = currentVertex.getEdgeBySide(Cell.WallSide.EAST);
                    if (currentEdge != null) {
                        args[(cellVertSize + 1) * i][2 * j + 1] = currentEdge.isActive() ? RenderIcons.HOR_EDGE : RenderIcons.HOR_HOLLOW;
                    }
                }
                if (j != 0 && i != maze.getMazeHeight()) {
                    for (int k = 1; k <= cellVertSize; k++) {
                        args[(cellVertSize + 1) * i + k][2 * j - 1] = RenderIcons.HOR_HOLLOW;
                    }
                }
            }
        }

        addExits();
        for (int i = 0; i < finalStringArray.length; i++) {
            String[] appliedArgs = new String[2 * maze.getMazeWidth() + 1];
            for (int j = 0; j < appliedArgs.length; j++) {
                appliedArgs[j] = iconMap.get(args[i][j]);
            }
            resultString.append(String.format(finalStringArray[i], (Object[]) appliedArgs));
        }
        return resultString.toString();
    }

    private Vertex[] allPossibleVertexForCell(Cell.Location location) {
        return new Vertex[]{maze.getVertex(location.row(), location.col()),
            maze.getVertex(location.row(), (location.col() + 1) == maze.getMazeWidth() ? (location.col() + 1)
                : (location.col() - 1)),
            maze.getVertex((location.row() + 1 == maze.getMazeHeight() ? location.row() + 1 : location.row() - 1),
                location.col()),
            maze.getVertex((location.row() + 1 == maze.getMazeHeight() ? location.row() + 1 : location.row() - 1),
                (location.col() + 1) == maze.getMazeWidth() ? (location.col() + 1)
                    : (location.col() - 1))};
        }

    private Vertex[] deleteNulls(Vertex[] vertices) {
        int countNull = 0;
        int counter = 0;
        for (var i: vertices) {
            if (i == null) {
                countNull++;
            }
        }
        if (countNull != 0) {
            Vertex[] newVertices = new Vertex[vertices.length - countNull];
            for (var i: vertices) {
                if (i != null) {
                    newVertices[counter] = i;
                    counter++;
                }
            }
            return newVertices;
        }
        return vertices;
    }

    private Vertex[] twoVerticesForEntrance(Cell.WallSide wallSide, Cell.Location location) {
        Vertex[] possibleVertices = allPossibleVertexForCell(location);
        possibleVertices = deleteNulls(possibleVertices);
        if (possibleVertices.length < 4) {
            if (possibleVertices.length == 2) {
                return possibleVertices;
            } else {
                return switch (wallSide) {
                    case NORTH, SOUTH -> new Vertex[] {possibleVertices[1], possibleVertices[2]};
                    case WEST, EAST -> new Vertex[] {possibleVertices[0], possibleVertices[1]};
                };
            }
        } else {
            return switch (wallSide) {
                case NORTH -> new Vertex[] {possibleVertices[0], possibleVertices[1]};
                case SOUTH -> new Vertex[] {possibleVertices[2], possibleVertices[3]};
                case WEST -> new Vertex[] {possibleVertices[0], possibleVertices[2]};
                case EAST -> new Vertex[] {possibleVertices[1], possibleVertices[3]};
            };
        }
    }

    private int[] gateAddress(Cell.WallSide wallSide, Vertex first, Vertex second) {
        try {
            return switch (wallSide) {
                case NORTH -> new int[]{0, first.getVertexCol() + second.getVertexCol()};
                case SOUTH -> new int[]{(cellVertSize + 1) * (maze.getMazeHeight()),
                    first.getVertexCol() + second.getVertexCol()};
                case WEST -> new int[]{(cellVertSize + 1) / 2 * (first.getVertexRow() + second.getVertexRow()), 0};
                case EAST -> new int[]{(cellVertSize + 1) / 2 * (first.getVertexRow() + second.getVertexRow()),
                    2 * maze.getMazeWidth()};
            };
        } catch (NullPointerException exception) {
            log.error(exception.getMessage());
        }

        return new int[0];
    }

    private void changeArgs(Cell.WallSide wallSide, int[] address) {
        switch (wallSide) {
            case NORTH, SOUTH -> args[address[0]][address[1]] = RenderIcons.HOR_GATE;
            case EAST, WEST -> args[address[0]][address[1]] = RenderIcons.VERT_HOLLOW;
        }
    }

    public void addExits() {
        Cell.Location firstLocation = maze.getStart();
        Cell.Location secondLocation = maze.getEnd();

        Vertex entranceFirst = twoVerticesForEntrance(maze.getStartSide(), firstLocation)[0];
        Vertex entranceSecond = twoVerticesForEntrance(maze.getStartSide(), firstLocation)[1];
        Vertex exitFirst = twoVerticesForEntrance(maze.getEndSide(), secondLocation)[0];
        Vertex exitSecond = twoVerticesForEntrance(maze.getEndSide(), secondLocation)[1];

        int[] startAddress = gateAddress(maze.getStartSide(), entranceFirst, entranceSecond);
        int[] exitAddress = gateAddress(maze.getEndSide(), exitFirst, exitSecond);

        changeArgs(maze.getStartSide(), startAddress);
        changeArgs(maze.getEndSide(), exitAddress);
    }

    public void render() {
        log.info(Arrays.toString(maze.getNumbersArray()) + "\n" + mazeToString());
    }
}
