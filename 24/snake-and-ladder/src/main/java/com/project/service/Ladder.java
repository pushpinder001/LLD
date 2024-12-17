package com.project.service;

public class Ladder extends IBoardObject {
    public Ladder(int startRow, int startCol, int endRow, int endCol) {
        super(startRow, startCol, endRow, endCol);
    }

    @Override
    public boolean isThisBoardObjectPosition(int row, int col) {
        if(row == startRow && col == startCol) {
            return true;
        }
        return false;
    }

    @Override
    public int[] getTargetPosition() {
        return new int[]{endRow, endCol};
    }
}
