package chess.logic.game;

import chess.logic.board.ChessBoard;
import chess.logic.board.Player;
import chess.logic.board.Square;
import chess.logic.piecemovers.*;
import chess.logic.pieces.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for movement related logic. Class offers methods to
 * get possible moves and threatened squares of a Piece or Player on ChessBoard
 * given as parameter. Also offers method to move piece to target location on
 * chessboard.
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

    /**
     * Creates a new MovementLogic initializing mover-objects for each chess
     * piece-class.
     */
    public MovementLogic() {
        bishopMover = new BishopMover();
        kingMover = new KingMover();
        knightMover = new KnightMover();
        pawnMover = new PawnMover();
        queenMover = new QueenMover();
        rookMover = new RookMover();
    }

    /**
     * Uses corresponding mover to returns a set containing all squares given
     * piece threatens on given board.
     *
     * @param piece piece of which threatened squares are being checked
     * @param board board on which piece is placed
     * @return a set containing all squares given piece threatens on given board
     */
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

    /**
     * Uses corresponding mover to returns a set containing all squares given
     * piece can move to on given board.
     *
     * @param piece piece of which possible moves are being checked
     * @param board board on which piece is placed
     * @return a set containing all squares given piece can move to on given
     * board
     */
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

    /**
     * Uses corresponding PieceMover to move given piece to target square on
     * given board.
     *
     * @param piece piece to be moved
     * @param target square where piece will be moved to
     * @param board board on which movement happens
     */
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

    /**
     * Returns set containing all squares that given player's pieces threaten on
     * given board.
     *
     * @param player given player
     * @param board given chessboard
     * @return set containing all squares that given player's pieces threaten
     */
    public Set<Square> squaresThreatenedByPlayer(Player player, ChessBoard board) {
        Set<Square> threatenedSquares = new HashSet();
        board.getPieces(player).stream().forEach(piece -> {
            threatenedSquares.addAll(threatenedSquares(piece, board));
        });
        return threatenedSquares;
    }

    /**
     * Returns set containing all squares that given player's pieces can move to
     * on given board.
     *
     * @param player given player
     * @param board given chessboard
     * @return set containing all squares that given player's pieces can move to
     */
    public Set<Square> possibleMovesByPlayer(Player player, ChessBoard board) {
        Set<Square> possibleMoves = new HashSet();
        board.getPieces(player).stream().forEach(piece -> {
            possibleMoves.addAll(possibleMoves(piece, board));
        });
        return possibleMoves;
    }

}
