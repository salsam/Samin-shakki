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
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author sami
 */
public class Rook extends Piece {

    public Rook(Square square, Player owner) {
        super(square, owner);
        try {
            if (owner == Player.BLACK) {
                this.picture = ImageIO.read(new File("/home/sami/Samin-shakki/Samin-shakki/src/main/resources/blackRook1.png"));
            } else {
                this.picture = ImageIO.read(new File("/home/sami/Samin-shakki/Samin-shakki/src/main/resources/whiteRook1.png"));
            }
        } catch (IOException e) {
        }
    }

    /**
     * Returns a field to field copy of this piece.
     *
     * @param location location where the clone will be placed
     * @return deep copy of this rook
     */
    @Override
    public Piece clone(Square location) {
        return new Rook(location, this.owner);
    }

    /**
     * Return a list containing all squares that this rook threatens.
     *
     * @param board board on which this rook moves
     * @return list containing all squares this rook threatens
     */
    @Override
    public List<Square> threatenedSquares(ChessBoardLogic board) {
        List<Square> possibilities = new ArrayList<>();
        addHorizontalPossibilities(location, board, possibilities);
        addVerticalPossibilities(location, board, possibilities);

        return possibilities;
    }
}
