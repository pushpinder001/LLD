package model;

import model.peices.Piece;

public class Cell {
    private Piece piece;

    public boolean addPiece(Piece piece) {
        if(this.piece != null) {
            throw new RuntimeException("Piece cannot be added to cell as there is already a piece");
        }
        this.piece = piece;
        return true;
    }

    public Piece removePiece() {
        if(piece == null) {
            throw new RuntimeException("Cannot remove piece as there is no piece present in the cell");
        }
        Piece p = this.piece;
        this.piece = null;

        return p;
    }

    public boolean hasPiece() {
        return this.piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        if(piece == null) {
            return ".";
        }
        return piece.getColor().getPieceTypeToUniCode().get(piece.getType()).toString();
    }
}
