package chess.board;

import chess.pieces.*;

/**
 *
 * @author samisalo
 */
public class StandardBoardInitializer extends ChessBoardInitializer {

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
        putPieceOnBoard(board, new Bishop(board.getSquare(2, 0), Player.WHITE));
        putPieceOnBoard(board, new Bishop(board.getSquare(5, 0), Player.WHITE));
        putPieceOnBoard(board, new Bishop(board.getSquare(2, 7), Player.BLACK));
        putPieceOnBoard(board, new Bishop(board.getSquare(5, 7), Player.BLACK));
    }

    private void initializeKingsAndQueens(ChessBoard board) {
        putPieceOnBoard(board, new Queen(board.getSquare(3, 0), Player.WHITE));
        putPieceOnBoard(board, new King(board.getSquare(4, 0), Player.WHITE));
        putPieceOnBoard(board, new King(board.getSquare(3, 7), Player.BLACK));
        putPieceOnBoard(board, new Queen(board.getSquare(4, 7), Player.BLACK));
    }

    private void initializeKnights(ChessBoard board) {
        putPieceOnBoard(board, new Knight(board.getSquare(1, 0), Player.WHITE));
        putPieceOnBoard(board, new Knight(board.getSquare(6, 0), Player.WHITE));
        putPieceOnBoard(board, new Knight(board.getSquare(1, 7), Player.BLACK));
        putPieceOnBoard(board, new Knight(board.getSquare(6, 7), Player.BLACK));
    }

    private void initializeRooks(ChessBoard board) {
        putPieceOnBoard(board, new Rook(board.getSquare(0, 0), Player.WHITE));
        putPieceOnBoard(board, new Rook(board.getSquare(7, 0), Player.WHITE));
        putPieceOnBoard(board, new Rook(board.getSquare(0, 7), Player.BLACK));
        putPieceOnBoard(board, new Rook(board.getSquare(7, 7), Player.BLACK));
    }

    public void initializePawns(ChessBoard board) {
        for (int i = 0; i < 8; i++) {
            putPieceOnBoard(board, new Pawn(board.getSquare(i, 1), Player.WHITE));
            putPieceOnBoard(board, new Pawn(board.getSquare(i, 6), Player.BLACK));

        }
    }
}
