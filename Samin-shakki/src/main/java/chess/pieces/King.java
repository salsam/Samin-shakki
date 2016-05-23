/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.pieces;

import java.util.ArrayList;
import java.util.List;
import chess.board.Player;
import chess.board.Square;

/**
 *
 * @author sami
 */
public class King extends Piece {

    public King(int file, int rank, Player owner) {
        super(file, rank, owner);
    }

    @Override
    public List<Square> possibleMoves(Square[][] board) {
        List<Square> possibilities = new ArrayList<>();
        Square target;
        int[] fileChange = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        int[] rankChange = new int[]{1, 1, 1, 0, 0, -1, -1, -1};

        for (int i = 0; i < 8; i++) {
            target = board[location.getFile() + fileChange[i]][location.getRank() + rankChange[i]];
            if (legalToMoveTo(target)) {
                possibilities.add(target);
            }
        }

        return possibilities;
    }
}
