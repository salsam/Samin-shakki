package chess.pieces;

import chess.logic.board.Player;

/**
 *
 * @author samisalo
 */
public class Pawn extends Piece {

    private boolean movedTwoSquaresLastTurn;
    private boolean hasBeenMoved;

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
