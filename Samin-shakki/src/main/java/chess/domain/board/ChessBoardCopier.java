package chess.domain.board;

import static chess.logic.board.chessboardinitializers.ChessBoardInitializer.addPieceToOwner;
import java.util.ArrayList;

/**
 * This class is used to make copies of ChessBoards with method copy.
 *
 * @author sami
 */
public class ChessBoardCopier {

    /**
     * Creates a new ChessBoardCopier-object.
     */
    public ChessBoardCopier() {
    }

    /**
     * Returns a new ChessBoard that deeply equals the one given as parameter.
     *
     * @param board chessboard to be copied.
     * @return a deep copy of given ChessBoard.
     */
    public static ChessBoard copy(ChessBoard board) {
        ChessBoard copy = new ChessBoard(board.getMovementLogic());
        copy.setTable(copyTable(board.getTable()));
        setPieces(copy);
        
        return copy;
    }
    
    private static Square[][] copyTable(Square[][] table) {
        Square[][] copyTable = new Square[table.length][table[0].length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                copyTable[i][j] = table[i][j].clone();
            }
        }
        return copyTable;
    }
    
    private static void setPieces(ChessBoard board) {
        board.setBlackPieces(new ArrayList());
        board.setWhitePieces(new ArrayList());
        
        for (int i = 0; i < board.getTable().length; i++) {
            for (int j = 0; j < board.getTable()[0].length; j++) {
                addPieceToOwner(board.getSquare(i, j), board);
            }
        }
    }
}
