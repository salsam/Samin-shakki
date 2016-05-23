package chess.board;

import chess.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sami
 */
public class ChessBoard {

    Square[][] board;
    List<Piece> pieces;

    public ChessBoard() {
        this.board = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new Square(i, j);
            }
        }
        this.pieces = new ArrayList<>();
    }

    public Square[][] getBoard() {
        return board;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
