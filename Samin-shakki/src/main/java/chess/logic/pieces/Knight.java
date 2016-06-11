/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template column, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.logic.pieces;

import chess.gui.io.ImageLoader;
import chess.logic.board.ChessBoard;
import java.util.Set;
import chess.logic.board.Player;
import chess.logic.board.Square;

/**
 *
 * @author sami
 */
public class Knight extends Piece {

    public Knight(Square square, Player owner) {
        super(square, owner);

        if (owner == Player.BLACK) {
            this.picture = ImageLoader.getImage("blackKnight1.png");
        } else {
            this.picture = ImageLoader.getImage("whiteKnight1.png");
        }
    }

    /**
     * Returns a field to field copy of this piece.
     *
     * @param location location where the clone will be placed
     * @return deep copy of this knight
     */
    @Override
    public Piece clone(Square location) {
        return new Knight(location, this.owner);
    }

    /**
     * Return a list containing all squares that this knight threatens.
     *
     * @param board board where this knight moves
     * @return list containing all squares this knight threatens
     */
    @Override
    public Set<Square> threatenedSquares(ChessBoard board) {
        int[] rowChange = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] columnChange = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

        return possibilities(columnChange, rowChange, board);
    }
}
