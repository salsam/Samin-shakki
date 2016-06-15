package chess.logic.pieces;

import chess.logic.board.Player;

/**
 *
 * This class corresponds physical bishop-piece thus only containing knowledge
 * of it's place, whether or not is has moved yet and if it moved two squares
 * last turn. Class also offers methods to access this information and clone
 * this pawn.
 *
 * @author sami
 */
public class Pawn extends Piece {

    private boolean movedTwoSquaresLastTurn;
    private boolean hasBeenMoved;

    /**
     * Creates a new Pawn-class object with given location and owner.
     *
     * @param column column of the square this pawn is placed on
     * @param row row of the square this pawn is placed on
     * @param owner owner of this pawn
     */
    public Pawn(int column, int row, Player owner) {
        super(column, row, owner);
        movedTwoSquaresLastTurn = false;
        hasBeenMoved = false;
    }

    public boolean getHasBeenMoved() {
        return hasBeenMoved;
    }

    public void setHasBeenMoved(boolean hasBeenMoved) {
        this.hasBeenMoved = hasBeenMoved;
    }

    public boolean getMovedTwoSquaresLastTurn() {
        return movedTwoSquaresLastTurn;
    }

    public void setMovedTwoSquaresLastTurn(boolean movedTwoSquaresLastTurn) {
        this.movedTwoSquaresLastTurn = movedTwoSquaresLastTurn;
    }

    @Override
    public Piece clone() {
        Pawn clone = new Pawn(column, row, owner);
        clone.setMovedTwoSquaresLastTurn(movedTwoSquaresLastTurn);
        return clone;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        Pawn pawn = (Pawn) obj;

        if (hasBeenMoved != pawn.getHasBeenMoved()) {
            return false;
        }

        return movedTwoSquaresLastTurn == pawn.getMovedTwoSquaresLastTurn();
    }

    /**
     * Returns last row in the direction this pawn is moving towards.
     *
     * @return integer value of last row on board in direction of movement.
     */
    public int opposingEnd() {
        if (owner == Player.BLACK) {
            return 0;
        }
        return 7;
    }
}
