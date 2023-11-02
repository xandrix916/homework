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

        int[] startIndex = getGateIndex(startCell, entranceSide);
        int[] endIndex = getGateIndex(endCell, exitSide);

        args[startIndex[0]][startIndex[1]] = getProperIcon(entranceSide);
        args[endIndex[0]][endIndex[1]] = getProperIcon(exitSide);
    }

    public void render() {
        log.info(Arrays.toString(maze.getNumbersArray()) + "\n" + mazeToString());
    }
}
