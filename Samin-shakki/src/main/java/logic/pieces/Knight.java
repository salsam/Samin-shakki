/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.pieces;

import java.util.List;
import logic.assistance.Player;
import logic.assistance.Square;

/**
 *
 * @author sami
 */
public class Knight extends Piece {

    public Knight(int column, int row, Player owner) {
        super(column, row, owner);
    }

    @Override
    public List<Square> possibleMoves(Square[][] board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
