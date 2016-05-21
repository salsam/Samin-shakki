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
        putBishop(2, 0, Player.WHITE);
        putBishop(5, 0, Player.WHITE);
        putBishop(2, 7, Player.BLACK);
        putBishop(5, 7, Player.BLACK);
    }

    private void initializeKingsAndQueens() {
        putQueen(3, 0, Player.WHITE);
        putKing(4, 0, Player.WHITE);
        putKing(3, 7, Player.BLACK);
        putQueen(4, 7, Player.BLACK);
    }

    private void initializeKnights() {
        putKnight(1, 0, Player.WHITE);
        putKnight(6, 1, Player.WHITE);
        putKnight(1, 7, Player.BLACK);
        putKnight(6, 7, Player.BLACK);
    }

    private void initializeRooks() {
        putRook(0, 0, Player.WHITE);
        putRook(7, 0, Player.WHITE);
        putRook(0, 7, Player.BLACK);
        putRook(7, 7, Player.BLACK);
    }

    public void initializePawns() {
        for (int i = 0; i < 8; i++) {
            putPawn(i, 1, Player.WHITE);
            putPawn(i, 6, Player.BLACK);

        }
    }

    public void putBishop(int column, int row, Player owner) {
        Bishop bishop = new Bishop(column, row, owner);
        board[column][row].setPiece(bishop);
        pieces.add(bishop);
    }

    public void putKing(int column, int row, Player owner) {
        King king = new King(column, row, owner);
        board[column][row].setPiece(king);
        pieces.add(king);
    }

    public void putKnight(int column, int row, Player owner) {
        Knight knight = new Knight(column, row, owner);
        board[column][row].setPiece(knight);
        pieces.add(knight);
    }

    public void putQueen(int column, int row, Player owner) {
        Queen queen = new Queen(column, row, owner);
        board[column][row].setPiece(queen);
        pieces.add(queen);
    }

    public void putPawn(int column, int row, Player owner) {
        Pawn pawn = new Pawn(column, row, owner);
        board[column][row].setPiece(pawn);
        pieces.add(pawn);
    }

    public void putRook(int column, int row, Player owner) {
        Rook rook = new Rook(column, row, owner);
        board[column][row].setPiece(rook);
        pieces.add(rook);
    }
}
