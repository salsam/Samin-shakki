package logic.assistance;

import logic.pieces.Piece;

public class Square {

    private int column;
    private int row;
    private Piece piece;

    public Square(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
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

        if (this.getColumn() != square.getColumn()) {
            return false;
        }

        if (this.getRow() != square.getRow()) {
            return false;
        }

        return true;
    }

}
