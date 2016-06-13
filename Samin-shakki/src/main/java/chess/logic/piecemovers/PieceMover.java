package chess.logic.piecemovers;

import static chess.logic.board.ChessBoardInitializer.removePieceFromOwner;
import chess.logic.board.ChessBoard;
import java.util.Set;
import chess.logic.board.Square;
import chess.pieces.Piece;
import java.util.HashSet;

public abstract class PieceMover {
    
    public abstract Set<Square> threatenedSquares(Piece piece, ChessBoard board);

    /**
     * Returns a list of squares this piece can legally move to.
     *
     * @param board ChessBoard this piece moves on
     * @return list containing all squares this piece can legally move to
     */
    public Set<Square> possibleMoves(Piece piece, ChessBoard board) {
        Set<Square> moves = new HashSet();
        
        threatenedSquares(piece, board).stream()
                .filter((move) -> (legalToMoveTo(piece, move, board)))
                .forEach((move) -> moves.add(move));
        
        return moves;
    }
    
    protected void addDiagonalPossibilities(Square current, ChessBoard board, Set<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 1, 1);
        possibilitiesToDirection(current, board, possibilities, 1, -1);
        possibilitiesToDirection(current, board, possibilities, -1, 1);
        possibilitiesToDirection(current, board, possibilities, -1, -1);
    }
    
    protected void addHorizontalPossibilities(Square current, ChessBoard board, Set<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 0, 1);
        possibilitiesToDirection(current, board, possibilities, 0, -1);
    }
    
    protected void addVerticalPossibilities(Square current, ChessBoard board, Set<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 1, 0);
        possibilitiesToDirection(current, board, possibilities, -1, 0);
    }
    
    protected Set<Square> possibilities(Square location, int[] columnChange, int[] rowChange, ChessBoard board) {
        Set<Square> possibilities = new HashSet();
        
        for (int i = 0; i < 8; i++) {
            int newcolumn = location.getColumn() + columnChange[i];
            int newrow = location.getRow() + rowChange[i];
            
            if (!board.withinTable(newcolumn, newrow)) {
                continue;
            }
            
            Square target = board.getSquare(newcolumn, newrow);
            possibilities.add(target);
        }
        
        return possibilities;
    }
    
    protected boolean legalToMoveTo(Piece piece, Square target, ChessBoard board) {
        
        if (!target.containsAPiece()) {
            return true;
        }
        
        return piece.getOwner() != target.getPiece().getOwner();
    }

    /**
     * Moves this piece to target location on the given board. If this piece
     * takes an opposing piece, that will be removed from its owner on board.
     *
     * @param target Square where this piece will be moved.
     * @param board Board on which this piece will be moved.
     */
    public void move(Piece piece, Square target, ChessBoard board) {
        board.getSquare(piece.getColumn(), piece.getRow()).setPiece(null);
        
        if (target.containsAPiece()) {
            removePieceFromOwner(target.getPiece(), board);
        }
        target.setPiece(piece);
        
        piece.setColumn(target.getColumn());
        piece.setRow(target.getRow());
    }
    
    private void possibilitiesToDirection(Square current, ChessBoard board, Set<Square> possibilities, int columnChange, int rowChange) {
        int newColumn = current.getColumn() + columnChange;
        int newRow = current.getRow() + rowChange;
        
        while (board.withinTable(newColumn, newRow)) {
            Square target = board.getSquare(newColumn, newRow);
            possibilities.add(target);
            
            if (target.containsAPiece()) {
                break;
            }
            newColumn = target.getColumn() + columnChange;
            newRow = target.getRow() + rowChange;
        }
    }
}
