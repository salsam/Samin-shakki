package chess.logic.board.chessboardinitializers;

import chess.logic.board.ChessBoard;
import chess.logic.board.Square;
import chess.logic.pieces.King;
import chess.logic.pieces.Piece;

/**
 * All classes that inherit this abstract class are used to initialize different
 * starting situations on chessboard like empty board or standard starting
 * positions.
 *
 * @author samisalo
 */
public abstract class ChessBoardInitializer {

    /**
     * This method will be used to initialize chessboard meaning setting up
     * starting positions depending on which ChessBoardInitializer is used..
     *
     * @param board board to initialized
     */
    public abstract void initialize(ChessBoard board);

    /**
     * Adds the piece on target square to list of pieces its owner owns. Also
     * adds a reference to Map Kings if the piece is of King class.
     *
     * @param target Square
     * @param chessBoard ChessBoard to which piece will be added
     */
    public static void addPieceToOwner(Square target, ChessBoard chessBoard) {
        if (target.containsAPiece()) {
            Piece piece = target.getPiece();
            if (piece.getClass() == King.class) {
                chessBoard.getKings().put(piece.getOwner(), (King) piece);
            }
            chessBoard.getPieces(piece.getOwner()).add(piece);
        }
    }

    /**
     * Removes target piece from its owner's owned pieces list.
     *
     * @param piece The piece you want to remove.
     * @param chessBoard ChessBoardLogic where piece will be removed from
     */
    public static void removePieceFromOwner(Piece piece, ChessBoard chessBoard) {
        chessBoard.getPieces(piece.getOwner()).remove(piece);
    }

    protected void clearBoard(ChessBoard board) {
        for (int i = 0; i < board.getTable().length; i++) {
            for (int j = 0; j < board.getTable()[0].length; j++) {
                if (board.getSquare(i, j).containsAPiece()) {
                    removePieceFromOwner(board.getSquare(i, j).getPiece(), board);
                    board.getSquare(i, j).setPiece(null);
                }
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
    public static void putPieceOnBoard(ChessBoard board, Piece piece) {
        if (board.withinTable(piece.getColumn(), piece.getRow())) {

            board.getSquare(piece.getColumn(), piece.getRow()).setPiece(piece);
            addPieceToOwner(board.getSquare(piece.getColumn(), piece.getRow()), board);
        }
    }
}