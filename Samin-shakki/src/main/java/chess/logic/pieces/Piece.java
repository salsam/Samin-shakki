package chess.logic.pieces;

import chess.logic.board.ChessBoard;
import java.util.List;
import chess.logic.board.Player;
import chess.logic.board.Square;
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

    /**
     * Returns a list of squares this piece can legally move to.
     *
     * @param board ChessBoard this piece moves on
     * @return list containing all squares this piece can legally move to
     */
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

    /**
     * Moves this piece to target location on the given board. If this piece
     * takes an opposing piece, that will be removed from its owner on board.
     *
     * @param target Square where this piece will be moved.
     * @param board Board on which this piece will be moved.
     */
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
