/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template column, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.logic.pieces;

import chess.gui.io.ImageLoader;
import chess.logic.board.ChessBoardLogic;
import java.util.Set;
import chess.logic.board.Player;
import chess.logic.board.Square;
import java.util.HashSet;

/**
 *
 * @author sami
 */
public class Queen extends Piece {

    public Queen(Square square, Player owner) {
        super(square, owner);

        if (owner == Player.BLACK) {
            this.picture = ImageLoader.getImage("blackQueen1.png");
        } else {
            this.picture = ImageLoader.getImage("whiteQueen1.png");
        }
    }

    /**
     * Returns a field to field copy of this piece.
     *
     * @param location location where the clone will be placed
     * @return deep copy of this queen
     */
    @Override
    public Piece clone(Square location) {
        return new Queen(location, this.owner);
    }

    /**
     * Return a list containing all squares that this queen threatens.
     *
     * @param board board on which this queen moves
     * @return list containing all squares this queen threatens
     */
    @Override
    public Set<Square> threatenedSquares(ChessBoardLogic board) {
        Set<Square> possibilities = new HashSet<>();
        addDiagonalPossibilities(location, board, possibilities);
        addHorizontalPossibilities(location, board, possibilities);
        addVerticalPossibilities(location, board, possibilities);

        return possibilities;
    }
}
