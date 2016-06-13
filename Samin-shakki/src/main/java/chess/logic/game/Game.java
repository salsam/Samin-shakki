package chess.logic.game;

import chess.logic.board.ChessBoard;
import chess.logic.board.Square;
import chess.logic.board.ChessBoardInitializer;
import chess.logic.board.Player;
import static chess.logic.board.Player.getOpponent;
import chess.logic.piecemovers.KingMover;
import chess.logic.piecemovers.PawnMover;
import chess.logic.piecemovers.PieceMover;
import java.util.Map;

/**
 * This class is responsible for one game of chess with given starting
 * positions. Class offers methods to check legal moves of all pieces on board,
 * all squares all pieces threaten as well as knowledge of current situation in
 * the game.
 *
 * @author sami
 */
public class Game {

    private ChessBoard board;
    private int turn;
    private LegalityChecker checker;

    public Game(ChessBoardInitializer init) {
        this.board = new ChessBoard();
        init.initialise(board);
        turn = 1;
        checker = new LegalityChecker(board);
    }

    /**
     * Returns player whose turn is now.
     *
     * @return white it is whites turn else black.
     */
    public Player whoseTurn() {
        if (turn % 2 == 1) {
            return Player.WHITE;
        } else {
            return Player.BLACK;
        }
    }

    public ChessBoard getChessBoard() {
        return this.board;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.board = chessBoard;
        this.checker = new LegalityChecker(chessBoard);
    }

    /**
     * Updates the squares that current player threatens and adds 1 to turn
     * counter in field turn. Thus changing the player whose turn is now. After
     * turn has changed also makes new player's pawns no longer possible to
     * capture en passant as one turn has passed.
     */
    public void nextTurn() {
        board.updateThreatenedSquares(whoseTurn());
        turn++;
        makePawnsUnEnPassantable(whoseTurn());
    }

    /**
     * Updates set containing all squares that opponent threatens and then
     * checks if player's king is on one of those.
     *
     * @param player
     * @return true if player's king is threatened by opposing piece
     */
    public boolean checkIfChecked(Player player) {
        Map<Player, KingMover> kings = board.getKings();
        board.updateThreatenedSquares(getOpponent(player));
        return board.threatenedSquares(getOpponent(player)).contains(kings.get(player).getLocation());
    }

    /**
     * Checks if player owns a piece on square that corresponds given column and
     * row.
     *
     * @param player chosen player
     * @param column column of target square
     * @param row row of target square.
     * @return true if player owns a piece on square corresponding given column
     * and row.
     */
    public boolean checkPlayerOwnsAPieceOnTargetSquare(Player player, int column, int row) {
        return checker.checkPlayerOwnsAPieceOnTheTargetSquare(player, column, row);
    }

    /**
     * Checks whether or not player is checkmated in this game.
     *
     * @param player player who is possibly checkmated.
     * @return true if player is checkmated. Else false.
     */
    public boolean checkMate(Player player) {
        ChessBoard backUp = board.copy();
        for (PieceMover piece : board.getPieces(player)) {
            for (Square possibility : piece.possibleMoves(board)) {
                piece.move(possibility, board);
                board.updateThreatenedSquares(getOpponent(player));
                if (!checkIfChecked(player)) {
                    setChessBoard(backUp.copy());
                    return false;
                }
                setChessBoard(backUp.copy());
                board.updateThreatenedSquares(getOpponent(player));
            }
        }

        return true;
    }

    /**
     * Returns whether or not chosen player has any legal moves.
     *
     * @param player chosen player
     * @return true player has no legal moves otherwise false.
     */
    public boolean stalemate(Player player) {
        for (PieceMover piece : board.getPieces(player)) {
            if (!piece.possibleMoves(board).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Changes the field movedTwoSquaresLastTurn to false for every pawn player
     * owns thus making them no longer possible to be captured en passant.
     *
     * @param player player
     */
    public void makePawnsUnEnPassantable(Player player) {
        board.getPieces(player).stream().forEach(piece -> {
            if (piece.getClass() == PawnMover.class) {
                PawnMover pawn = (PawnMover) piece;
                pawn.setMovedTwoSquaresLastTurn(false);
            }
        });
    }

}
