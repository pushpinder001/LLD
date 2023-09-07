package model.peices;

import dto.Position;
import model.Board;
import service.ChessGamePieceColor;

import java.util.List;
import java.util.Optional;

public abstract class Piece {
    public enum Type {
        PAWN, ROOK, KNIGHT, BISHOP, KING, QUEEN;
    }
    protected ChessGamePieceColor color;
    protected int curRow;

    protected int curCol;

    protected Board board;

    private Type type;

    protected boolean hasOpponentPiece(int row, int col) {
        if(this.board.hasPiece(row, col) && board.getPiece(row, col).color != color) {
            return true;
        }
        return false;
    }

    public Piece(ChessGamePieceColor color, int curRow, int curCol, Board board, Type type) {
        this.color = color;
        this.curRow = curRow;
        this.curCol = curCol;
        this.board = board;
        this.type = type;
    }

    protected boolean validateMove(int row, int col) {
        List<Position> possibleMoves = nextMoves();
        Optional<Position> pos = possibleMoves.stream()
                .filter(position -> position.row()==row && position.col()==col).findFirst();
        if(pos.isEmpty()) {
            throw new RuntimeException("Invalid move");
        }
        return true;
    }

    public abstract List<Position> nextMoves();

    public boolean move(int row, int col) {
        if(Board.validPosition(row, col) && validateMove(row, col)) {
            if(this.board.hasPiece(row, col)) this.board.removePiece(row, col);

            this.board.addPiece(this, row, col);
            this.board.removePiece(curRow, curCol);
            curRow = row;
            curCol = col;
            return true;
        }
        return false;
    }

    public ChessGamePieceColor getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }
}
