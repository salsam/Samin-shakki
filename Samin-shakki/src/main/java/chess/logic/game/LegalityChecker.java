package chess.logic.game;

import chess.logic.board.ChessBoard;
import chess.logic.board.Player;
import chess.pieces.Piece;

/**
 * This class provides methods to check if player owns targeted piece, whether
 * certain movement is legal and whether given input in in allowed form.
 *
 * @author samisalo
 */
public class LegalityChecker {

    private ChessBoard board;

    public LegalityChecker(ChessBoard board) {
        this.board = board;
    }

    /**
     * Sets parameter to be referenced from field board.
     *
     * @param board board to be referenced from field board
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Checks whether input is in allowed form. The square corresponding given
     * column and row must be within table. If square cannot be empty also
     * checks if chosen square is empty.
     *
     * @param column column of target square
     * @param row row of target square
     * @param squareCanBeEmpty if true square corresponding input can be empty
     * @return true if input is in allowed form
     */
    public boolean inputIsInAllowedForm(int column, int row, boolean squareCanBeEmpty) {

        if (!board.withinTable(column, row)) {
            return false;
        }

        if (!squareCanBeEmpty && !board.getSquare(column, row).containsAPiece()) {
            return false;
        }

        return true;
    }

    /**
     * Checks whether player owns a piece on the square of which location is
     * given as input. If player owns a piece on target square, returns input.
     * If not, then returns empty string "".
     *
     * @param player player whose piece we're choosing.
     * @param x x-coordinate of target square
     * @param y y-coordinate of target square
     * @return input if player does own a piece on target square, else empty
     * string
     */
    public Boolean checkPlayerOwnsAPieceOnTheTargetSquare(Player player, int x, int y) {

        if (!inputIsInAllowedForm(x, y, false)) {
            return false;
        }

        if (board.getSquare(x, y).getPiece().getOwner() != player) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether input corresponds a square that chosen piece can legally
     * move to. If it's true, returns input as it was given. Otherwise returns
     * empty string "".
     *
     * @param piece piece chosen for movement.
     * @param column column of target square.
     * @param row row of target square.
     * @return input if input corresponds a possible square. Else empty string
     * "".
     */
    public boolean checkThatMovementIsLegal(Piece piece, int column, int row) {
        return board.getMovementLogic().possibleMoves(piece, board).contains(board.getSquare(column, row));
    }
}
