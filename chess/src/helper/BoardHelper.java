package helper;

import model.Board;
import model.Cell;
import model.peices.*;
import service.ChessGamePieceColor;

import java.util.List;

public final class BoardHelper {

    private static void fillBoard(Board board) {
        //Row 0,1 for Black
        List.of(Piece.Type.ROOK, Piece.Type.KNIGHT, Piece.Type.BISHOP, Piece.Type.KING, Piece.Type.QUEEN).forEach(
                (e) -> {
                    addPiecesForRow(0, e, ChessGamePieceColor.BLACK, board);
                }
        );
        addPiecesForRow(1, Piece.Type.PAWN, ChessGamePieceColor.BLACK, board);

        //Row 7,8 for White
        List.of(Piece.Type.ROOK, Piece.Type.KNIGHT, Piece.Type.BISHOP, Piece.Type.KING, Piece.Type.QUEEN).forEach(
                (e) -> {
                    addPiecesForRow(Board.SIZE-1, e, ChessGamePieceColor.WHITE, board);
                }
        );
        addPiecesForRow(Board.SIZE-2, Piece.Type.PAWN, ChessGamePieceColor.WHITE, board);
    }

    private static void addPiecesForRow(int row, Piece.Type type, ChessGamePieceColor color, Board board) {
        switch(type) {
            case PAWN ->  {
                for(int i=0; i<Board.SIZE; i++) {
                    board.addPiece(new Pawn(color, row, i, board), row, i);
                }
            }
            case ROOK -> {
                board.addPiece(new Rook(color, row, 0, board), row, 0);
                board.addPiece(new Rook(color, row, Board.SIZE-1, board), row, Board.SIZE-1);
            }
            case KNIGHT -> {
                board.addPiece(new Knight(color, row, 1, board), row, 1);
                board.addPiece(new Knight(color, row, Board.SIZE-2, board), row, Board.SIZE-2);
            }
            case BISHOP -> {
                board.addPiece(new Bishop(color, row, 2, board), row, 2);
                board.addPiece(new Bishop(color, row, Board.SIZE-3, board), row, Board.SIZE-3);
            }
            case KING -> {
                board.addPiece(new King(color, row, 3, board), row, 3);
            }
            case QUEEN -> {
                board.addPiece(new Queen(color, row, 4, board), row, 4);
            }
        }
    }

    public static Board buildNewBoard() {
        Cell[][] cells = new Cell[Board.SIZE][Board.SIZE];
        Board board = new Board(cells);
        for(int i=0; i<Board.SIZE; i++) {
            for(int j=0; j<Board.SIZE; j++) {
                cells[i][j] = new Cell();
            }
        }
        fillBoard(board);

        return board;
    }
}
