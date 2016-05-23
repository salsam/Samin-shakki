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
        List<Square> possibilities = new ArrayList<>();
        int targetFile;
        int targetRank;
        int changeOfFile = 2;
        int changeOfRank = 1;
        int signOfFileChange = 1;
        int signOfRankChange = 1;
        Square target;

        for (int i = 0; i < 8; i++) {
            targetFile = location.getFile() + signOfFileChange * changeOfFile;
            targetRank = location.getRank() + signOfRankChange * changeOfRank;
            target = board[targetFile][targetRank];
            if (legalToMoveTo(target)) {
                possibilities.add(target);
            }
            if (i % 4 == 0) {
                changeOfRank = 3 - changeOfRank;
                changeOfFile = 3 - changeOfFile;
            }

            if (i % 2 == 0) {
                signOfRankChange *= -1;
            }
            signOfFileChange *= -1;
        }

        return possibilities;
    }
}
