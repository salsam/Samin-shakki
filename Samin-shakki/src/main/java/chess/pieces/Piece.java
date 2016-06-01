package chess.pieces;

import chess.board.ChessBoard;
import java.util.List;
import chess.board.Player;
import chess.board.Square;
import java.util.ArrayList;

public abstract class Piece {

    protected Square location;
    protected Player owner;

    public Piece(Square square, Player owner) {
        this.location = square;
        this.owner = owner;
    }

    abstract public List<Square> threatenedSquares(ChessBoard board);

    abstract public Piece clone(Square location);

    public List<Square> possibleMoves(ChessBoard board) {
        List<Square> moves = new ArrayList();

        threatenedSquares(board).stream()
                .filter((move) -> (legalToMoveTo(move, board)))
                .forEach((move) -> moves.add(move));

        return moves;
    }

    public Player getOwner() {
        return this.owner;
    }

    public Square getLocation() {
        return this.location;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Piece piece = (Piece) obj;

        if (piece.getLocation() != this.location) {
            return false;
        }

        return this.owner == piece.getOwner();
    }

    protected void addDiagonalPossibilities(Square current, ChessBoard board, List<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 1, 1);
        possibilitiesToDirection(current, board, possibilities, 1, -1);
        possibilitiesToDirection(current, board, possibilities, -1, 1);
        possibilitiesToDirection(current, board, possibilities, -1, -1);
    }

    protected void addHorizontalPossibilities(Square current, ChessBoard board, List<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 0, 1);
        possibilitiesToDirection(current, board, possibilities, 0, -1);
    }

    protected void addVerticalPossibilities(Square current, ChessBoard board, List<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 1, 0);
        possibilitiesToDirection(current, board, possibilities, -1, 0);
    }

    protected List<Square> possibilities(int[] fileChange, int[] rankChange, ChessBoard board) {
        List<Square> possibilities = new ArrayList();

        for (int i = 0; i < 8; i++) {
            int newFile = location.getFile() + fileChange[i];
            int newRank = location.getRank() + rankChange[i];

            if (!board.withinTable(newFile, newRank)) {
                continue;
            }

            Square target = board.getSquare(newFile, newRank);
            possibilities.add(target);
        }

        return possibilities;
    }

    protected boolean legalToMoveTo(Square target, ChessBoard board) {

        if (!target.containsAPiece()) {
            return true;
        }

        return owner != target.getPiece().owner;
    }

    public void move(Square target, ChessBoard board) {
        this.location.setPiece(null);

        if (target.containsAPiece()) {
            board.removePieceFromOwner(target.getPiece());
        }
        target.setPiece(this);

        this.location = target;
    }

    protected void possibilitiesToDirection(Square current, ChessBoard board, List<Square> possibilities, int fileChange, int rankChange) {
        int newFile = current.getFile() + fileChange;
        int newRank = current.getRank() + rankChange;

        while (board.withinTable(newFile, newRank)) {
            Square target = board.getSquare(newFile, newRank);
            possibilities.add(target);
            if (target.containsAPiece()) {
                break;
            }
            newFile = target.getFile() + fileChange;
            newRank = target.getRank() + rankChange;
        }
    }
}
