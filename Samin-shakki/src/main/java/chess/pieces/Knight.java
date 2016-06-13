package chess.pieces;

import chess.logic.board.Player;

/**
 *
 * @author samisalo
 */
public class Knight extends Piece {

    public Knight(int column, int row, Player player) {
        super(column, row, player);
    }

    @Override
    public Piece clone() {
        return new Knight(column, row, owner);
    }

}
