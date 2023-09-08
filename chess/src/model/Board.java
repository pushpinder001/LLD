package model;

import model.peices.*;

public class Board {
    public final static int SIZE = 8;

    private Cell[][] cells;

    public Board(Cell[][] cells) {
        this.cells = cells;
    }

    public static boolean validPosition(int row, int col) {
        if(row>=0 && col>=0 && row<SIZE && col<SIZE) {
            return true;
        }
        return false;
    }

    public boolean hasPiece(int row, int col) {
        if(!Board.validPosition(row, col)) {
            throw new RuntimeException("row or col is invalid");
        }

        return this.cells[row][col].hasPiece();
    }
    public Piece getPiece(int row, int col) {
        return this.cells[row][col].getPiece();
    }

    public boolean addPiece(Piece piece, int row, int col) {
        if(!Board.validPosition(row, col)) {
            throw new RuntimeException("No piece at the input position");
        }

        return this.cells[row][col].addPiece(piece);
    }

    public Piece removePiece(int row, int col) {
        if(!Board.validPosition(row, col)) {
            throw new RuntimeException("No piece at the input position");
        }

        return this.cells[row][col].removePiece();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(".....Board....\n");

        for(int i=0; i<=SIZE; i++) {
            for(int j=0; j<=SIZE; j++) {
                if(i==0 && j==0) {
                    sb.append("  ");
                }else if(i==0) {
                    sb.append(String.format("%d ", j-1));
                } else if(j==0) {
                    sb.append(String.format("%d ", i-1));
                } else {
                    sb.append(String.format("%s ", cells[i-1][j-1].toString()));
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
