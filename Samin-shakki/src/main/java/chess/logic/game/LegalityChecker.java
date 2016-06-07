package chess.logic.game;

import chess.logic.board.ChessBoardLogic;
import chess.logic.board.Player;
import chess.logic.board.Square;
import java.util.List;

/**
 * This class provides methods to check if playerOwns targeted piece, whether
 * certain movement is legal and whether given input in in allowed form.
 *
 * @author samisalo
 */
public class LegalityChecker {

    private ChessBoardLogic board;

    public LegalityChecker(ChessBoardLogic board) {
        this.board = board;
    }

    /**
     * Sets parameter to be referenced from field board.
     *
     * @param board board to be referenced from field board
     */
    public void setBoard(ChessBoardLogic board) {
        this.board = board;
    }

    /**
     * Checks whether input is in allowed form. That is length of input must be
     * 2, input must consist of numbers and square corresponding those numbers
     * must be within table. If square cannot be empty also checks if chosen
     * square is empty.
     *
     * @param input input to be validated
     * @param squareCanBeEmpty if true square corresponding input can be empty
     * @return true if input is in allowed form
     */
    public boolean inputIsInAllowedForm(String input, boolean squareCanBeEmpty) {
        int column;
        int row;

        if (input.length() != 2) {
            return false;
        }

        try {
            column = Character.getNumericValue(input.charAt(0));
            row = Character.getNumericValue(input.charAt(1));
        } catch (Exception e) {
            return false;
        }

        if (!board.withinTable(column, row)) {
            return false;
        }

        if (!squareCanBeEmpty && !board.getSquare(column, row).containsAPiece()) {
            return false;
        }

        return true;
    }

    /**
     * Checks whether input corresponds a square that chosen piece can legally
     * move to. If it's true, returns input as it was given. Otherwise returns
     * empty string "".
     *
     * @param possibilities squares chosen piece can legally move to.
     * @param input input that player gave.
     * @return input if input corresponds a possible square. Else empty string
     * "".
     */
    public String checkThatMovementIsLegal(List<Square> possibilities, String input) {
        int column = Character.getNumericValue(input.charAt(0));
        int row = Character.getNumericValue(input.charAt(1));

        if (!possibilities.contains(board.getSquare(column, row))) {
            for (Square possibility : possibilities) {
                System.out.print(possibility + " ");
            }
            input = "";
        }
        return input;
    }

    /**
     * Checks whether player owns a piece on the square of which location is
     * given as input. If player owns a piece on target square, returns input.
     * If not, then returns empty string "".
     *
     * @param player player whose piece we're choosing.
     * @param input input for location of target piece.
     * @return input if player does own a piece on target square, else empty
     * string
     */
    public String checkThatPlayerOwnsAPieceOnTheTargetSquare(Player player, String input) {
        int column = Character.getNumericValue(input.charAt(0));
        int row = Character.getNumericValue(input.charAt(1));

        if (board.getSquare(column, row).getPiece().getOwner() != player) {
            System.out.println("Please, choose one of your own pieces.");
            input = "";
        }
        return input;
    }
}
