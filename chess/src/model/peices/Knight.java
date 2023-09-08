package model.peices;

import dto.Position;
import model.Board;
import service.ChessGamePieceColor;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    public Knight(ChessGamePieceColor color, int curRow, int curCol, Board board) {
        super(color, curRow, curCol, board, Type.KNIGHT);
    }

    @Override
    public List<Position> nextMoves() {
        int[][] dir = {{2,1}, {1,2}, {2,-1}, {-1,2}};
        List<Position> moves = new ArrayList<>();

        for(int i=0; i<dir.length; i++) {
            int row = curRow + dir[i][0];
            int col = curCol + dir[i][1];
            if(Board.validPosition(row, col) && (!board.hasPiece(row, col) || hasOpponentPiece(row, col))) {
                moves.add(new Position(row, col));
            }
        }

        return moves;
    }
}
