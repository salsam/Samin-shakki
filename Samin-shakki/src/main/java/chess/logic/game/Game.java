package chess.logic.game;

import chess.logic.board.ChessBoard;
import chess.logic.board.Square;
import chess.logic.board.ChessBoardInitializer;
import chess.logic.board.Player;
import static chess.logic.board.Player.getOpponent;
import chess.logic.pieces.King;
import chess.logic.pieces.Pawn;
import chess.logic.pieces.Piece;

/**
 * This class is responsible for keeping track of current situation on the
 * chessboard. Class offers methods to check legal moves of all pieces on board
 * and all squares threatened by players. Class also offers methods to check if
 * game has ended and whether or not one player is checked.
 *
 * @author sami
 */
public class Game {

    private ChessBoard board;
    private int turn;
    private LegalityChecker checker;

    public Game(ChessBoardInitializer init, MovementLogic movementLogic) {
        this.board = new ChessBoard(movementLogic);
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

    public LegalityChecker getChecker() {
        return checker;
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
        King playersKing = board.getKings().get(player);
        board.updateThreatenedSquares(getOpponent(player));
        return board.threatenedSquares(getOpponent(player)).contains(board.getSquare(playersKing.getColumn(), playersKing.getRow()));
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
            for (Square possibility : board.getMovementLogic().possibleMoves(piece, board)) {
                board.getMovementLogic().move(piece, possibility, board);
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
        for (Piece piece : board.getPieces(player)) {
            if (!board.getMovementLogic().possibleMoves(piece, board).isEmpty()) {
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
            if (piece.getClass() == Pawn.class) {
                Pawn pawn = (Pawn) piece;
                pawn.setMovedTwoSquaresLastTurn(false);
            }
        });
    }

}
