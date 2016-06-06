package chess.logic.board;

import chess.logic.pieces.Piece;

/**
 * Square class is responsible for keeping track of its location and possible
 * piece situated on it.
 *
 * @author samisalo
 */
public class Square {

    private int column;
    private int row;
    private Piece piece;

    public Square(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getcolumn() {
        return column;
    }

    public int getrow() {
        return row;
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

        if (this.column != square.getcolumn()) {
            return false;
        }

        if (this.row != square.getrow()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.column;
        hash = 47 * hash + this.row;
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
        return "(" + column + "," + row + ")";
    }

    /**
     * Returns a field to field copy of this square.
     *
     * @return a deep copy of this square
     */
    @Override
    public Square clone() {
        Square clone = new Square(this.column, this.row);

        if (containsAPiece()) {
            clone.setPiece(this.piece.clone(clone));
        }

        return clone;
    }

}
