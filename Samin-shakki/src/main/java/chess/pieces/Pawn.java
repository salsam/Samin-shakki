package chess.pieces;

import chess.board.ChessBoard;
import java.util.ArrayList;
import java.util.List;
import chess.board.Player;
import chess.board.Square;

public class Pawn extends Piece {
    
    public Pawn(int file, int rank, Player owner) {
        super(file, rank, owner);
    }
    
    @Override
    public List<Square> possibleMoves(ChessBoard board) {
        List<Square> moves = new ArrayList<>();
        Square target = board.getBoard()[location.getFile()][location.getRank() + owner.getDirection()];
        
        if (legalToMoveTo(target)) {
            moves.add(target);
            
            if (firstMovement(board)) {
                target = board.getBoard()[location.getFile()][target.getRank() + owner.getDirection()];
                if (legalToMoveTo(target)) {
                    moves.add(target);
                }
            }
        }
        
        if (canTakeAnOpposingPiece(1, board)) {
            moves.add(board.getBoard()[location.getFile() + 1][location.getRank() + owner.getDirection()]);
        }
        
        if (canTakeAnOpposingPiece(-1, board)) {
            moves.add(board.getBoard()[location.getFile() - 1][location.getRank() + owner.getDirection()]);
        }
        return moves;
    }
    
    public boolean canTakeAnOpposingPiece(int direction, ChessBoard board) {
        Square target = board.getBoard()[location.getFile() + direction][location.getRank() + owner.getDirection()];
        
        if (legalToMoveTo(target)) {
            if (target.getPiece() != null) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean firstMovement(ChessBoard board) {
        if (owner == Player.WHITE) {
            return location.getRank() == 1;
        }
        return location.getRank() == 6;
    }
}
