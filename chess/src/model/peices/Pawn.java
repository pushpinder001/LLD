package model.peices;

import dto.Position;
import model.Board;
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
}
