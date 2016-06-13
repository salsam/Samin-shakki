package chess.logic.pieces;

import chess.logic.board.Player;

/**
 *
 * @author samisalo
 */
public class King extends Piece {

    private boolean hasBeenMoved;

    public King(int column, int row, Player player) {
        super(column, row, player);
        this.hasBeenMoved = false;
    }

    public boolean getHasBeenMoved() {
        return hasBeenMoved;
    }

    public void setHasBeenMoved(boolean hasBeenMoved) {
        this.hasBeenMoved = hasBeenMoved;
    }

    @Override
    public Piece clone() {
        King clone = new King(column, row, owner);
        clone.setHasBeenMoved(hasBeenMoved);
        return clone;
    }

}
