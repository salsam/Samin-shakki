package logic.assistance;

import java.util.ArrayList;
import java.util.List;
import logic.pieces.*;

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

    public void initialize() {
        initializePawns();
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 7) {
                initializeRooks(i);
            }

            if (i == 1 || i == 6) {
                initializeKnights(i);
            }

            if (i == 2 || i == 5) {
                initializeBishops(i);
            }

            if (i == 3 || i == 4) {
                initializeKingAndQueen(i);
            }
        }
    }

    private void initializeBishops(int i) {
        board[0][i].setPiece(new Bishop(0, i, Player.WHITE));
        pieces.add(new Bishop(0, i, Player.WHITE));
        board[7][i].setPiece(new Bishop(7, i, Player.BLACK));
        pieces.add(new Bishop(7, i, Player.BLACK));
    }

    private void initializeKingAndQueen(int i) {
        board[0][i].setPiece(new Queen(0, i, Player.WHITE));
        pieces.add(new Queen(0, i, Player.WHITE));
        board[7][i].setPiece(new King(7, i, Player.BLACK));
        pieces.add(new King(7, i, Player.BLACK));
    }

    private void initializeKnights(int i) {
        board[0][i].setPiece(new Knight(0, i, Player.WHITE));
        pieces.add(new Knight(0, i, Player.WHITE));
        board[7][i].setPiece(new Knight(7, i, Player.BLACK));
        pieces.add(new Knight(7, i, Player.BLACK));
    }

    private void initializeRooks(int i) {
        if (i == 3) {
            board[0][i].setPiece(new Rook(0, i, Player.WHITE));
            pieces.add(new Rook(0, i, Player.WHITE));
            board[7][i].setPiece(new Rook(7, i, Player.BLACK));
            pieces.add(new Rook(7, i, Player.BLACK));
        } else if (i == 4) {
            board[0][i].setPiece(new King(0, i, Player.WHITE));
            pieces.add(new King(0, i, Player.WHITE));
            board[7][i].setPiece(new Queen(7, i, Player.BLACK));
            pieces.add(new Queen(7, i, Player.BLACK));
        }
    }

    public void initializePawns() {
        Pawn pawn;
        for (int i = 0; i < 8; i++) {
            pawn = new Pawn(i, 0, Player.WHITE);
            board[i][1].setPiece(pawn);
            pieces.add(pawn);
            pawn = new Pawn(i, 6, Player.BLACK);
            board[i][6].setPiece(pawn);
            pieces.add(pawn);
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

}
