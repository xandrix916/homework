package edu.project2;

import java.util.EnumMap;

public class Vertex {
    private final int vertexRow; //0..n
    private final int vertexCol; //0..m

    private final EnumMap<Cell.WallSide, Edge> edgeMap = new EnumMap<>(Cell.WallSide.class);

    public Vertex(int vertexRow, int vertexCol) {
        this.vertexRow = vertexRow;
        this.vertexCol = vertexCol;

        edgeMap.put(Cell.WallSide.NORTH, null);
        edgeMap.put(Cell.WallSide.SOUTH, null);
        edgeMap.put(Cell.WallSide.EAST, null);
        edgeMap.put(Cell.WallSide.WEST, null);
    }

    private boolean checkEdge(Cell.WallSide wallSide, Vertex[][] vertices) {
        Edge adjacentEdge = null;
        switch (wallSide) {
            case NORTH -> {
                if (vertexRow - 1 < 0) {
                    return true; //means there's nothing to change
                }
                adjacentEdge = vertices[vertexRow - 1][vertexCol].edgeMap.get(Cell.WallSide.SOUTH);
            }
            case SOUTH -> {
                if (vertexRow + 1 >= vertices.length) {
                    return true;
                }
                adjacentEdge = vertices[vertexRow + 1][vertexCol].edgeMap.get(Cell.WallSide.NORTH);
            }
            case WEST -> {
                if (vertexCol - 1 < 0) {
                    return true;
                }
                adjacentEdge = vertices[vertexRow][vertexCol - 1].edgeMap.get(Cell.WallSide.EAST);
            }
            case EAST -> {
                if (vertexCol + 1 >= vertices[0].length) {
                    return true;
                }
                adjacentEdge = vertices[vertexRow][vertexCol + 1].edgeMap.get(Cell.WallSide.WEST);
            }
        }
        if (adjacentEdge != null) {
            this.edgeMap.put(wallSide, adjacentEdge);
            return true;
        }
        return false;
    }



    public void updateEdges(Vertex[][] vertices) {
        for (var side: edgeMap.keySet()) {
            if (!checkEdge(side, vertices)) {
                Vertex secondVertex = switch (side) {
                    case NORTH -> vertices[vertexRow - 1][vertexCol];
                    case SOUTH -> vertices[vertexRow + 1][vertexCol];
                    case WEST -> vertices[vertexRow][vertexCol - 1];
                    case EAST -> vertices[vertexRow][vertexCol + 1];
                };
                this.edgeMap.put(side, new Edge(this, secondVertex, true,
                    false, false));
            }
        }
   }

    public record Edge(Vertex firstVertex, Vertex secondVertex, boolean isActive, boolean isVisited, boolean isRendered){}
}
