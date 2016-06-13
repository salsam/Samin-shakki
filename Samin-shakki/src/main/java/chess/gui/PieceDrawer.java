package chess.gui;

import static chess.gui.io.ImageLoader.getImage;
import chess.logic.board.Player;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author samisalo
 */
public class PieceDrawer {

    private Map<Tuple, Image> images;

    public PieceDrawer() {
        images = new HashMap();
        addImages();
    }

    private void addImages() {
        images.put(new Tuple(Bishop.class, Player.BLACK), getImage("blackBishop1.png"));
        images.put(new Tuple(Bishop.class, Player.WHITE), getImage("whiteBishop1.png"));
        images.put(new Tuple(King.class, Player.BLACK), getImage("blackKing1.png"));
        images.put(new Tuple(King.class, Player.WHITE), getImage("whiteKing1.png"));
        images.put(new Tuple(Knight.class, Player.BLACK), getImage("blackKnight1.png"));
        images.put(new Tuple(Knight.class, Player.WHITE), getImage("whiteKnight1.png"));
        images.put(new Tuple(Pawn.class, Player.BLACK), getImage("blackPawn1.png"));
        images.put(new Tuple(Pawn.class, Player.WHITE), getImage("whitePawn1.png"));
        images.put(new Tuple(Queen.class, Player.BLACK), getImage("blackQueen1.png"));
        images.put(new Tuple(Queen.class, Player.WHITE), getImage("whiteQueen1.png"));
        images.put(new Tuple(Rook.class, Player.BLACK), getImage("blackRook1.png"));
        images.put(new Tuple(Rook.class, Player.WHITE), getImage("whiteRook1.png"));
    }

    /**
     * Draws a picture of chosen piece. Picture depends on the class of chosen
     * piece as well as the owner of piece.
     *
     * @param piece piece to be drawn.
     * @param graphics Graphics object used to draw the image.
     * @param sideLength length of each square's sides.
     */
    public void draw(Piece piece, Graphics graphics, int sideLength) {
        int x = piece.getColumn() * sideLength;
        int y = piece.getRow() * sideLength;
        graphics.drawImage(images.get(new Tuple(piece.getClass(), piece.getOwner())), x, y, sideLength, sideLength, null);
    }
}
