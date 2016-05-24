/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.pieces;

import chess.board.ChessBoard;
import java.util.ArrayList;
import java.util.List;
import chess.board.Player;
import chess.board.Square;

/**
 *
 * @author sami
 */
public class King extends Piece {

    public King(Square square, Player owner) {
        super(square, owner);
    }

    @Override
    public List<Square> possibleMoves(ChessBoard board) {
        List<Square> possibilities = new ArrayList<>();
        Square target;
        int[] fileChange = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        int[] rankChange = new int[]{1, 1, 1, 0, 0, -1, -1, -1};
        int newFile;
        int newRank;

        for (int i = 0; i < 8; i++) {
            newFile = location.getFile() + fileChange[i];
            newRank = location.getRank() + rankChange[i];

            if (!board.withinTable(newFile, newRank)) {
                continue;
            }
            target = board.getBoard()[newFile][newRank];
            if (legalToMoveTo(target, board)) {
                possibilities.add(target);
            }
        }

        return possibilities;
    }
}
