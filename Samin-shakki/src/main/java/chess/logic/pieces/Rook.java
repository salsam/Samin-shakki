package chess.logic.pieces;

import chess.logic.board.Player;

/**
 *
 * This class corresponds physical rook-piece thus only containing knowledge of
 * it's place and whether or not it has been moved so far. This class gives
 * methods to access that data and also to clone this rook.
 *
 * @author sami
 */
public class Rook extends Piece {

    private boolean hasBeenMoved;

    /**
     * Creates a new Rook-class object with given location and owner.
     *
     * @param column column of the square this rook will be placed on
     * @param row row of the square this rook will be placed on
     * @param player owner of this rook
     */
    public Rook(int column, int row, Player player) {
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
        Rook clone = new Rook(column, row, owner);
        clone.setHasBeenMoved(hasBeenMoved);
        return clone;
    }

}
