package edu.project2;

public class CellTree {
    private CellTree parentTree;
    public static final String STRING_VIEW_TEMPLATE = "(%d, %d) --> ";
    private final Cell currentNodeValue;

    public StringBuilder stringView = new StringBuilder();

    public CellTree(Cell cell) {
        this.currentNodeValue = cell;
        this.parentTree = null;
        stringView.append(anotherNode(cell.getLocation().row(), cell.getLocation().col()));
    }

    /*public CellTree(CellTree otherCellTree) {
        this.currentNodeValue = new Cell(otherCellTree.currentNodeValue);
        if (otherCellTree.parentTree == null) {
            this.parentTree = null;
        } else {
            CellTree otherRoot = otherCellTree.getRoot();
            CellTree root = new CellTree(otherRoot.currentNodeValue);
            while (root.childTree.currentNodeValue != this.currentNodeValue) {
                root.childTree = new CellTree(otherRoot.childTree.currentNodeValue);
                root.childTree.parentTree = root;
                otherRoot = otherRoot.childTree;
            }
            this.parentTree = root;
        }
        if (otherCellTree.childTree == null) {
            this.childTree = null;
        } else {
            CellTree otherTillEnd = otherCellTree.childTree;
            CellTree tillEnd = new CellTree(otherTillEnd.currentNodeValue);
            tillEnd pare
            while (otherTillEnd.childTree != null) {
                tillEnd.childTree = new CellTree(otherCellTree.childTree.currentNodeValue);
                tillEnd = t
            }
        }

    }*/

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

    public Cell getCurrentNode() {
        return currentNodeValue;
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
