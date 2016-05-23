package chess.pieces;

import chess.board.ChessBoard;
import java.util.List;
import chess.board.Player;
import chess.board.Square;

public abstract class Piece {

    protected Square location;
    protected Player owner;

    public Piece(Square square, Player owner) {
        this.location = square;
        this.owner = owner;
    }

    abstract public List<Square> possibleMoves(ChessBoard board);

    public Player getOwner() {
        return this.owner;
    }

    public Square getLocation() {
        return this.location;
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

    public boolean legalToMoveTo(Square target) {

        if (!withinTable(target.getFile(), target.getRank())) {
            return false;
        }

        if (!target.containsAPiece()) {
            return true;
        }

        return owner != target.getPiece().owner;
    }

    public void move(Square target) {
        this.location.setPiece(null);
        target.setPiece(this);

        this.location = target;
    }

    protected void possibilitiesToDirection(Square current, ChessBoard board, List<Square> possibilities, int fileChange, int rankChange) {
        int newFile = current.getFile() + fileChange;
        int newRank = current.getRank() + rankChange;
        if (withinTable(newFile, newRank)) {
            Square target = board.getBoard()[newFile][newRank];

            while (legalToMoveTo(target)) {
                possibilities.add(target);
                if (target.containsAPiece()) {
                    break;
                }
                newFile = target.getFile() + fileChange;
                newRank = target.getRank() + rankChange;
                if (withinTable(newFile, newRank)) {
                    target = board.getBoard()[newFile][newRank];
                } else {
                    break;
                }
            }
        }
    }

    protected void verticalPossibilities(Square current, ChessBoard board, List<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 1, 0);
        possibilitiesToDirection(current, board, possibilities, -1, 0);
    }

    protected boolean withinTable(int file, int rank) {
        if (file < 0 || file > 7) {
            return false;
        }
        if (rank < 0 || rank > 7) {
            return false;
        }
        return true;
    }

}
