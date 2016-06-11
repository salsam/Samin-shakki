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
import java.util.HashSet;

/**
 *
 * @author sami
 */
public class Rook extends Piece {

    public Rook(Square square, Player owner) {
        super(square, owner);

        if (owner == Player.BLACK) {
            this.picture = ImageLoader.getImage("blackRook1.png");
        } else {
            this.picture = ImageLoader.getImage("whiteRook1.png");
        }
    }

    /**
     * Returns a field to field copy of this piece.
     *
     * @param location location where the clone will be placed
     * @return deep copy of this rook
     */
    @Override
    public Piece clone(Square location) {
        return new Rook(location, this.owner);
    }

    /**
     * Return a list containing all squares that this rook threatens.
     *
     * @param board board on which this rook moves
     * @return list containing all squares this rook threatens
     */
    @Override
    public Set<Square> threatenedSquares(ChessBoard board) {
        Set<Square> possibilities = new HashSet<>();
        addHorizontalPossibilities(location, board, possibilities);
        addVerticalPossibilities(location, board, possibilities);

        return possibilities;
    }
}
