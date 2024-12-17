package com.project.service;

public abstract class IBoardObject {
    protected int startRow;
    protected int startCol;
    protected int endRow;
    protected int endCol;

    public IBoardObject(int startRow, int startCol, int endRow, int endCol) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
    }

    public abstract boolean isThisBoardObjectPosition(int row, int col);

    public abstract int[] getTargetPosition();

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }
}
