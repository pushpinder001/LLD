package service;

import dto.Position;
import model.Board;
import model.Player;
import model.peices.King;
import model.peices.Piece;

import java.util.List;

public class ChessGame {
    private Board board;
    private List<Player> players;

    private int turn;

    private Player winner;

    public ChessGame() {
        this.players = List.of(new Player(ChessGamePieceColor.WHITE.ordinal()), new Player(ChessGamePieceColor.BLACK.ordinal()));
        this.board = new Board();
        this.turn = 0;
        this.winner = null;
    }

    private boolean checkForWin(Piece piece, Piece targetPosPiece) {
        if(targetPosPiece != null && targetPosPiece instanceof King && targetPosPiece.getColor() != piece.getColor()) {
            winner = this.players.get(piece.getColor().ordinal());
            System.out.printf("Player %d has won", this.winner.getId());
            return true;
        }

        return false;
    }

    private Piece getPieceForPosition(Position position) {
        Piece piece = this.board.getPiece(position.row(), position.col());
        if(piece == null) {
            throw new RuntimeException("No valid piece for the given position");
        }

        return piece;
    }

    private boolean validate() {
        if(this.winner != null) {
            throw new RuntimeException(String.format("Player %d has won", this.winner.getId()));
        }
        return true;
    }
    public List<Position> nextMoves(int playerId, Position curPos) {
        validate();
        if(turn != playerId ) {
            throw new RuntimeException("Cannot check move without turn");
        }

        Piece piece = getPieceForPosition(curPos);
        if(turn != piece.getColor().ordinal() ) {
            throw new RuntimeException("Cannot get moves of opposite player");
        }
        return piece.nextMoves();
    }

    public boolean move(int playerId, Position curPos, Position targetPos) {
        validate();
        Piece targetPosPiece = board.getCell(targetPos.row(), targetPos.col()).getPiece();

        //Logic for winning
        if(turn != playerId ) {
            throw new RuntimeException("Cannot move without turn");
        }

        Piece piece = getPieceForPosition(curPos);
        piece.move(targetPos.row(), targetPos.col());

        if(!checkForWin(piece, targetPosPiece)) {
            turn = 1 - turn;
        }

        return true;
    }

    public void printBoard() {
        System.out.println(board.toString());
    }
}
