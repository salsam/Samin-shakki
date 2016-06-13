package chess.logic.pieces;

import chess.logic.board.Player;

/**
 *
 * @author samisalo
 */
public class Queen extends Piece {

    public Queen(int column, int row, Player player) {
        super(column, row, player);
    }

    @Override
    public Piece clone() {
        return new Queen(column, row, owner);
    }

}
