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
public class Bishop extends Piece {

    public Bishop(int x, int y, Player owner) {
        super(x, y, owner);
    }

    @Override
    public List<Square> possibleMoves(Square[][] board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
