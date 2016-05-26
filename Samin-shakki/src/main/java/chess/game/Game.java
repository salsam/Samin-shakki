package chess.game;

import java.util.List;
import chess.board.ChessBoard;
import chess.board.Square;
import chess.board.ChessBoardInitializer;
import chess.board.Player;
import chess.gui.ChessNotationTableDrawer;
import chess.pieces.Piece;
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

            if (board.getBlackPieces().isEmpty() || board.getWhitePieces().isEmpty()) {
                continues = false;
            }
            turn++;
        }
    }

    private List<Square> possibleMoves(Piece piece) {
        return piece.possibleMoves(board);
    }

    private void turn(Player player) {
        Piece chosen = null;
        Square target = null;
        List<Square> possibleMoves;
        graphics.draw(board);
        cancelled = true;

        System.out.println(player + "'s turn");

        while (cancelled) {
            cancelled = false;
            chosen = chooseAPieceToMove(player);
            System.out.println(chosen.getClass().toString());
            possibleMoves = possibleMoves(chosen);
            target = chooseATargetSquareForMovement(possibleMoves);
        }

        chosen.move(target);
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
        int file = -1;
        int rank = -1;

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
