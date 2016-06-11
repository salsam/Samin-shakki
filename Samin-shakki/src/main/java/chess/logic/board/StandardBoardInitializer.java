package chess.logic.board;

import chess.logic.pieces.Rook;
import chess.logic.pieces.Pawn;
import chess.logic.pieces.Knight;
import chess.logic.pieces.King;
import chess.logic.pieces.Queen;
import chess.logic.pieces.Bishop;

/**
 * This class is used to initialise standard starting positions of chess. Using
 * method @see #initialise(ChessBoard)
 *
 * @author samisalo
 */
public class StandardBoardInitializer extends ChessBoardInitializer {

    /**
     * Initialises the ChessBoard given as parameter with standard chess
     * starting positions.
     *
     * @param board ChessBoard to initialised.
     */
    @Override
    public void initialise(ChessBoard board) {
        clearBoard(board);
        initialiseBishops(board);
        initialiseKingsAndQueens(board);
        initialiseKnights(board);
        initialisePawns(board);
        initialiseRooks(board);
    }

    private void initialiseBishops(ChessBoard board) {
        putPieceOnBoard(board, new Bishop(board.getSquare(2, 0), Player.WHITE));
        putPieceOnBoard(board, new Bishop(board.getSquare(5, 0), Player.WHITE));
        putPieceOnBoard(board, new Bishop(board.getSquare(2, 7), Player.BLACK));
        putPieceOnBoard(board, new Bishop(board.getSquare(5, 7), Player.BLACK));
    }

    private void initialiseKingsAndQueens(ChessBoard board) {
        putPieceOnBoard(board, new Queen(board.getSquare(3, 0), Player.WHITE));
        putPieceOnBoard(board, new King(board.getSquare(4, 0), Player.WHITE));
        putPieceOnBoard(board, new King(board.getSquare(3, 7), Player.BLACK));
        putPieceOnBoard(board, new Queen(board.getSquare(4, 7), Player.BLACK));
    }

    private void initialiseKnights(ChessBoard board) {
        putPieceOnBoard(board, new Knight(board.getSquare(1, 0), Player.WHITE));
        putPieceOnBoard(board, new Knight(board.getSquare(6, 0), Player.WHITE));
        putPieceOnBoard(board, new Knight(board.getSquare(1, 7), Player.BLACK));
        putPieceOnBoard(board, new Knight(board.getSquare(6, 7), Player.BLACK));
    }

    private void initialiseRooks(ChessBoard board) {
        putPieceOnBoard(board, new Rook(board.getSquare(0, 0), Player.WHITE));
        putPieceOnBoard(board, new Rook(board.getSquare(7, 0), Player.WHITE));
        putPieceOnBoard(board, new Rook(board.getSquare(0, 7), Player.BLACK));
        putPieceOnBoard(board, new Rook(board.getSquare(7, 7), Player.BLACK));
    }

    private void initialisePawns(ChessBoard board) {
        for (int i = 0; i < 8; i++) {
            putPieceOnBoard(board, new Pawn(board.getSquare(i, 1), Player.WHITE));
            putPieceOnBoard(board, new Pawn(board.getSquare(i, 6), Player.BLACK));

        }
    }
}
