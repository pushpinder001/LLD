package service;

import model.peices.Piece;

import java.util.Map;

public enum ChessGamePieceColor {
    WHITE, BLACK;

    private Map<Piece.Type, Character> pieceTypeToUniCode;

    ChessGamePieceColor() {
        switch(this.name()) {
            case "WHITE" -> {
                pieceTypeToUniCode = Map.of(
                        Piece.Type.ROOK, '\u2656', Piece.Type.KNIGHT, '\u2658', Piece.Type.BISHOP, '\u2657',
                        Piece.Type.KING, '\u2654', Piece.Type.QUEEN, '\u2655', Piece.Type.PAWN, '\u2659'
                );
            }
            case "BLACK" -> {
                pieceTypeToUniCode = Map.of(
                        Piece.Type.ROOK, '\u265C', Piece.Type.KNIGHT, '\u265E', Piece.Type.BISHOP, '\u265D',
                        Piece.Type.KING, '\u265A', Piece.Type.QUEEN, '\u265B', Piece.Type.PAWN, '\u265F'
                );
            }
        }
    }

    public Map<Piece.Type, Character> getPieceTypeToUniCode() {
        return pieceTypeToUniCode;
    }
}
