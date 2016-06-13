/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template column, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.logic.piecemovers;

import chess.logic.board.ChessBoard;
import java.util.Set;
import chess.logic.board.Square;
import chess.logic.pieces.Piece;

/**
 *
 * @author sami
 */
public class KnightMover extends PieceMover {

    public KnightMover() {
    }

    /**
     * Return a list containing all squares that this knight threatens.
     *
     * @param board board where this knight moves
     * @return list containing all squares this knight threatens
     */
    @Override
    public Set<Square> threatenedSquares(Piece piece, ChessBoard board) {
        int[] rowChange = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] columnChange = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

        return possibilities(board.getSquare(piece.getColumn(), piece.getRow()), columnChange, rowChange, board);
    }
}
