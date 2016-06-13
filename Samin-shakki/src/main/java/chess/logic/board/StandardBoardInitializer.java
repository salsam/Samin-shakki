package chess.logic.board;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

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
        putPieceOnBoard(board, new Bishop(2, 0, Player.WHITE));
        putPieceOnBoard(board, new Bishop(5, 0, Player.WHITE));
        putPieceOnBoard(board, new Bishop(2, 7, Player.BLACK));
        putPieceOnBoard(board, new Bishop(5, 7, Player.BLACK));
    }

    private void initialiseKingsAndQueens(ChessBoard board) {
        putPieceOnBoard(board, new Queen(3, 0, Player.WHITE));
        putPieceOnBoard(board, new King(4, 0, Player.WHITE));
        putPieceOnBoard(board, new King(3, 7, Player.BLACK));
        putPieceOnBoard(board, new Queen(4, 7, Player.BLACK));
    }

    private void initialiseKnights(ChessBoard board) {
        putPieceOnBoard(board, new Knight(1, 0, Player.WHITE));
        putPieceOnBoard(board, new Knight(6, 0, Player.WHITE));
        putPieceOnBoard(board, new Knight(1, 7, Player.BLACK));
        putPieceOnBoard(board, new Knight(6, 7, Player.BLACK));
    }

    private void initialiseRooks(ChessBoard board) {
        putPieceOnBoard(board, new Rook(0, 0, Player.WHITE));
        putPieceOnBoard(board, new Rook(7, 0, Player.WHITE));
        putPieceOnBoard(board, new Rook(0, 7, Player.BLACK));
        putPieceOnBoard(board, new Rook(7, 7, Player.BLACK));
    }

    private void initialisePawns(ChessBoard board) {
        for (int i = 0; i < 8; i++) {
            putPieceOnBoard(board, new Pawn(i, 1, Player.WHITE));
            putPieceOnBoard(board, new Pawn(i, 6, Player.BLACK));

        }
    }
}
