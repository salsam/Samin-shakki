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
public class Rook extends Piece {

    public Rook(int file, int rank, Player owner) {
        super(file, rank, owner);
    }

    @Override
    public List<Square> possibleMoves(Square[][] board) {
        List<Square> possibilities = new ArrayList<>();
        horizontalPossibilities(location, board, possibilities);
        verticalPossibilities(location, board, possibilities);

        return possibilities;
    }
}
