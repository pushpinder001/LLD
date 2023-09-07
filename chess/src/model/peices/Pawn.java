package model.peices;

import dto.Position;
import model.Board;
import service.ChessGame;
import service.ChessGamePieceColor;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    private boolean hasMoved;
    public Pawn(ChessGamePieceColor color, int curRow, int curCol, Board board) {
        super(color, curRow, curCol, board, Type.PAWN);
    }

    @Override
    public List<Position> nextMoves() {
        List<Position> nextMoves = new ArrayList<>();
        int dir = color== ChessGamePieceColor.BLACK? 1: -1;

        if(Board.validPosition(curRow+dir, curCol) && !this.board.hasPiece(curRow+dir, curCol)) {
            nextMoves.add(new Position(curRow+dir,  curCol));
            if(!hasMoved && Board.validPosition(curRow+2*dir, curCol) && !this.board.hasPiece(curRow+2*dir, curCol)) {
                nextMoves.add(new Position(curRow+2*dir, curCol));
            }
        }

        if(Board.validPosition(curRow+dir, curCol-1) && hasOpponentPiece(curRow+dir, curCol-1)) {
            nextMoves.add(new Position(curRow+dir, curCol-1));
        }

        if(Board.validPosition(curRow+dir, curCol+1) && hasOpponentPiece(curRow+dir, curCol+1)) {
            nextMoves.add(new Position(curRow+dir, curCol+1));
        }
        return nextMoves;
    }

    @Override
    public boolean move(int row, int col) {
        if(Board.validPosition(row, col) && validateMove(row, col)) {
            if(this.board.hasPiece(row, col)) this.board.removePiece(row, col);

            this.board.addPiece(this, row, col);
            this.board.removePiece(curRow, curCol);
            curRow = row;
            curCol = col;

            if(!hasMoved) hasMoved=true;
            if(row ==0 || row == Board.getROWS()-1) {
                this.board.addPiece(new Queen(color, row, col, board),row, col);
            }
            return true;
        }

        return false;
    }
}
