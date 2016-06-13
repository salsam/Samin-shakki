/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template column, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.logic.piecemovers;

import chess.logic.board.ChessBoard;
import java.util.Set;
import chess.logic.board.Player;
import chess.logic.board.Square;
import chess.pieces.Bishop;
import chess.pieces.Piece;
import java.util.HashSet;

/**
 * This class is responsible of movement calculation of bishops.
 *
 * @author sami
 */
public class BishopMover extends PieceMover {

    public BishopMover() {
    }

    /**
     * Return a list containing all squares that this bishop threatens.
     *
     * @param board board where this bishop moves
     * @return list containing all squares this bishop threatens
     */
    @Override
    public Set<Square> threatenedSquares(Piece bishop, ChessBoard board) {
        bishop = (Bishop) bishop;
        Set<Square> possibilities = new HashSet<>();
        addDiagonalPossibilities(board.getSquare(bishop.getColumn(), bishop.getRow()), board, possibilities);

        return possibilities;
    }

}
