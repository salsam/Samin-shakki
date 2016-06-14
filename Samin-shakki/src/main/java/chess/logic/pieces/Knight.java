package chess.logic.pieces;

import chess.logic.board.Player;

/**
 *
 * This class corresponds physical knight-piece thus only containing knowledge
 * of it's place and a method to copy it.
 *
 * @author sami
 */
public class Knight extends Piece {

    /**
     * Creates a new Knight-class object with given location and owner.
     *
     * @param column column of the square this knight will be placed on
     * @param row row of the square this knight will be placed on
     * @param player owner of this knight
     */
    public Knight(int column, int row, Player player) {
        super(column, row, player);
    }

    @Override
    public Piece clone() {
        return new Knight(column, row, owner);
    }

}
