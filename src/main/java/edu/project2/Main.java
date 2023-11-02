package edu.project2;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        //Kruskal kruskal = new Kruskal();
        //Kruskal kruskal = new Kruskal(new int[]{8, 5, 10, 9, 0, 4, 1, 7, 11, 6, 3, 2});
        Kruskal kruskal = new Kruskal(new int[]{11, 7, 1, 4, 3, 8, 0, 10, 9, 2, 6, 5});
        Maze maze = kruskal.generate(3, 3);
        //maze.pickRandomEntranceExit();
        maze.render();

    }
}
