package chess.logic.pieces;

import chess.logic.board.Player;

/**
 *
 * This class corresponds physical queen-piece thus only containing knowledge of
 * it's place and a method to copy it.
 *
 * @author sami
 */
public class Queen extends Piece {

    /**
     * Creates a new Queen-class object with given location and owner.
     *
     * @param column column of the square this queen is placed on
     * @param row row of the square this queen is placed on
     * @param player owner of this queen
     */
    public Queen(int column, int row, Player player) {
        super(column, row, player);
    }

    @Override
    public Piece clone() {
        return new Queen(column, row, owner);
    }

}
