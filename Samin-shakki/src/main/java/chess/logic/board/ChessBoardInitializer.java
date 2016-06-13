package chess.logic.board;

import chess.logic.piecemovers.KingMover;
import chess.logic.piecemovers.PieceMover;

/**
 * All classes that inherit this abstract class are used to initialise different
 * starting situation on chessboard like empty board or standard starting
 * positions.
 *
 * @author samisalo
 */
public abstract class ChessBoardInitializer {

    /**
     * Adds the piece on target square to list of pieces its owner owns. Also
     * adds a reference to Map Kings if the piece is of King class.
     *
     * @param target Square
     * @param chessBoardLogic ChessBoardLogic to which piece will be added
     */
    public static void addPieceToOwner(Square target, ChessBoard chessBoardLogic) {
        if (target.containsAPiece()) {
            PieceMover piece = target.getPiece();
            if (piece.getClass() == KingMover.class) {
                chessBoardLogic.getKings().put(piece.getOwner(), (KingMover) piece);
            }
            chessBoardLogic.getPieces(piece.getOwner()).add(piece);
        }
    }

    public abstract void initialise(ChessBoard board);

    /**
     * Removes target piece from its owner's owned pieces list.
     *
     * @param piece The piece you want to remove.
     * @param chessBoardLogic ChessBoardLogic where piece will be removed from
     */
    public static void removePieceFromOwner(PieceMover piece, ChessBoard chessBoardLogic) {
        chessBoardLogic.getPieces(piece.getOwner()).remove(piece);
    }

    protected void clearBoard(ChessBoard board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.getSquare(i, j).setPiece(null);
            }
        }
    }

    /**
     * Makes the square piece is located on refer to piece thus adding piece on
     * chessboard.
     *
     * @param board board Piece will be placed on.
     * @param piece piece Piece to be placed.
     */
    public static void putPieceOnBoard(ChessBoard board, PieceMover piece) {
        if (board.withinTable(piece.getLocation().getColumn(), piece.getLocation().getRow())) {

            board.getSquare(piece.getLocation().getColumn(), piece.getLocation().getRow()).setPiece(piece);
            addPieceToOwner(piece.getLocation(), board);
        }
    }
}
