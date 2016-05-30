/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.pieces;

import chess.board.ChessBoard;
import java.util.List;
import chess.board.Player;
import static chess.board.Player.getOpponent;
import chess.board.Square;
import java.util.ArrayList;

/**
 *
 * @author sami
 */
public class King extends Piece {

    public King(Square square, Player owner) {
        super(square, owner);
    }

    @Override
    public List<Square> threatenedSquares(ChessBoard board) {
        int[] fileChange = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        int[] rankChange = new int[]{1, 1, 1, 0, 0, -1, -1, -1};

        return possibilities(fileChange, rankChange, board);
    }

    @Override
    public List<Square> possibleMoves(ChessBoard board) {
        List<Square> moves = new ArrayList<>();

        for (Square target : threatenedSquares(board)) {
            if (legalToMoveTo(target, board) && !isThreatenedByOpponent(target, board)) {
                moves.add(target);
            }
        }

        return moves;
    }

    private boolean isThreatenedByOpponent(Square target, ChessBoard board) {
        return board.threatenedSquares(getOpponent(owner)).contains(target);
    }

}
