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
    private ChessBoardInitializer initializer;
    private int turn;
    private boolean continues;
    private Scanner lukija;
    private ChessNotationTableDrawer graphics;
    private boolean cancelled;

    public Game(ChessBoardInitializer init) {
        this.board = new ChessBoard();
        this.initializer = init;
        init.initialize(board);
        turn = 1;
        continues = true;
        lukija = new Scanner(System.in);
        graphics = new ChessNotationTableDrawer();
        cancelled = true;
    }

    public void start() {
        while (continues) {
            if (turn % 2 == 1) {
                turn(Player.WHITE);
            } else {
                turn(Player.BLACK);
            }

            if (turn == 50) {
                continues = false;
            }
        }
    }

    public List<Square> possibleMoves(Piece piece) {
        return piece.possibleMoves(board);
    }

    public void turn(Player player) {
        Piece chosen = null;
        Square target = null;
        List<Square> possibleMoves;
        graphics.draw(board);

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
        int file = 0;
        int rank = 0;
        while (input.equals("")) {
            System.out.println("Please write coordinates in the form fileRank of a piece to move");
            input = lukija.nextLine();

            if (!inputIsInAllowedForm(input, false)) {
                input = "";
                continue;
            }

            file = Character.getNumericValue(input.charAt(0));
            rank = Character.getNumericValue(input.charAt(1));

            if (board.getBoard()[file][rank].getPiece().getOwner() != player) {
                System.out.println("Please, choose one of your own pieces.");
                input = "";
                continue;
            }
            System.out.println("c");

        }
        return board.getBoard()[file][rank].getPiece();
    }

    private Square chooseATargetSquareForMovement(List<Square> possibilities) {
        String input = "";
        int file = 0;
        int rank = 0;
        while (input.equals("")) {
            System.out.println("Please write coordinates in form FileRank of a square to move to or x to cancel");
            input = lukija.nextLine();

            if (input.equals("x")) {
                cancelled = true;
                break;
            }

            if (!inputIsInAllowedForm(input, true)) {
                input = "";
                continue;
            }
            if (!possibilities.contains(board.getBoard()[file][rank])) {
                System.out.println("Please, choose one of the legal movements: ");
                for (Square possibility : possibilities) {
                    System.out.print(possibility + " ");
                }
                input = "";
                continue;
            }

        }
        return board.getBoard()[file][rank];
    }

    public boolean isBetween(int number, int start, int end) {
        if (number < start || number > end) {
            return false;
        }

        return true;
    }

    public boolean inputIsInAllowedForm(String input, boolean canBeEmpty) {
        int file;
        int rank;
        if (input.length() != 2) {
            return false;
        }
        System.out.println("d");

        try {
            file = Character.getNumericValue(input.charAt(0));
            rank = Character.getNumericValue(input.charAt(1));
        } catch (Exception e) {
            return false;
        }

        System.out.println("e");

        if (!isBetween(file, 0, 7) || !isBetween(rank, 0, 7)) {
            return false;
        }

        System.out.println("f");

        if (!canBeEmpty && !board.getBoard()[file][rank].containsAPiece()) {
            return false;
        }

        System.out.println("g");
        return true;
    }
}
