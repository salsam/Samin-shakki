package logic;

import java.util.List;
import chess.assistance.ChessBoard;
import chess.assistance.Square;
import chess.pieces.Piece;

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
