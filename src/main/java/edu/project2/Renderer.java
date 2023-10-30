package edu.project2;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.EnumMap;

@Slf4j
public class Renderer {
    private final Maze maze;
    private final EnumMap<RenderIcons, String> iconMap = new EnumMap<>(RenderIcons.class) {{
        put(RenderIcons.VERTEX, "[]");
        put(RenderIcons.VERT_PART_EDGE, "||");
        put(RenderIcons.HOR_EDGE, "==========");
        put(RenderIcons.HOR_HOLLOW, " ".repeat(10));
        put(RenderIcons.VERT_HOLLOW, " ");
    }};

    private String[] templateString() {
        String[] strings = new String[5 * maze.getMazeHeight() + 1];
        Arrays.fill(strings, "%s".repeat(2 * maze.getMazeWidth() + 1) + "\n");
        return strings;
    }

    public Renderer(Maze maze) {
        this.maze = maze;
    }

    private String mazeToString() {
        Vertex currentVertex;
        Edge currentEdge;
        String[] finalStringArray = templateString();
        StringBuilder resultString = new StringBuilder();
        RenderIcons[][] args = new RenderIcons[5 * maze.getMazeHeight() + 1][2 * maze.getMazeWidth() + 1];

        for (int i = 0; i <= maze.getMazeHeight(); i++) { //NB: indexes are for maze vertex array. For args I use 2*col,
            for (int j = 0; j <= maze.getMazeWidth(); j++) {  // 5*row
                currentVertex = maze.getVertex(i, j);
                args[5 * i][2 * j] = RenderIcons.VERTEX;

                if (currentVertex != null) {
                    currentEdge = currentVertex.getEdgeBySide(Cell.WallSide.SOUTH);
                    if (currentEdge != null) {
                        for (int k = 1; k <= 4; k++) {
                            args[5 * i + k][2 * j] = currentEdge.isActive() ? RenderIcons.VERT_PART_EDGE : RenderIcons.VERT_HOLLOW;
                        }
                    }

                    currentEdge = currentVertex.getEdgeBySide(Cell.WallSide.EAST);
                    if (currentEdge != null) {
                        args[5 * i][2 * j + 1] = currentEdge.isActive() ? RenderIcons.HOR_EDGE : RenderIcons.HOR_HOLLOW;
                    }
                }
                if (j != 0 && i != maze.getMazeHeight()) {
                    for (int k = 1; k <= 4; k++) {
                        args[5 * i + k][2 * j - 1] = RenderIcons.HOR_HOLLOW;
                    }
                }
            }
        }

        for (int i = 0; i < finalStringArray.length; i++) {
            String[] appliedArgs = new String[2 * maze.getMazeWidth() + 1];
            for (int j = 0; j < appliedArgs.length; j++) {
                appliedArgs[j] = iconMap.get(args[i][j]);
            }
            resultString.append(String.format(finalStringArray[i], (Object[]) appliedArgs));
        }
        return resultString.toString();
    }

    public void render() {
        log.info("\n" + mazeToString());
    }
}
