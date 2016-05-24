package chess.board;

import chess.pieces.Piece;

/**
 *
 * @author samisalo
 */
public abstract class ChessBoardInitializer {

    public abstract void initialize(ChessBoard board);

    protected void clearBoard(ChessBoard board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.getBoard()[i][j].setPiece(null);
            }
        }
    }

    public void putPieceOnBoard(ChessBoard board, Piece piece) {
        board.getBoard()[piece.getLocation().getFile()][piece.getLocation().getRank()].setPiece(piece);
        board.getPieces().add(piece);
        if (piece.getOwner() == Player.WHITE) {
            board.getWhitePieces().add(piece);
        } else {
            board.getBlackPieces().add(piece);
        }
    }
}
