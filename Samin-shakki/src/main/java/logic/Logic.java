package logic;

import java.util.List;
import logic.assistance.ChessBoard;
import logic.assistance.Square;
import logic.pieces.Piece;

/**
 *
 * @author sami
 */
public class Logic {
    
    ChessBoard board;
    
    public Logic() {
        board = new ChessBoard();
    }
    
    public List<Square> possibleMoves(Piece piece) {
        return piece.possibleMoves(board.getBoard());
    }
}
