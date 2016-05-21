package logic.pieces;

import java.util.ArrayList;
import java.util.List;
import logic.assistance.Player;
import logic.assistance.Square;

public class Pawn extends Piece {

    public Pawn(int file, int rank, Player owner) {
        super(file, rank, owner);
    }

    @Override
    public List<Square> possibleMoves(Square[][] board) {
        List<Square> moves = new ArrayList<>();
        Square target = board[location.getFile()][location.getRank() + owner.getDirection()];

        if (legalToMoveTo(target)) {
            moves.add(target);

            if (location.getRank() == 1 && owner == Player.WHITE) {
                target = board[location.getFile()][target.getRank() + owner.getDirection()];
                if (legalToMoveTo(target)) {
                    moves.add(target);
                }
            } else if (location.getRank() == 6 && owner == Player.BLACK) {
                target = board[location.getFile()][location.getRank() + owner.getDirection()];
                if (legalToMoveTo(target)) {
                    moves.add(target);
                }
            }
        }

        if (canTakeAnOpposingPiece(1, board)) {
            moves.add(board[location.getFile() + 1][location.getRank() + owner.getDirection()]);
        }

        if (canTakeAnOpposingPiece(-1, board)) {
            moves.add(board[location.getFile() - 1][location.getRank() + owner.getDirection()]);
        }
        return moves;
    }

    public boolean canTakeAnOpposingPiece(int direction, Square[][] board) {
        Square target = board[location.getFile() + direction][location.getRank() + owner.getDirection()];

        if (legalToMoveTo(target)) {
            if (target.getPiece() != null) {
                return true;
            }
        }

        return false;
    }
}
