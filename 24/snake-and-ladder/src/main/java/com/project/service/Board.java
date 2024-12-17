package com.project.service;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int rows;
    private final int cols;
    int[][] grid; //OCP non compliant as we are assuming board to be square
    List<IBoardObject> listBoardObjects;
    private int startRow = 0;  //OCP non compliant
    private int startCol = 0;  //OCP non compliant

    public Board(int rows, int cols, List<IBoardObject> listBoardObjects) {
        this.rows = rows;
        this.cols = cols;
        this.listBoardObjects = new ArrayList<>(listBoardObjects);

        if(!isBoardValid()) {
            //throw exception
            throw new RuntimeException("Invalid boardObjects");
        }

    }

    //TODO:: validate board no two board object should have same startPosition
    private boolean isBoardValid() {
        return true;
    }


    //For winning case
    public boolean isThisEndOfBoard(@NonNull int[] curPos) {
        if((curPos[0] == (rows -1)) && (curPos[1] == (cols -1))) return true;
        return false;
    }

    public int[] getTargetBoardPositionForMove(int[] curPos, int noOfMoves) {
        int pos = curPos[0]*cols + curPos[1];
        int newPos = pos + noOfMoves;

        if(newPos >= rows*cols) {
            return curPos;
        }

        int newRow = newPos/cols;
        int newCol = newPos%cols;

        for(var boardObj : listBoardObjects) {
            if(boardObj.isThisBoardObjectPosition(newRow, newCol)) {
                return boardObj.getTargetPosition();
            }
        }

        return new int[]{newRow, newCol};
    }


    public int[] getStartPosition() {
        return new int[]{startRow, startCol};
    }
}
