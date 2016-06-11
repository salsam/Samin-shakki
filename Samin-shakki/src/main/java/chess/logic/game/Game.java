package chess.logic.game;

import chess.logic.board.ChessBoard;
import chess.logic.board.Square;
import chess.logic.board.ChessBoardInitializer;
import chess.logic.board.Player;
import static chess.logic.board.Player.getOpponent;
import chess.logic.pieces.King;
import chess.logic.pieces.Piece;
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
     * counter in field turn. Thus changing the player whose turn is now.
     */
    public void nextTurn() {
        board.updateThreatenedSquares(whoseTurn());
        turn++;
    }

    /**
     * Updates set containing all squares that opponent threatens and then
     * checks if player's king is on one of those.
     *
     * @param player
     * @return true if player's king is threatened by opposing piece
     */
    public boolean checkIfChecked(Player player) {
        Map<Player, King> kings = board.getKings();
        board.updateThreatenedSquares(getOpponent(player));
        return board.threatenedSquares(getOpponent(player)).contains(kings.get(player).getLocation());
    }

    /**
     * Checks if player owns a piece on square that corresponds given column and
     * row.
     *
     * @see LegalityChecker.checkPlayerOwnsAPieceOnTargetSquare()
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
        for (Piece piece : board.getPieces(player)) {
            for (Square possibility : piece.possibleMoves(board)) {
                piece.move(possibility, board);
                board.updateThreatenedSquares(getOpponent(player));
                if (!checkIfChecked(player)) {
                    setChessBoard(backUp.copy());
                    return false;
                }
                setChessBoard(backUp.copy());
            }
        }

        return true;
    }

}
