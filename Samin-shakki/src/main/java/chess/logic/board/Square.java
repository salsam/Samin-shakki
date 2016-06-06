package chess.logic.board;

import chess.logic.pieces.Piece;

/**
 * Square class is responsible for keeping track of its location and possible
 * piece situated on it.
 *
 * @author samisalo
 */
public class Square {

    private int file;
    private int rank;
    private Piece piece;

    public Square(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Square square = (Square) obj;

        if (this.file != square.getFile()) {
            return false;
        }

        if (this.rank != square.getRank()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.file;
        hash = 47 * hash + this.rank;
        return hash;
    }

    /**
     * Returns true if Square contains a piece that is field piece doesn't refer
     * to null.
     *
     * @return true if Square contains a piece
     */
    public boolean containsAPiece() {
        return piece != null;
    }

    @Override
    public String toString() {
        return "(" + file + "," + rank + ")";
    }

    /**
     * Returns a field to field copy of this square.
     *
     * @return a deep copy of this square
     */
    @Override
    public Square clone() {
        Square clone = new Square(this.file, this.rank);

        if (containsAPiece()) {
            clone.setPiece(this.piece.clone(clone));
        }

        return clone;
    }

}
