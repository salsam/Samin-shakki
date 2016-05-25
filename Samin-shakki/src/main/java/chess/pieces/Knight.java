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
public class Knight extends Piece {
    
    public Knight(Square square, Player owner) {
        super(square, owner);
    }
    
    @Override
    public List<Square> possibleMoves(ChessBoard board) {
        int[] rankChange = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] fileChange = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
        List<Square> possibilities = new ArrayList<>();
        
        addPossibilities(fileChange, rankChange, board, possibilities);
        
        return possibilities;
    }
}
