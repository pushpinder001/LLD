package model;

import model.peices.*;
import service.ChessGamePieceColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private static int ROWS = 8;
    private static int COLS = 8;

    private Cell[][] cells;

    public Board() {
        this.cells = new Cell[ROWS][COLS];
        for(int i=0; i<ROWS; i++) {
            for(int j=0; j<COLS; j++) {
                this.cells[i][j] = new Cell();
            }
        }

        fillBoard();
    }

    public static boolean validPosition(int row, int col) {
        if(row>=0 && col>=0 && row<ROWS && col<COLS) {
            return true;
        }
        return false;
    }

    private void fillBoard() {
        //Row 0,1 for Black
        List.of(Piece.Type.ROOK, Piece.Type.KNIGHT, Piece.Type.BISHOP, Piece.Type.KING, Piece.Type.QUEEN).forEach(
                (e) -> {
                    addPiecesForRow(0, e, ChessGamePieceColor.BLACK, this);
                }
        );
        addPiecesForRow(1, Piece.Type.PAWN, ChessGamePieceColor.BLACK, this);

        //Row 7,8 for White
        List.of(Piece.Type.ROOK, Piece.Type.KNIGHT, Piece.Type.BISHOP, Piece.Type.KING, Piece.Type.QUEEN).forEach(
                (e) -> {
                    addPiecesForRow(ROWS-1, e, ChessGamePieceColor.WHITE, this);
                }
        );
        addPiecesForRow(ROWS-2, Piece.Type.PAWN, ChessGamePieceColor.WHITE, this);
    }

    private void addPiecesForRow(int row, Piece.Type type, ChessGamePieceColor color, Board board) {
        switch(type) {
            case PAWN ->  {
                for(int i=0; i<COLS; i++) {
                    addPiece(new Pawn(color, row, i, board), row, i);
                }
            }
            case ROOK -> {
                addPiece(new Rook(color, row, 0, board), row, 0);
                addPiece(new Rook(color, row, COLS-1, board), row, COLS-1);
            }
            case KNIGHT -> {
                addPiece(new Knight(color, row, 1, board), row, 1);
                addPiece(new Knight(color, row, COLS-2, board), row, COLS-2);
            }
            case BISHOP -> {
                addPiece(new Bishop(color, row, 2, board), row, 2);
                addPiece(new Bishop(color, row, COLS-3, board), row, COLS-3);
            }
            case KING -> {
                addPiece(new King(color, row, 3, board), row, 3);
            }
            case QUEEN -> {
                addPiece(new Queen(color, row, 4, board), row, 4);
            }
        }
    }

    public static int getROWS() {
        return ROWS;
    }

    public static int getCOLS() {
        return COLS;
    }

    public Cell getCell(int row, int col) {
        if(!Board.validPosition(row, col)) {
            throw new RuntimeException("row or col is invalid");
        }

        return this.cells[row][col];
    }

    public boolean hasPiece(int row, int col) {
        if(!Board.validPosition(row, col)) {
            throw new RuntimeException("row or col is invalid");
        }

        return this.cells[row][col].hasPiece();
    }
    public Piece getPiece(int row, int col) {
        if(!hasPiece(row, col)) {
            throw new RuntimeException("No piece at the input position");
        }

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

        for(int i=0; i<=ROWS; i++) {
            for(int j=0; j<=COLS; j++) {
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
