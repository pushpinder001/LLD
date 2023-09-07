package model.peices;

import dto.Position;
import model.Board;
import service.ChessGame;
import service.ChessGamePieceColor;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    public Bishop(ChessGamePieceColor color, int curRow, int curCol, Board board) {
        super(color, curRow, curCol, board, Type.BISHOP);
    }

    @Override
    public List<Position> nextMoves() {
        List<Position> moves = new ArrayList<>();
        int[][] dir = {{1,1}, {-1,-1}, {1,-1}, {-1,1}};

        for(int i=0; i<4; i++) {
            int row = curRow + dir[i][0];
            int col = curCol + dir[i][1];

            while(Board.validPosition(row, col)) {
                if(board.hasPiece(row, col)) {
                    if(hasOpponentPiece(row, col)) {
                        moves.add(new Position(row, col));
                    }
                    break;
                }
                moves.add(new Position(row, col));
                row += dir[i][0];
                col += dir[i][1];
            }
        }
        return moves;
    }
}
