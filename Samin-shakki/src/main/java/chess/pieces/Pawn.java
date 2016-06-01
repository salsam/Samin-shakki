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
    public Piece clone(Square location) {
        return new Pawn(location, this.owner);
    }

    @Override
    public List<Square> threatenedSquares(ChessBoard board) {
        List<Square> squares = new ArrayList();
        int[] fileChange = new int[]{1, -1};
        int file = this.location.getFile();
        int rank = this.location.getRank() + this.owner.getDirection();

        for (int i = 0; i < 2; i++) {
            if (board.withinTable(file + fileChange[i], rank)) {
                squares.add(board.getSquare(file + fileChange[i], rank));
            }
        }

        return squares;
    }

    @Override
    public List<Square> possibleMoves(ChessBoard board) {
        List<Square> moves = new ArrayList<>();
        int newRank = location.getRank() + owner.getDirection();

        addSquareIfWithinTableAndEmpty(board, newRank, moves);

        if (firstMovement()) {
            newRank += owner.getDirection();
            addSquareIfWithinTableAndEmpty(board, newRank, moves);
        }

        addPossibilitiesToTakeOpposingPieces(board, moves);

        return moves;
    }

    private void addPossibilitiesToTakeOpposingPieces(ChessBoard board, List<Square> moves) {
        threatenedSquares(board).stream().filter(i -> legalToMoveTo(i, board))
                .filter(i -> i.containsAPiece())
                .forEach(i -> moves.add(i));
    }

    private void addSquareIfWithinTableAndEmpty(ChessBoard board, int newRank, List<Square> moves) {
        Square target;
        if (board.withinTable(location.getFile(), newRank)) {
            target = board.getSquare(location.getFile(), newRank);

            if (!target.containsAPiece()) {
                moves.add(target);
            }
        }
    }

    private boolean firstMovement() {
        if (owner == Player.WHITE) {
            return location.getRank() == 1;
        }
        return location.getRank() == 6;
    }
}
