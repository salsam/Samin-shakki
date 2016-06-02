package chess.logic.board;

import chess.logic.pieces.Piece;

/**
 *
 * @author samisalo
 */
public abstract class ChessBoardInitializer {
    
    public abstract void initialize(ChessBoard board);
    
    protected void clearBoard(ChessBoard board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.getSquare(i, j).setPiece(null);
            }
        }
    }
    
    public void putPieceOnBoard(ChessBoard board, Piece piece) {
        if (board.withinTable(piece.getLocation().getFile(), piece.getLocation().getRank())) {
            
            board.getSquare(piece.getLocation().getFile(), piece.getLocation().getRank()).setPiece(piece);
            board.addPieceToOwner(piece.getLocation());
        }
    }
}
