package chess.pieces;

import java.util.List;
import chess.board.Player;
import chess.board.Square;

public abstract class Piece {

    protected Square location;
    protected Player owner;

    public Piece(int file, int rank, Player owner) {
        this.location = new Square(file, rank);
        this.owner = owner;
    }

    abstract public List<Square> possibleMoves(Square[][] board);

    public Player getOwner() {
        return this.owner;
    }

    public Square getLocation() {
        return this.location;
    }

    public boolean legalToMoveTo(Square target) {

        if (target.getFile() < 0 || target.getFile() > 7) {
            return false;
        }

        if (target.getRank() < 0 || target.getFile() > 7) {
            return false;
        }

        if (target.getPiece() == null) {
            return true;
        }

        return owner != target.getPiece().owner;
    }

    public void move(Square target) {
        location.setPiece(null);
        target.setPiece(this);

        location = target;
    }
}
