package logic.pieces;

import java.util.ArrayList;
import java.util.List;
import logic.assistance.Player;
import logic.assistance.Square;

public class Pawn extends Piece {

    private boolean movedTwoStepsLastTime;

    public Pawn(int column, int row, Player owner) {
        super(column, row, owner);
    }

    @Override
    public List<Square> possibleMoves(Square[][] board) {
        List<Square> moves = new ArrayList<>();
        Square target = board[location.getColumn()][location.getRow() + owner.getDirection()];

        if (legalToMoveTo(target)) {
            moves.add(target);

            if (location.getRow() == 1 && owner == Player.WHITE) {
                target = board[location.getColumn()][target.getRow() + owner.getDirection()];
                if (legalToMoveTo(target)) {
                    moves.add(target);
                }
            } else if (location.getRow() == 6 && owner == Player.BLACK) {
                target = board[location.getColumn()][location.getRow() + owner.getDirection()];
                if (legalToMoveTo(target)) {
                    moves.add(target);
                }
            }
        }

        if (canTakeAnOpposingPiece(1, board)) {
            moves.add(board[location.getColumn() + 1][location.getRow() + owner.getDirection()]);
        }

        if (canTakeAnOpposingPiece(-1, board)) {
            moves.add(board[location.getColumn() - 1][location.getRow() + owner.getDirection()]);
        }
        return moves;
    }

    public boolean canTakeAnOpposingPiece(int direction, Square[][] board) {
        Square target = board[location.getColumn() + direction][location.getRow() + owner.getDirection()];

        if (legalToMoveTo(target)) {
            if (target.getPiece() != null && owner != target.getPiece().getOwner()) {
                return true;
            }
        }

        return false;
    }
}
