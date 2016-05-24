package chess.board;

import chess.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sami
 */
public class ChessBoard {

    private Square[][] board;
    private List<Piece> pieces;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    public ChessBoard() {
        this.board = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new Square(i, j);
            }
        }
        this.pieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
    }

    public Square[][] getBoard() {
        return board;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public Square getSquare(int file, int rank) {
        return board[file][rank];
    }

    public boolean withinTable(int file, int rank) {
        if (file < 0 || file >= board.length) {
            return false;
        }
        if (rank < 0 || rank >= board[0].length) {
            return false;
        }
        return true;
    }
}
