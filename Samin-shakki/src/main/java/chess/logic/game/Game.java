package chess.logic.game;

import chess.logic.board.ChessBoard;
import chess.logic.board.ChessBoardCopier;
import chess.logic.board.Square;
import chess.logic.board.chessboardinitializers.ChessBoardInitializer;
import chess.logic.board.Player;
import static chess.logic.board.Player.getOpponent;
import chess.logic.pieces.King;
import chess.logic.pieces.Pawn;
import chess.logic.pieces.Piece;

/**
 * This class is responsible for keeping track of current game situation. Class
 * offers methods to check if game has ended and whether or not one player is
 * checked. Class also offers methods to keep track of current turn and start
 * next turn.
 *
 * @author sami
 */
public class Game {

    private ChessBoard board;
    private ChessBoardInitializer init;
    private int turn;
    private LegalityChecker checker;
    private boolean continues;

    /**
     * Creates a new game with given movement logic and chessboard initializer.
     *
     * @param init chessboard initializer to be used for this game
     * @param movementLogic movement logic to be used for this game
     */
    public Game(ChessBoardInitializer init, MovementLogic movementLogic) {
        this.board = new ChessBoard(movementLogic);
        this.init = init;
        this.init.initialize(board);
        turn = 1;
        checker = new LegalityChecker(board);
        continues = true;
    }

    /**
     * Returns player whose turn is now.
     *
     * @return white if it is whites turn else black.
     */
    public Player whoseTurn() {
        if (turn % 2 == 1) {
            return Player.WHITE;
        } else {
            return Player.BLACK;
        }
    }

    public boolean getContinues() {
        return continues;
    }

    public void setContinues(boolean continues) {
        this.continues = continues;
    }

    public LegalityChecker getChecker() {
        return checker;
    }

    public ChessBoard getChessBoard() {
        return this.board;
    }

    /**
     * Sets the given chessBoard in the field board and updates LegalityChecker
     * to check that board instead of old board.
     *
     * @param chessBoard new chessboard
     */
    public void setChessBoard(ChessBoard chessBoard) {
        this.board = chessBoard;
        this.checker.setBoard(chessBoard);
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
     * @param player player being checked
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
        ChessBoard backUp = ChessBoardCopier.copy(board);
        for (Piece piece : board.getPieces(player)) {
            for (Square possibility : board.getMovementLogic().possibleMoves(piece, board)) {
                board.getMovementLogic().move(piece, possibility, board);
                board.updateThreatenedSquares(getOpponent(player));
                if (!checkIfChecked(player)) {
                    setChessBoard(ChessBoardCopier.copy(backUp));
                    return false;
                }
                setChessBoard(ChessBoardCopier.copy(backUp));
                board.updateThreatenedSquares(getOpponent(player));
            }
        }

        continues = false;
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

    /**
     * Resets the game situation to beginning of the game.
     */
    public void reset() {
        init.initialize(board);
        turn = 1;
        continues = true;
    }

}
