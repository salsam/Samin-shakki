package chess.pieces;

import chess.board.ChessBoard;
import java.util.List;
import chess.board.Player;
import chess.board.Square;

public abstract class Piece {

    protected Square location;
    protected Player owner;

    public Piece(int file, int rank, Player owner) {
        this.location = new Square(file, rank);
        this.owner = owner;
    }

    abstract public List<Square> possibleMoves(ChessBoard board);

    public Player getOwner() {
        return this.owner;
    }

    public Square getLocation() {
        return this.location;
    }

    public boolean legalToMoveTo(Square target) {

        if (target.getFile() < 0 || target.getFile() > 7) {
            return false;
        }

        if (target.getRank() < 0 || target.getFile() > 7) {
            return false;
        }

        if (!target.containsAPiece()) {
            return true;
        }

        return owner != target.getPiece().owner;
    }

    public void move(Square target) {
        location.setPiece(null);
        target.setPiece(this);

        location = target;
    }

    protected void possibilitiesToDirection(Square current, ChessBoard board, List<Square> possibilities, int fileChange, int rankChange) {
        Square target = current;

        while (legalToMoveTo(target)) {
            if (target.containsAPiece()) {
                break;
            }
            target = board.getBoard()[target.getFile() + fileChange][target.getRank() + rankChange];
            possibilities.add(target);
            if (target.containsAPiece()) {
                break;
            }
        }
    }

    protected void diagonalPossibilities(Square current, ChessBoard board, List<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 1, 1);
        possibilitiesToDirection(current, board, possibilities, 1, -1);
        possibilitiesToDirection(current, board, possibilities, -1, 1);
        possibilitiesToDirection(current, board, possibilities, -1, -1);
    }

    protected void horizontalPossibilities(Square current, ChessBoard board, List<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 0, 1);
        possibilitiesToDirection(current, board, possibilities, 0, -1);
    }

    protected void verticalPossibilities(Square current, ChessBoard board, List<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 1, 0);
        possibilitiesToDirection(current, board, possibilities, -1, 0);
    }

}
