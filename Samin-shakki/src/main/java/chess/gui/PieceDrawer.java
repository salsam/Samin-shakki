package chess.gui;

import static chess.io.ImageLoader.getImage;
import chess.logic.board.Player;
import chess.logic.pieces.Bishop;
import chess.logic.pieces.King;
import chess.logic.pieces.Knight;
import chess.logic.pieces.Pawn;
import chess.logic.pieces.Piece;
import chess.logic.pieces.Queen;
import chess.logic.pieces.Rook;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author samisalo
 */
public class PieceDrawer {

    private Map<Pair, Image> images;

    public PieceDrawer() {
        images = new HashMap();
        addImages();
    }

    private void addImages() {
        images.put(new Pair(Bishop.class, Player.BLACK), getImage("blackBishop1.png"));
        images.put(new Pair(Bishop.class, Player.WHITE), getImage("whiteBishop1.png"));
        images.put(new Pair(King.class, Player.BLACK), getImage("blackKing1.png"));
        images.put(new Pair(King.class, Player.WHITE), getImage("whiteKing1.png"));
        images.put(new Pair(Knight.class, Player.BLACK), getImage("blackKnight1.png"));
        images.put(new Pair(Knight.class, Player.WHITE), getImage("whiteKnight1.png"));
        images.put(new Pair(Pawn.class, Player.BLACK), getImage("blackPawn1.png"));
        images.put(new Pair(Pawn.class, Player.WHITE), getImage("whitePawn1.png"));
        images.put(new Pair(Queen.class, Player.BLACK), getImage("blackQueen1.png"));
        images.put(new Pair(Queen.class, Player.WHITE), getImage("whiteQueen1.png"));
        images.put(new Pair(Rook.class, Player.BLACK), getImage("blackRook1.png"));
        images.put(new Pair(Rook.class, Player.WHITE), getImage("whiteRook1.png"));
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
        graphics.drawImage(images.get(new Pair(piece.getClass(), piece.getOwner())), x, y, sideLength, sideLength, null);
    }
}
