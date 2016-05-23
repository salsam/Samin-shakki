package chess.board;

/**
 *
 * @author samisalo
 */
public class emptyBoardInitializer extends chessBoardInitializer {

    @Override
    public void initialize(ChessBoard board) {
        clearBoard(board);
    }

}
