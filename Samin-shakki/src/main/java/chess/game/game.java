package chess.game;

import java.util.List;
import chess.board.ChessBoard;
import chess.board.Square;
import chess.board.chessBoardInitializer;
import chess.pieces.Piece;

/**
 *
 * @author sami
 */
public class game {

    ChessBoard board;
    chessBoardInitializer initializer;

    public game(chessBoardInitializer init) {
        this.board = new ChessBoard();
        this.initializer = init;
        init.initialize(board);
    }

    public List<Square> possibleMoves(Piece piece) {
        return piece.possibleMoves(board);
    }
}
