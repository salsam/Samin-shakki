package chess.pieces;

import chess.logic.board.Player;

/**
 *
 * @author samisalo
 */
public class Bishop extends Piece {

    public Bishop(int column, int row, Player owner) {
        super(column, row, owner);
    }

    @Override
    public Piece clone() {
        return new Bishop(column, row, owner);
    }

}
