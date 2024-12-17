package com.project.service;

public class Snake extends IBoardObject {
    public Snake(int startRow, int startCol, int endRow, int endCol) {
        //Add validation
        super(startRow, startCol, endRow, endCol);
    }

    @Override
    public boolean isThisBoardObjectPosition(int row, int col) {
        if(row == endRow && col == endCol) {
            return true;
        }

        return false;
    }

    @Override
    public int[] getTargetPosition() {
        return new int[]{startRow, startCol};
    }
}
