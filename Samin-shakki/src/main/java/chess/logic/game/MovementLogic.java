package chess.logic.game;

import chess.logic.board.ChessBoard;
import chess.logic.board.Square;
import chess.logic.piecemovers.*;
import chess.logic.pieces.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for movement related logic. Class offers methods to
 * get possible moves and threatened squares of a Piece on ChessBoard given as
 * parameter. Also offers method to move piece to target location on chessboard.
 *
 * @author sami
 */
public class MovementLogic {

    private BishopMover bishopMover;
    private KingMover kingMover;
    private KnightMover knightMover;
    private PawnMover pawnMover;
    private QueenMover queenMover;
    private RookMover rookMover;

    public MovementLogic() {
        bishopMover = new BishopMover();
        kingMover = new KingMover();
        knightMover = new KnightMover();
        pawnMover = new PawnMover();
        queenMover = new QueenMover();
        rookMover = new RookMover();
    }

    public Set<Square> threatenedSquares(Piece piece, ChessBoard board) {
        if (piece.getClass() == Bishop.class) {
            return bishopMover.threatenedSquares(piece, board);
        } else if (piece.getClass() == King.class) {
            return kingMover.threatenedSquares(piece, board);
        } else if (piece.getClass() == Knight.class) {
            return knightMover.threatenedSquares(piece, board);
        } else if (piece.getClass() == Pawn.class) {
            return pawnMover.threatenedSquares(piece, board);
        } else if (piece.getClass() == Queen.class) {
            return queenMover.threatenedSquares(piece, board);
        } else if (piece.getClass() == Rook.class) {
            return rookMover.threatenedSquares(piece, board);
        }
        return new HashSet<>();
    }

    public Set<Square> possibleMoves(Piece piece, ChessBoard board) {
        if (piece.getClass() == Bishop.class) {
            return bishopMover.possibleMoves(piece, board);
        } else if (piece.getClass() == King.class) {
            return kingMover.possibleMoves(piece, board);
        } else if (piece.getClass() == Knight.class) {
            return knightMover.possibleMoves(piece, board);
        } else if (piece.getClass() == Pawn.class) {
            return pawnMover.possibleMoves(piece, board);
        } else if (piece.getClass() == Queen.class) {
            return queenMover.possibleMoves(piece, board);
        } else if (piece.getClass() == Rook.class) {
            return rookMover.possibleMoves(piece, board);
        }
        return new HashSet<>();
    }

    public void move(Piece piece, Square target, ChessBoard board) {
        if (piece.getClass() == Bishop.class) {
            bishopMover.move(piece, target, board);
        } else if (piece.getClass() == King.class) {
            kingMover.move(piece, target, board);
        } else if (piece.getClass() == Knight.class) {
            knightMover.move(piece, target, board);
        } else if (piece.getClass() == Pawn.class) {
            pawnMover.move(piece, target, board);
        } else if (piece.getClass() == Queen.class) {
            queenMover.move(piece, target, board);
        } else if (piece.getClass() == Rook.class) {
            rookMover.move(piece, target, board);
        }
    }

}
