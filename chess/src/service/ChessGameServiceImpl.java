package service;

import dto.Position;
import model.Board;
import model.Player;
import model.peices.King;
import model.peices.Piece;

import java.util.List;

public class ChessGameServiceImpl implements ChessGameService{
    private Board board;
    private List<Player> players;

    private int turn;

    private Player winner;

    public ChessGameServiceImpl(Board board) {
        this.players = List.of(new Player(ChessGamePieceColor.WHITE.ordinal()), new Player(ChessGamePieceColor.BLACK.ordinal()));
        this.board = board;
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

    private boolean validate(int playerId) {
        if(this.winner != null) {
            throw new RuntimeException(String.format("Player %d has won", this.winner.getId()));
        }
        if(turn != playerId) {
            throw new RuntimeException("Cannot move without turn");
        }
        return true;
    }

    @Override
    public List<Position> nextMoves(int playerId, Position curPos) {
        validate(playerId);

        Piece piece = getPieceForPosition(curPos);
        if(turn != piece.getColor().ordinal() ) {
            throw new RuntimeException("Cannot get moves of opposite player");
        }
        return piece.nextMoves();
    }

    @Override
    public boolean move(int playerId, Position curPos, Position targetPos) {
        int targetRow = targetPos.row();
        int targetCol = targetPos.col();

        validate(playerId);
        Piece targetPosPiece = this.board.getPiece(targetRow, targetCol);
        Piece piece = getPieceForPosition(curPos);

        if(piece.validateMove(targetRow, targetCol)) {
            if(this.board.hasPiece(targetRow, targetCol)) this.board.removePiece(targetRow, targetCol);

            this.board.addPiece(piece, targetRow, targetCol);
            this.board.removePiece(curPos.row(), curPos.col());
            piece.setCurRow(targetRow);
            piece.setCurCol(targetCol);

            if(!checkForWin(piece, targetPosPiece)) {
                turn = 1 - turn;
            }
            return true;
        }
        return false;

    }

    @Override
    public void printBoard() {
        System.out.println(board.toString());
    }
}
