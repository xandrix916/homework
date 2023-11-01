package edu.project2;

public class CellTree {
    private CellTree parentTree;
    public static final String STRING_VIEW_TEMPLATE = "(%d, %d) --> ";

    public StringBuilder stringView = new StringBuilder();

    public CellTree(Cell cell) {
        this.parentTree = null;
        stringView.append(anotherNode(cell.getLocation().row(), cell.getLocation().col()));
    }

    private String anotherNode(int row, int col) {
        return String.format(STRING_VIEW_TEMPLATE, row, col);
    }
    private void setParentTree(CellTree parentTree) {
        this.parentTree = parentTree;
    }

    public void connect(CellTree secondTree) {
        secondTree.getRoot().setParentTree(this);
        stringView.append(secondTree.getRoot().stringView);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        CellTree cellTree = (CellTree) o;
        return this.getRoot() == cellTree.getRoot();
    }

    private CellTree getRoot() {
        CellTree upToRoot = this;
        while (upToRoot.parentTree != null) {
            upToRoot = upToRoot.parentTree;
        }
        return upToRoot;
    }

    @Override
    public String toString() {
        return stringView.toString();
    }
}
