package chess.board;

import chess.pieces.Piece;

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

        if (this.getFile() != square.getFile()) {
            return false;
        }

        if (this.getRank() != square.getRank()) {
            return false;
        }

        return true;
    }

}
