package chess.logic.pieces;

import chess.logic.board.ChessBoardLogic;
import java.util.ArrayList;
import java.util.List;
import chess.logic.board.Player;
import chess.logic.board.Square;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pawn extends Piece {

    private boolean movedTwoSquaresLastTurn;

    public Pawn(Square square, Player owner) {
        super(square, owner);
        movedTwoSquaresLastTurn = false;
        try {
            if (owner == Player.BLACK) {
                this.picture = ImageIO.read(new File("/home/sami/Samin-shakki/Samin-shakki/src/main/resources/blackPawn1.png"));
            } else {
                this.picture = ImageIO.read(new File("/home/sami/Samin-shakki/Samin-shakki/src/main/resources/whitePawn1.png"));
            }
        } catch (IOException e) {
        }
    }

    /**
     * Returns a field to field copy of this piece.
     *
     * @param location location where the clone will be placed
     * @return deep copy of this pawn
     */
    @Override
    public Piece clone(Square location) {
        return new Pawn(location, this.owner);
    }

    @Override
    public void move(Square target, ChessBoardLogic board) {
        if (Math.abs(this.location.getrow() - target.getrow()) == 2) {
            movedTwoSquaresLastTurn = true;
        }
        super.move(target, board);
    }

    public boolean getMovedTwoSquaresLastTurn() {
        return movedTwoSquaresLastTurn;
    }

    public void setMovedTwoSquaresLastTurn(boolean movedTwoSquaresLastTurn) {
        this.movedTwoSquaresLastTurn = movedTwoSquaresLastTurn;
    }

    /**
     * Return a list containing all squares that this pawn threatens. En passant
     * is a special move in chess which only pawns can perform. It means that if
     * opposing pawn moves two squares to be next to your own pawn, on your next
     * turn your pawn can take it as if it had only moved one square.
     *
     * @param board board on which this pawn moves
     * @return list containing all squares this pawn threatens
     */
    @Override
    public List<Square> threatenedSquares(ChessBoardLogic board) {
        List<Square> squares = new ArrayList();
        int[] columnChange = new int[]{1, -1};
        int column = this.location.getcolumn();
        int row = this.location.getrow() + this.owner.getDirection();
        Square target;

        for (int i = 0; i < 2; i++) {
            if (board.withinTable(column + columnChange[i], row)) {
                target = board.getSquare(column + columnChange[i], row);
                squares.add(target);
            }
            enPassant(board, column, columnChange, i, squares);
        }

        return squares;
    }

    private void enPassant(ChessBoardLogic board, int column, int[] columnChange, int i, List<Square> squares) {
        Square target;
        if (board.withinTable(column + columnChange[i], this.location.getrow())) {
            target = board.getSquare(column + columnChange[i], this.location.getrow());

            if (target.containsAPiece() && this.owner != target.getPiece().getOwner()) {
                if (target.getPiece().getClass() == Pawn.class) {
                    Pawn opposingPawn = (Pawn) target.getPiece();
                    if (opposingPawn.getMovedTwoSquaresLastTurn()) {
                        squares.add(target);
                    }
                }
            }
        }
    }

    /**
     * Returns a list containing all squares this pawn can legally move to. That
     * means squares diagonally forward (to pawn's owner's direction) of this
     * pawn where is an opposing piece to be taken as well as square straight
     * forward if it doesn't contain a piece. If it's pawn's first movement,
     * then pawn can move up to two squares forward if there's no pieces of
     * either owner on the way.
     *
     * @param board chessboard on which movement happens
     * @return a list containing all squares this pawn can legally move to.
     */
    @Override
    public List<Square> possibleMoves(ChessBoardLogic board) {
        List<Square> moves = new ArrayList<>();
        int newrow = location.getrow() + owner.getDirection();

        addSquareIfWithinTableAndEmpty(board, newrow, moves);

        if (firstMovement()) {
            newrow += owner.getDirection();
            addSquareIfWithinTableAndEmpty(board, newrow, moves);
        }

        addPossibilitiesToTakeOpposingPieces(board, moves);

        return moves;
    }

    private void addPossibilitiesToTakeOpposingPieces(ChessBoardLogic board, List<Square> moves) {
        threatenedSquares(board).stream().filter(i -> legalToMoveTo(i, board))
                .filter(i -> i.containsAPiece())
                .forEach(i -> moves.add(i));
    }

    private void addSquareIfWithinTableAndEmpty(ChessBoardLogic board, int newrow, List<Square> moves) {
        Square target;
        if (board.withinTable(location.getcolumn(), newrow)) {
            target = board.getSquare(location.getcolumn(), newrow);

            if (!target.containsAPiece()) {
                moves.add(target);
            }
        }
    }

    private boolean firstMovement() {
        if (owner == Player.WHITE) {
            return location.getrow() == 1;
        }
        return location.getrow() == 6;
    }
}
