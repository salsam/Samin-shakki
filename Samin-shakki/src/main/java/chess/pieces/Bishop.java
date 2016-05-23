/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.pieces;

import java.util.List;
import chess.board.Player;
import chess.board.Square;
import java.util.ArrayList;

/**
 *
 * @author sami
 */
public class Bishop extends Piece {

    public Bishop(int file, int rank, Player owner) {
        super(file, rank, owner);
    }

    @Override
    public List<Square> possibleMoves(Square[][] board) {
        List<Square> possibilities = new ArrayList<>();
        possibilitiesToDirection(location, board, possibilities, 1, 1);
        possibilitiesToDirection(location, board, possibilities, 1, -1);
        possibilitiesToDirection(location, board, possibilities, -1, 1);
        possibilitiesToDirection(location, board, possibilities, -1, -1);

        return possibilities;
    }

    private void possibilitiesToDirection(Square current, Square[][] board, List<Square> possibilities, int fileChange, int rankChange) {
        Square target = current;

        while (legalToMoveTo(target)) {
//            if (target.includesAPiece() && target.getPiece()) {
//                break;
//            }
            target = board[target.getFile() + fileChange][target.getRank() + rankChange];
            possibilities.add(target);
        }
    }
}
