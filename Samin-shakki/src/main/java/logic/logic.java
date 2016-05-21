package logic;

import java.util.List;
import logic.assistance.ChessBoard;
import logic.assistance.Square;
import logic.pieces.Piece;

/**
 *
 * @author sami
 */
public class logic {
    
    ChessBoard board;
    
    public logic() {
        board = new ChessBoard();
    }
    
    public List<Square> possibleMoves(Piece piece) {
        return piece.possibleMoves(board.getBoard());
    }
}
