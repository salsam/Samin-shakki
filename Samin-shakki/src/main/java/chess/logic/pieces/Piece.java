package chess.logic.pieces;

import static chess.logic.board.ChessBoardInitializer.removePieceFromOwner;
import chess.logic.board.ChessBoardLogic;
import java.util.Set;
import chess.logic.board.Player;
import chess.logic.board.Square;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public abstract class Piece {

    protected Square location;
    protected Player owner;
    protected BufferedImage picture;

    public Piece(Square square, Player owner) {
        this.location = square;
        this.owner = owner;
    }

    public abstract Set<Square> threatenedSquares(ChessBoardLogic board);

    public abstract Piece clone(Square location);

    /**
     * Draws a picture of this piece. Picture depends on the class of chosen
     * piece as well as the owner of piece.
     *
     * @param graphics Graphics object used to draw the image.
     */
    public void draw(Graphics graphics) {
        int x = this.location.getcolumn() * 30;
        int y = this.location.getrow() * 30;
        graphics.drawImage(picture, x, y, 30, 30, null);
    }

    /**
     * Returns a list of squares this piece can legally move to.
     *
     * @param board ChessBoard this piece moves on
     * @return list containing all squares this piece can legally move to
     */
    public Set<Square> possibleMoves(ChessBoardLogic board) {
        Set<Square> moves = new HashSet();

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

    protected void addDiagonalPossibilities(Square current, ChessBoardLogic board, Set<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 1, 1);
        possibilitiesToDirection(current, board, possibilities, 1, -1);
        possibilitiesToDirection(current, board, possibilities, -1, 1);
        possibilitiesToDirection(current, board, possibilities, -1, -1);
    }

    protected void addHorizontalPossibilities(Square current, ChessBoardLogic board, Set<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 0, 1);
        possibilitiesToDirection(current, board, possibilities, 0, -1);
    }

    protected void addVerticalPossibilities(Square current, ChessBoardLogic board, Set<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 1, 0);
        possibilitiesToDirection(current, board, possibilities, -1, 0);
    }

    protected Set<Square> possibilities(int[] columnChange, int[] rowChange, ChessBoardLogic board) {
        Set<Square> possibilities = new HashSet();

        for (int i = 0; i < 8; i++) {
            int newcolumn = location.getcolumn() + columnChange[i];
            int newrow = location.getrow() + rowChange[i];

            if (!board.withinTable(newcolumn, newrow)) {
                continue;
            }

            Square target = board.getSquare(newcolumn, newrow);
            possibilities.add(target);
        }

        return possibilities;
    }

    protected boolean legalToMoveTo(Square target, ChessBoardLogic board) {

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
    public void move(Square target, ChessBoardLogic board) {
        this.location.setPiece(null);

        if (target.containsAPiece()) {
            removePieceFromOwner(target.getPiece(), board);
        }
        target.setPiece(this);

        this.location = target;
    }

    protected void possibilitiesToDirection(Square current, ChessBoardLogic board, Set<Square> possibilities, int columnChange, int rowChange) {
        int newcolumn = current.getcolumn() + columnChange;
        int newrow = current.getrow() + rowChange;

        while (board.withinTable(newcolumn, newrow)) {
            Square target = board.getSquare(newcolumn, newrow);
            possibilities.add(target);
            if (target.containsAPiece()) {
                break;
            }
            newcolumn = target.getcolumn() + columnChange;
            newrow = target.getrow() + rowChange;
        }
    }
}
