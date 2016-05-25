package chess.pieces;

import chess.board.ChessBoard;
import java.util.ArrayList;
import java.util.List;
import chess.board.Player;
import chess.board.Square;

public class Pawn extends Piece {

    public Pawn(Square square, Player owner) {
        super(square, owner);
    }

    @Override
    public List<Square> possibleMoves(ChessBoard board) {
        List<Square> moves = new ArrayList<>();
        int newRank = location.getRank() + owner.getDirection();

        if (!board.withinTable(location.getFile(), newRank)) {
            return moves;
        }

        Square target = board.getSquare(location.getFile(), newRank);

        if (!target.containsAPiece()) {
            moves.add(target);

            if (firstMovement(board)) {
                target = board.getSquare(location.getFile(), target.getRank() + owner.getDirection());
                if (legalToMoveTo(target, board)) {
                    moves.add(target);
                }
            }
        }

        if (canTakeAnOpposingPiece(1, board)) {
            moves.add(board.getSquare(location.getFile() + 1, location.getRank() + owner.getDirection()));
        }

        if (canTakeAnOpposingPiece(-1, board)) {
            moves.add(board.getSquare(location.getFile() - 1, location.getRank() + owner.getDirection()));
        }
        return moves;
    }

    private boolean canTakeAnOpposingPiece(int direction, ChessBoard board) {
        int newFile = location.getFile() + direction;
        int newRank = location.getRank() + owner.getDirection();
        if (!board.withinTable(newFile, newRank)) {
            return false;
        }
        Square target = board.getSquare(newFile, newRank);

        if (legalToMoveTo(target, board)) {
            if (target.getPiece() != null) {
                return true;
            }
        }

        return false;
    }

    private boolean firstMovement(ChessBoard board) {
        if (owner == Player.WHITE) {
            return location.getRank() == 1;
        }
        return location.getRank() == 6;
    }
}
