/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.logic.pieces;

import chess.logic.board.ChessBoard;
import java.util.List;
import chess.logic.board.Player;
import chess.logic.board.Square;

/**
 *
 * @author sami
 */
public class Knight extends Piece {

    public Knight(Square square, Player owner) {
        super(square, owner);
    }
    
    @Override
    public Piece clone(Square location) {
        return new Knight(location, this.owner);
    }

    @Override
    public List<Square> threatenedSquares(ChessBoard board) {
        int[] rankChange = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] fileChange = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

        return possibilities(fileChange, rankChange, board);
    }
}
