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
import static chess.logic.board.Player.getOpponent;
import chess.logic.board.Square;
import java.util.HashSet;

/**
 *
 * @author sami
 */
public class King extends Piece {

    public King(Square square, Player owner) {
        super(square, owner);
        
        if (owner == Player.BLACK) {
            picture = ImageLoader.getImage("blackKing1.png");
        } else {
            picture = ImageLoader.getImage("whiteKing1.png");
        }
    }

    /**
     * Returns a field to field copy of this piece.
     *
     * @param location location where the clone will be placed
     * @return deep copy of this king
     */
    @Override
    public Piece clone(Square location) {
        return new King(location, this.owner);
    }

    /**
     * Return a list containing all squares that this king threatens.
     *
     * @param board board where this king moves
     * @return list containing all squares this king threatens
     */
    @Override
    public Set<Square> threatenedSquares(ChessBoard board) {
        int[] columnChange = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        int[] rowChange = new int[]{1, 1, 1, 0, 0, -1, -1, -1};

        return possibilities(columnChange, rowChange, board);
    }

    /**
     * Returns a list containing all squares this king can legally move to. That
     * means all neighbor squares of king's location that aren't threatened
     * by opponent or contain player's own piece.
     *
     * @param board chessboard on which movement happens
     * @return a list containing all squares this king can legally move to.
     */
    @Override
    public Set<Square> possibleMoves(ChessBoard board) {
        Set<Square> moves = new HashSet<>();

        threatenedSquares(board).stream()
                .filter((target) -> (legalToMoveTo(target, board) && !isThreatenedByOpponent(target, board)))
                .forEach((target) -> {
                    moves.add(target);
                });

        return moves;
    }

    private boolean isThreatenedByOpponent(Square target, ChessBoard board) {
        return board.threatenedSquares(getOpponent(owner)).contains(target);
    }

}
