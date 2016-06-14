package chess.logic.pieces;

import chess.logic.board.Player;

/**
 * This class corresponds physical bishop-piece thus only containing knowledge
 * of it's place and a method to copy it.
 *
 * @author sami
 */
public class Bishop extends Piece {

    /**
     * Creates a new Bishop-object with give owner and location.
     *
     * @param column column this bishop will be placed on
     * @param row row this bishop will be placed on
     * @param owner owner of this bishop
     */
    public Bishop(int column, int row, Player owner) {
        super(column, row, owner);
    }

    @Override
    public Piece clone() {
        return new Bishop(column, row, owner);
    }

}
