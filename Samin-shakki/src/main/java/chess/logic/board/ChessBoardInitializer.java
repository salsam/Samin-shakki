package chess.logic.board;

import chess.logic.pieces.Piece;

/**
 * All classes that inherit this abstract class are used to initialise different
 * starting situation on chessboard like empty board or standard starting
 * positions.
 *
 * @author samisalo
 */
public abstract class ChessBoardInitializer {

    public abstract void initialise(ChessBoardLogic board);

    protected void clearBoard(ChessBoardLogic board) {
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
    public void putPieceOnBoard(ChessBoardLogic board, Piece piece) {
        if (board.withinTable(piece.getLocation().getcolumn(), piece.getLocation().getrow())) {

            board.getSquare(piece.getLocation().getcolumn(), piece.getLocation().getrow()).setPiece(piece);
            board.addPieceToOwner(piece.getLocation());
        }
    }
}
