package chess.logic.game;

import java.util.List;
import chess.logic.board.ChessBoard;
import chess.logic.board.Square;
import chess.logic.board.ChessBoardInitializer;
import chess.logic.board.Player;
import static chess.logic.board.Player.getOpponent;
import chess.gui.ChessNotationTableDrawer;
import chess.logic.pieces.King;
import chess.logic.pieces.Piece;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author sami
 */
public class Game {

    private ChessBoard board;
    private int turn;
    private boolean continues;
    private Scanner reader;
    private ChessNotationTableDrawer graphics;
    private boolean cancelled;

    public Game(ChessBoardInitializer init) {
        this.board = new ChessBoard();
        init.initialize(board);
        turn = 1;
        continues = true;
        reader = new Scanner(System.in);
        graphics = new ChessNotationTableDrawer();
    }

    public void start() {
        while (continues) {
            if (turn % 2 == 1) {
                turn(Player.WHITE);
            } else {
                turn(Player.BLACK);
            }

            if (turn == 100) {
                continues = false;
            }

            turn++;
        }
    }

    private void turn(Player player) {
        Piece chosen = null;
        Square target = null;
        List<Square> possibleMoves;
        graphics.draw(board);
        cancelled = true;
        board.updateThreatenedSquares(getOpponent(player));
        ChessBoard backUp = board.copy();

        System.out.println(player + "'s turn");

        while (true) {
            while (cancelled) {
                cancelled = false;
                chosen = chooseAPieceToMove(player);
                System.out.println(chosen.getClass().toString());
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
        int file = -1;
        int rank = -1;

        while (input.equals("")) {
            System.out.println("Please write coordinates in the form fileRank of a piece to move");
            input = reader.nextLine();

            if (!inputIsInAllowedForm(input, false)) {
                input = "";
                continue;
            }

            file = Character.getNumericValue(input.charAt(0));
            rank = Character.getNumericValue(input.charAt(1));

            input = checkThatPlayerOwnsTargetedPiece(file, rank, player, input);

        }
        return board.getBoard()[file][rank].getPiece();
    }

    private String checkThatPlayerOwnsTargetedPiece(int file, int rank, Player player, String input) {
        if (board.getSquare(file, rank).getPiece().getOwner() != player) {
            System.out.println("Please, choose one of your own pieces.");
            input = "";
        }
        return input;
    }

    private Square chooseATargetSquareForMovement(List<Square> possibilities) {
        String input = "";
        int file = 0;
        int rank = 0;

        while (input.equals("")) {
            System.out.println("Please write coordinates in form FileRank of a square to move to or x to cancel");
            input = reader.nextLine();

            if (input.equals("x")) {
                cancelled = true;
                break;
            }

            if (!inputIsInAllowedForm(input, true)) {
                input = "";
                continue;
            }

            file = Character.getNumericValue(input.charAt(0));
            rank = Character.getNumericValue(input.charAt(1));

            input = checkThatMovementIsLegal(possibilities, file, rank, input);

        }

        return board.getSquare(file, rank);
    }

    private String checkThatMovementIsLegal(List<Square> possibilities, int file, int rank, String input) {
        if (!possibilities.contains(board.getSquare(file, rank))) {
            System.out.println("Please, choose one of the legal movements: ");
            for (Square possibility : possibilities) {
                System.out.print(possibility + " ");
            }
            input = "";
        }
        return input;
    }

    private boolean inputIsInAllowedForm(String input, boolean squareCanBeEmpty) {
        int file;
        int rank;

        if (input.length() != 2) {
            return false;
        }

        try {
            file = Character.getNumericValue(input.charAt(0));
            rank = Character.getNumericValue(input.charAt(1));
        } catch (Exception e) {
            return false;
        }

        if (!board.withinTable(file, rank)) {
            return false;
        }

        if (!squareCanBeEmpty && !board.getSquare(file, rank).containsAPiece()) {
            return false;
        }

        return true;
    }
}