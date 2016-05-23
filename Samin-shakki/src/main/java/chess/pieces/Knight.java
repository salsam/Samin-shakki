/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.pieces;

import java.util.ArrayList;
import java.util.List;
import chess.assistance.Player;
import chess.assistance.Square;

/**
 *
 * @author sami
 */
public class Knight extends Piece {

    public Knight(int file, int rank, Player owner) {
        super(file, rank, owner);
    }

    @Override
    public List<Square> possibleMoves(Square[][] board) {
        int[] rankChange = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] fileChange = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
        List<Square> possibilities = new ArrayList<>();
        Square target;

        for (int i = 0; i < 8; i++) {
            target = board[location.getFile() + fileChange[i]][location.getRank() + rankChange[i]];
            if (legalToMoveTo(target)) {
                possibilities.add(target);
            }
        }

        return possibilities;
    }
}
