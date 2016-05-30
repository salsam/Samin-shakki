/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.pieces;

import chess.board.ChessBoard;
import java.util.List;
import chess.board.Player;
import chess.board.Square;
import java.util.ArrayList;

/**
 *
 * @author sami
 */
public class Bishop extends Piece {

    public Bishop(Square square, Player owner) {
        super(square, owner);
    }

    @Override
    public List<Square> threatenedSquares(ChessBoard board) {
        List<Square> possibilities = new ArrayList<>();
        addDiagonalPossibilities(location, board, possibilities);

        return possibilities;
    }
}
