package chess.logic.game;

import java.util.List;
import chess.logic.board.ChessBoardLogic;
import chess.logic.board.Square;
import chess.logic.board.ChessBoardInitializer;
import chess.logic.board.Player;
import static chess.logic.board.Player.getOpponent;
import chess.logic.pieces.King;
import chess.logic.pieces.Piece;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * This class is responsible for one game of chess with given starting
 * positions.
 *
 * @author sami
 */
public class Game {

    private ChessBoardLogic board;
    private int turn;
    private boolean continues;
    private Scanner reader;
    private boolean cancelled;
    private LegalityChecker checker;

    public Game(ChessBoardInitializer init) {
        this.board = new ChessBoardLogic();
        init.initialise(board);
        turn = 1;
        continues = true;
        reader = new Scanner(System.in);
        checker = new LegalityChecker(board);
    }

    public Player whoseTurn() {
        if (turn % 2 == 1) {
            return Player.WHITE;
        } else {
            return Player.BLACK;
        }
    }

    public int getTurn() {
        return turn;
    }

    public ChessBoardLogic getChessBoard() {
        return this.board;
    }

    public LegalityChecker getChecker() {
        return checker;
    }

    public void nextTurn() {
        turn++;
    }

    public void turn(Player player) {
        Piece chosen = null;
        Square target = null;
        Set<Square> possibleMoves;
        cancelled = true;
        board.updateThreatenedSquares(getOpponent(player));
        ChessBoardLogic backUp = board.copy();

        if (checkIfChecked(player)) {
            if (checkMate(player)) {
                System.out.println(getOpponent(player) + "won!");
                continues = false;
                return;
            }
        }

        board.updateThreatenedSquares(getOpponent(player));

        System.out.println(player + "'s turn");

        while (true) {
            while (cancelled) {
                cancelled = false;
                checker.setBoard(board);
                chosen = chooseAPieceToMove(player);
                possibleMoves = chosen.possibleMoves(board);
                target = chooseATargetSquareForMovement(possibleMoves);

            }

            chosen.move(target, board);
            board.updateThreatenedSquares(getOpponent(player));

            if (!checkIfChecked(player)) {
                break;
            } else {
                cancelled = true;
                board = backUp.copy();
                board.updateThreatenedSquares(getOpponent(player));
                System.out.println("Your king is checked, you have to prevent that this turn!");
            }
        }
    }

    private boolean checkIfChecked(Player player) {
        Map<Player, King> kings = board.getKings();
        return board.threatenedSquares(getOpponent(player)).contains(kings.get(player).getLocation());
    }

    private Piece chooseAPieceToMove(Player player) {
        String input = "";
        int column = 0;
        int row = 0;

        while (input.equals("")) {
            System.out.println("Please write coordinates in the form columnrow of a piece to move");
            input = reader.nextLine();

            if (!checker.inputIsInAllowedForm(input, false)) {
                input = "";
                continue;
            }

            int x = Character.getNumericValue(input.charAt(0));
            int y = Character.getNumericValue(input.charAt(1));

            if (!checker.checkThatPlayerOwnsAPieceOnTheTargetSquare(player, x, y)) {
                input = "";
            }

        }
        return board.getBoard()[column][row].getPiece();
    }

    private Square chooseATargetSquareForMovement(Set<Square> possibilities) {
        String input = "";
        int column = 0;
        int row = 0;

        while (input.equals("")) {
            System.out.println("Please write coordinates in form columnrow of a square to move to or x to cancel");
            input = reader.nextLine();

            if (input.equals("x")) {
                cancelled = true;
                break;
            }

            if (!checker.inputIsInAllowedForm(input, true)) {
                input = "";
                continue;
            }

            input = checker.checkThatMovementIsLegal(possibilities, input);
            if (input.equals("")) {
                System.out.println("Please, choose one of the legal movements: ");
            }

        }

        return board.getSquare(column, row);
    }

    private boolean checkMate(Player player) {
        ChessBoardLogic backUp = board.copy();
        for (Piece piece : board.getPieces(player)) {
            for (Square possibility : piece.possibleMoves(board)) {
                piece.move(possibility, board);
                board.updateThreatenedSquares(getOpponent(player));
                if (!checkIfChecked(player)) {
                    board = backUp.copy();
                    return false;
                }
                board = backUp.copy();
            }
        }

        return true;
    }
}
