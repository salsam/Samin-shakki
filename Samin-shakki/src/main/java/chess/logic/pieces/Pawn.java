package chess.logic.pieces;

import chess.gui.io.ImageLoader;
import chess.logic.board.ChessBoard;
import static chess.logic.board.ChessBoardInitializer.removePieceFromOwner;
import java.util.HashSet;
import java.util.Set;
import chess.logic.board.Player;
import chess.logic.board.Square;

public class Pawn extends Piece {

    private boolean movedTwoSquaresLastTurn;

    public Pawn(Square square, Player owner) {
        super(square, owner);
        movedTwoSquaresLastTurn = false;

        if (owner == Player.BLACK) {
            this.picture = ImageLoader.getImage("blackPawn1.png");
        } else {
            this.picture = ImageLoader.getImage("whitePawn1.png");
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

    /**
     * This method moves pawns on board to target square. If pawn moves two
     * squares that is saved to field movedTwoSquaresLastTurn and thus this pawn
     * will be enpassantable on opponent's next turn. Also if movement is
     * enpassant, piece in the square one step back from target will be removed.
     * Enpassant is spotted from target square being empty and in different
     * column as moving pawn.
     *
     * @param target square that pawn is moving to.
     * @param board ChessBoard on which movement happens.
     */
    @Override
    public void move(Square target, ChessBoard board) {
        if (Math.abs(this.location.getRow() - target.getRow()) == 2) {
            movedTwoSquaresLastTurn = true;
        }

        if (!target.containsAPiece() && target.getColumn() != this.location.getColumn()) {
            Square enpassanted = board.getSquare(target.getColumn(), target.getRow() - this.owner.getDirection());
            Piece piece = enpassanted.getPiece();
            removePieceFromOwner(piece, board);
            enpassanted.setPiece(null);
        }

        super.move(target, board);
    }

    /**
     * Returns whether or not this pawn moved two squares on owner's last turn.
     *
     * @return true if this pawn moved two squares last turn.
     */
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
    public Set<Square> threatenedSquares(ChessBoard board) {
        Set<Square> squares = new HashSet();
        int[] columnChange = new int[]{1, -1};
        int column = this.location.getColumn();
        int row = this.location.getRow() + this.owner.getDirection();

        for (int i = 0; i < 2; i++) {
            if (board.withinTable(column + columnChange[i], row)) {
                Square target = board.getSquare(column + columnChange[i], row);
                squares.add(target);
            }
        }

        addPossibleEnPassant(board, squares);

        return squares;
    }

    private void addPossibleEnPassant(ChessBoard board, Set<Square> squares) {
        Square target;
        int[] columnChange = new int[]{1, -1};

        for (int i = 0; i < 2; i++) {
            if (board.withinTable(this.location.getColumn() + columnChange[i], this.location.getRow())) {
                target = board.getSquare(this.location.getColumn() + columnChange[i], this.location.getRow());

                if (targetContainsAnEnemyPawn(target)) {
                    Pawn opposingPawn = (Pawn) target.getPiece();
                    if (opposingPawn.getMovedTwoSquaresLastTurn()) {
                        squares.add(board.getSquare(target.getColumn(), target.getRow() + this.owner.getDirection()));
                    }
                }
            }
        }
    }

    private boolean targetContainsAnEnemyPawn(Square target) {
        if (!target.containsAPiece()) {
            return false;
        }

        if (target.getPiece().getOwner() == owner) {
            return false;
        }

        return target.getPiece().getClass() == Pawn.class;
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
    public Set<Square> possibleMoves(ChessBoard board) {
        Set<Square> moves = new HashSet<>();
        int newrow = location.getRow() + owner.getDirection();

        addSquareIfWithinTableAndEmpty(board, newrow, moves);

        if (firstMovement()) {
            newrow += owner.getDirection();
            addSquareIfWithinTableAndEmpty(board, newrow, moves);
        }

        addPossibilitiesToTakeOpposingPieces(board, moves);

        return moves;
    }

    private void addPossibilitiesToTakeOpposingPieces(ChessBoard board, Set<Square> moves) {
        threatenedSquares(board).stream().filter(i -> legalToMoveTo(i, board))
                .filter(i -> i.containsAPiece())
                .forEach(i -> moves.add(i));
        addPossibleEnPassant(board, moves);
    }

    private void addSquareIfWithinTableAndEmpty(ChessBoard board, int newrow, Set<Square> moves) {
        Square target;
        if (board.withinTable(location.getColumn(), newrow)) {
            target = board.getSquare(location.getColumn(), newrow);

            if (!target.containsAPiece()) {
                moves.add(target);
            }
        }
    }

    private boolean firstMovement() {
        if (owner == Player.WHITE) {
            return location.getRow() == 1;
        }
        return location.getRow() == 6;
    }
}
