package chess.logic.board;

import chess.logic.piecemovers.RookMover;
import chess.logic.piecemovers.PawnMover;
import chess.logic.piecemovers.KnightMover;
import chess.logic.piecemovers.KingMover;
import chess.logic.piecemovers.QueenMover;
import chess.logic.piecemovers.BishopMover;

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
        putPieceOnBoard(board, new BishopMover(board.getSquare(2, 0), Player.WHITE));
        putPieceOnBoard(board, new BishopMover(board.getSquare(5, 0), Player.WHITE));
        putPieceOnBoard(board, new BishopMover(board.getSquare(2, 7), Player.BLACK));
        putPieceOnBoard(board, new BishopMover(board.getSquare(5, 7), Player.BLACK));
    }

    private void initialiseKingsAndQueens(ChessBoard board) {
        putPieceOnBoard(board, new QueenMover(board.getSquare(3, 0), Player.WHITE));
        putPieceOnBoard(board, new KingMover(board.getSquare(4, 0), Player.WHITE));
        putPieceOnBoard(board, new KingMover(board.getSquare(3, 7), Player.BLACK));
        putPieceOnBoard(board, new QueenMover(board.getSquare(4, 7), Player.BLACK));
    }

    private void initialiseKnights(ChessBoard board) {
        putPieceOnBoard(board, new KnightMover(board.getSquare(1, 0), Player.WHITE));
        putPieceOnBoard(board, new KnightMover(board.getSquare(6, 0), Player.WHITE));
        putPieceOnBoard(board, new KnightMover(board.getSquare(1, 7), Player.BLACK));
        putPieceOnBoard(board, new KnightMover(board.getSquare(6, 7), Player.BLACK));
    }

    private void initialiseRooks(ChessBoard board) {
        putPieceOnBoard(board, new RookMover(board.getSquare(0, 0), Player.WHITE));
        putPieceOnBoard(board, new RookMover(board.getSquare(7, 0), Player.WHITE));
        putPieceOnBoard(board, new RookMover(board.getSquare(0, 7), Player.BLACK));
        putPieceOnBoard(board, new RookMover(board.getSquare(7, 7), Player.BLACK));
    }

    private void initialisePawns(ChessBoard board) {
        for (int i = 0; i < 8; i++) {
            putPieceOnBoard(board, new PawnMover(board.getSquare(i, 1), Player.WHITE));
            putPieceOnBoard(board, new PawnMover(board.getSquare(i, 6), Player.BLACK));

        }
    }
}
