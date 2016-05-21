/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.pieces;

import java.util.ArrayList;
import java.util.List;
import logic.assistance.Player;
import logic.assistance.Square;

/**
 *
 * @author sami
 */
public class King extends Piece {

    public King(int x, int y, Player owner) {
        super(x, y, owner);
    }

    @Override
    public List<Square> possibleMoves(Square[][] board) {
        List<Square> moves = new ArrayList<>();
        int changeOfColumn = 1;
        int changeOfRow = 1;
        Square target;

        for (int i = 0; i < 8; i++) {
            target = board[location.getColumn() + changeOfColumn][location.getRow() + changeOfRow];
            if (legalToMoveTo(target)) {
                moves.add(target);
            }
            if (i % 4 == 0) {
                changeOfRow *= -1;
            }

            if (i % 2 == 0) {
                changeOfColumn *= -1;
            }
        }

        return moves;
    }
}
