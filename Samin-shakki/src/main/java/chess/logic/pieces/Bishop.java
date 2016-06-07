/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template column, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.logic.pieces;

import chess.logic.board.ChessBoardLogic;
import java.util.List;
import chess.logic.board.Player;
import chess.logic.board.Square;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * This class is responsible of movement calculation of bishops.
 *
 * @author sami
 */
public class Bishop extends Piece {

    public Bishop(Square square, Player owner) {
        super(square, owner);
        try {
            if (owner == Player.BLACK) {
                this.picture = ImageIO.read(new File("/home/sami/Samin-shakki/Samin-shakki/src/main/resources/blackBishop1.png"));
            } else {
                this.picture = ImageIO.read(new File("/home/sami/Samin-shakki/Samin-shakki/src/main/resources/whiteBishop1.png"));
            }
        } catch (IOException e) {
        }
    }

    /**
     * Returns a field to field copy of this piece.
     *
     * @param location location where the clone will be placed
     * @return deep copy of this bishop
     */
    @Override
    public Piece clone(Square location) {
        return new Bishop(location, this.owner);
    }

    /**
     * Return a list containing all squares that this bishop threatens.
     *
     * @param board board where this bishop moves
     * @return list containing all squares this bishop threatens
     */
    @Override
    public List<Square> threatenedSquares(ChessBoardLogic board) {
        List<Square> possibilities = new ArrayList<>();
        addDiagonalPossibilities(location, board, possibilities);

        return possibilities;
    }
}
