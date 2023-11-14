package edu.project2;

import java.util.List;

public interface Solver {
    List<Cell.Location> solve(Maze maze, Cell.Location start, Cell.Location end);
}
