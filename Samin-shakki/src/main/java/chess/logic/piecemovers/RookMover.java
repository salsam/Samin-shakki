/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template column, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.logic.piecemovers;

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
public class RookMover extends PieceMover {

    private boolean hasBeenMoved;

    public RookMover(Square square, Player owner) {
        super(square, owner);
        hasBeenMoved = false;

        if (owner == Player.BLACK) {
            this.picture = ImageLoader.getImage("blackRook1.png");
        } else {
            this.picture = ImageLoader.getImage("whiteRook1.png");
        }
    }

    public boolean getHasBeenMoved() {
        return hasBeenMoved;
    }

    /**
     * Returns a field to field copy of this piece.
     *
     * @param location location where the clone will be placed
     * @return deep copy of this rook
     */
    @Override
    public PieceMover clone(Square location) {
        return new RookMover(location, this.owner);
    }

    /**
     * This method moves rook on the board and saves true to field hasBeenMoved.
     *
     * @see chess.logic.pieces.Piece.move()
     * @param target square this rook is moving to.
     * @param board board on which movement happens.
     */
    @Override
    public void move(Square target, ChessBoard board) {
        hasBeenMoved = true;
        super.move(target, board);
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
