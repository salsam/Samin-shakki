package logic.pieces;

import java.util.List;
import logic.assistance.Player;
import logic.assistance.Square;

public abstract class Piece {

    protected Square location;
    protected Player owner;

    public Piece(int x, int y, Player owner) {
        this.location = new Square(x, y);
        this.owner = owner;
    }

    abstract public List<Square> possibleMoves(Square[][] board);

    public void move(Square target) {
        location.setPiece(null);
        target.setPiece(this);

        location = target;
    }

    public Player getOwner() {
        return this.owner;
    }

    public Square getLocation() {
        return this.location;
    }

    public boolean legalToMoveTo(Square target) {

        if (target.getColumn() < 0 || target.getColumn() > 7) {
            return false;
        }

        if (target.getRow() < 0 || target.getColumn() > 7) {
            return false;
        }

        if (target.getPiece() == null) {
            return true;
        }

        return owner != target.getPiece().owner;
    }
}
