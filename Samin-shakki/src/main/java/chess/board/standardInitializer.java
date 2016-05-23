package chess.board;

import chess.pieces.*;

/**
 *
 * @author samisalo
 */
public class standardInitializer extends chessBoardInitializer {

    @Override
    public void initialize(ChessBoard board) {
        clearBoard(board);
        initializeBishops(board);
        initializeKingsAndQueens(board);
        initializeKnights(board);
        initializePawns(board);
        initializeRooks(board);
    }

    private void initializeBishops(ChessBoard board) {
        putPiece(board, new Bishop(2, 0, Player.WHITE));
        putPiece(board, new Bishop(5, 0, Player.WHITE));
        putPiece(board, new Bishop(2, 7, Player.BLACK));
        putPiece(board, new Bishop(5, 7, Player.BLACK));
    }

    private void initializeKingsAndQueens(ChessBoard board) {
        putPiece(board, new Queen(3, 0, Player.WHITE));
        putPiece(board, new King(4, 0, Player.WHITE));
        putPiece(board, new King(3, 7, Player.BLACK));
        putPiece(board, new Queen(4, 7, Player.BLACK));
    }

    private void initializeKnights(ChessBoard board) {
        putPiece(board, new Knight(1, 0, Player.WHITE));
        putPiece(board, new Knight(6, 1, Player.WHITE));
        putPiece(board, new Knight(1, 7, Player.BLACK));
        putPiece(board, new Knight(6, 7, Player.BLACK));
    }

    private void initializeRooks(ChessBoard board) {
        putPiece(board, new Rook(0, 0, Player.WHITE));
        putPiece(board, new Rook(7, 0, Player.WHITE));
        putPiece(board, new Rook(0, 7, Player.BLACK));
        putPiece(board, new Rook(7, 7, Player.BLACK));
    }

    public void initializePawns(ChessBoard board) {
        for (int i = 0; i < 8; i++) {
            putPiece(board, new Pawn(i, 1, Player.WHITE));
            putPiece(board, new Pawn(i, 6, Player.BLACK));

        }
    }
}
