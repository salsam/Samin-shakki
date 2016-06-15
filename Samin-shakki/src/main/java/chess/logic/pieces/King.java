package chess.logic.pieces;

import chess.logic.board.Player;

/**
 *
 * This class corresponds physical king-piece thus only containing knowledge of
 * it's place and whether or not is has been moved. This class also offers a
 * method to clone this king.
 *
 * @author sami
 */
public class King extends Piece {

    private boolean hasBeenMoved;

    /**
     * Creates a new King-object in given location with given owner.
     *
     * @param column column of the square this king will be placed on
     * @param row row of the square this king will be placed on
     * @param player owner of this king
     */
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

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        King king = (King) obj;
        return hasBeenMoved == king.getHasBeenMoved();
    }

}
