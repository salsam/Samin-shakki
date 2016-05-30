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
    
    abstract public List<Square> possibleMoves(ChessBoard board);
    
    public Player getOwner() {
        return this.owner;
    }
    
    public Square getLocation() {
        return this.location;
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
    
    protected List<Square> possibilities(int[] fileChange, int[] rankChange, ChessBoard board) {
        int newFile;
        int newRank;
        Square target;
        List<Square> possibilities = new ArrayList();
        
        for (int i = 0; i < 8; i++) {
            newFile = location.getFile() + fileChange[i];
            newRank = location.getRank() + rankChange[i];
            
            if (!board.withinTable(newFile, newRank)) {
                continue;
            }
            
            target = board.getSquare(newFile, newRank);
            
            if (legalToMoveTo(target, board)) {
                possibilities.add(target);
            }
        }
        
        return possibilities;
    }
    
    private boolean isThreatened(Square target, ChessBoard board, Player player) {
        if (player == Player.WHITE) {
            return board.getBlackPossibleMoves().contains(target);
        } else {
            return board.getWhitePossibleMoves().contains(target);
        }
    }
    
    protected boolean legalToMoveTo(Square target, ChessBoard board) {
        
        if (!board.withinTable(target.getFile(), target.getRank())) {
            return false;
        }
        
        if (!target.containsAPiece()) {
            return true;
        }
        
        if (this.getClass() == King.class && isThreatened(target, board, owner)) {
            return false;
        }
        
        return owner != target.getPiece().owner;
    }
    
    public void move(Square target, ChessBoard board) {
        this.location.setPiece(null);
        
        if (target.containsAPiece()) {
            board.removePiece(target.getPiece());
        }
        target.setPiece(this);
        
        this.location = target;
    }
    
    protected void possibilitiesToDirection(Square current, ChessBoard board, List<Square> possibilities, int fileChange, int rankChange) {
        int newFile = current.getFile() + fileChange;
        int newRank = current.getRank() + rankChange;
        if (board.withinTable(newFile, newRank)) {
            Square target = board.getSquare(newFile, newRank);
            
            while (legalToMoveTo(target, board)) {
                possibilities.add(target);
                if (target.containsAPiece()) {
                    break;
                }
                newFile = target.getFile() + fileChange;
                newRank = target.getRank() + rankChange;
                if (board.withinTable(newFile, newRank)) {
                    target = board.getSquare(newFile, newRank);
                } else {
                    break;
                }
            }
        }
    }
    
    protected void addVerticalPossibilities(Square current, ChessBoard board, List<Square> possibilities) {
        possibilitiesToDirection(current, board, possibilities, 1, 0);
        possibilitiesToDirection(current, board, possibilities, -1, 0);
    }
}
