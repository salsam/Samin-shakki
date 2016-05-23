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
public class King extends Piece {

    public King(int file, int rank, Player owner) {
        super(file, rank, owner);
    }

    @Override
    public List<Square> possibleMoves(Square[][] board) {
        List<Square> moves = new ArrayList<>();
        int changeOfFile = -1;
        int changeOfRank = 1;
        Square target;

        for (int i = 1; i < 10; i++) {
            target = board[location.getFile() + changeOfFile][location.getRank() + changeOfRank];
            if (changeOfFile != 0 || changeOfRank != 0) {
                if (legalToMoveTo(target)) {
                    moves.add(target);
                }
            }
            if (i % 3 == 0) {
                changeOfRank -= 1;
            }

            changeOfFile = (i % 3) - 1;
        }

        return moves;
    }
}
