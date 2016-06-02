package chess.logic.board;

/**
 *
 * @author samisalo
 */
public class EmptyBoardInitializer extends ChessBoardInitializer {

    @Override
    public void initialize(ChessBoard board) {
        clearBoard(board);
    }

}
