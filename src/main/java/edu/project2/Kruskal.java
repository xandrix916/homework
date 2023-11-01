package edu.project2;

import java.util.Collections;
import java.util.List;


public class Kruskal implements Generator {
    private Maze maze;
    private List<Edge> edges;

    private final int[] numberList;

    //private Map<Cell, CellTree> setMap = new HashMap<>();

    private CellTree[][] cellTrees;

    private void setNumbersArray() {
        int[] numbersArray = new int[edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            numbersArray[i] = edges.get(i).getNumber();
        }
        maze.setNumbersArray(numbersArray);
    }

    private void listSetArrange() {
        if (numberList == null) {
            Collections.shuffle(edges);
        } else {
            for (int j : numberList) {
                edges.add(edges.get(j));
            }
            edges = edges.subList(numberList.length, edges.size());
        }
        setNumbersArray();
        cellTrees = initTree();
    }

    private CellTree[][] initTree() {
        CellTree[][] trees = new CellTree[maze.getMazeHeight()][maze.getMazeWidth()];
        for (int i = 0; i < trees.length; i++) {
            for (int j = 0; j < trees[0].length; j++) {
                trees[i][j] = new CellTree(maze.getCellByCoordinates(i, j));
            }
        }
        return trees;
    }

    public Kruskal() {
        this.numberList = null;
    }

//    public Kruskal(int[] numberList) {
//        this.numberList = numberList;
//    }

    public Maze run() {
        for (var e: edges) {
            var connectedCells = e.getConnectedCells();
            Cell firstCell;
            Cell secondCell;
            if (connectedCells.get(Cell.WallSide.NORTH) != null) {
                firstCell = connectedCells.get(Cell.WallSide.NORTH);
                secondCell = connectedCells.get(Cell.WallSide.SOUTH);
            } else {
                firstCell = connectedCells.get(Cell.WallSide.WEST);
                secondCell = connectedCells.get(Cell.WallSide.EAST);
            }
            var firstTree = cellTrees[firstCell.getLocation().row()][firstCell.getLocation().col()];
            var secondTree = cellTrees[secondCell.getLocation().row()][secondCell.getLocation().col()];

            if (!firstTree.equals(secondTree)) {
                e.setActive(false);
                firstTree.connect(secondTree);
            }
        }
        return this.maze;
    }

    @Override
    public Maze generate(int height, int width) {
        this.maze = new Maze(height, width);
        this.edges = maze.getEdges();
        listSetArrange();
        return run();
    }


}
