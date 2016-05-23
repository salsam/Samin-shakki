package chess.board;

/**
 *
 * @author samisalo
 */
public abstract class chessBoardInitializer {

    abstract void initialize(ChessBoard board);

    protected void clearBoard(ChessBoard board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.getBoard()[i][j].setPiece(null);
            }
        }
    }
}
