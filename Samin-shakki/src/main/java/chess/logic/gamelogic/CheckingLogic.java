package chess.logic.gamelogic;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCopier;
import chess.domain.board.Player;
import static chess.domain.board.Player.getOpponent;
import chess.domain.board.Square;
import chess.domain.pieces.King;
import chess.domain.pieces.Piece;

/**
 *
 * @author sami
 */
public class CheckingLogic {

    private ChessBoard board;

    public CheckingLogic(ChessBoard board) {
        this.board = board;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
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
            if (piece.isTaken()) {
                continue;
            }
            board.updateThreatenedSquares(getOpponent(player));
            for (Square possibility : board.getMovementLogic().possibleMoves(piece, board)) {
                board.getMovementLogic().move(piece, possibility, board);
                board.updateThreatenedSquares(getOpponent(player));
                if (!checkIfChecked(player)) {
                    board.makeFieldsEqualTo(backUp);
                    return false;
                }
                board.makeFieldsEqualTo(backUp);
            }
        }
        board.makeFieldsEqualTo(backUp);

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
}
