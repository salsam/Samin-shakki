package chess.board;

import chess.pieces.Pawn;
import chess.pieces.Knight;
import chess.pieces.Bishop;
import chess.pieces.Piece;
import chess.pieces.Rook;
import chess.pieces.Queen;
import chess.pieces.King;
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
        board = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j);
            }
        }
        pieces = new ArrayList<>();
    }

    public void clearBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].setPiece(null);
            }
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void initialize() {
        clearBoard();
        initializeBishops();
        initializeKingsAndQueens();
        initializeKnights();
        initializePawns();
        initializeRooks();
    }

    private void initializeBishops() {
        putPiece(new Bishop(2, 0, Player.WHITE));
        putPiece(new Bishop(5, 0, Player.WHITE));
        putPiece(new Bishop(2, 7, Player.BLACK));
        putPiece(new Bishop(5, 7, Player.BLACK));
    }

    private void initializeKingsAndQueens() {
        putPiece(new Queen(3, 0, Player.WHITE));
        putPiece(new King(4, 0, Player.WHITE));
        putPiece(new King(3, 7, Player.BLACK));
        putPiece(new Queen(4, 7, Player.BLACK));
    }

    private void initializeKnights() {
        putPiece(new Knight(1, 0, Player.WHITE));
        putPiece(new Knight(6, 1, Player.WHITE));
        putPiece(new Knight(1, 7, Player.BLACK));
        putPiece(new Knight(6, 7, Player.BLACK));
    }

    private void initializeRooks() {
        putPiece(new Rook(0, 0, Player.WHITE));
        putPiece(new Rook(7, 0, Player.WHITE));
        putPiece(new Rook(0, 7, Player.BLACK));
        putPiece(new Rook(7, 7, Player.BLACK));
    }

    public void initializePawns() {
        for (int i = 0; i < 8; i++) {
            putPiece(new Pawn(i, 1, Player.WHITE));
            putPiece(new Pawn(i, 6, Player.BLACK));

        }
    }

    public void putPiece(Piece piece) {
        board[piece.getLocation().getFile()][piece.getLocation().getRank()].setPiece(piece);
        pieces.add(piece);
    }
}
