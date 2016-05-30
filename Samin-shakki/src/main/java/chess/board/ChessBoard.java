package chess.board;

import chess.pieces.Piece;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sami
 */
public class ChessBoard {

    private Square[][] board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    private Set<Square> blackPossibleMoves;
    private Set<Square> whitePossibleMoves;

    public ChessBoard() {
        this.board = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new Square(i, j);
            }
        }
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
        this.blackPossibleMoves = new HashSet();
        this.whitePossibleMoves = new HashSet();
    }

    public Set<Square> getBlackPossibleMoves() {
        return blackPossibleMoves;
    }

    public Square[][] getBoard() {
        return board;
    }

    public void setBoard(Square[][] newBoard) {
        this.board = newBoard;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public Set<Square> getWhitePossibleMoves() {
        return whitePossibleMoves;
    }

    public void setWhitePieces(List<Piece> whitePieces) {
        this.whitePieces = whitePieces;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public void setBlackPieces(List<Piece> blackPieces) {
        this.blackPieces = blackPieces;
    }

    public Square getSquare(int file, int rank) {
        return board[file][rank];
    }

    public boolean withinTable(int file, int rank) {
        if (file < 0 || file >= board.length) {
            return false;
        }
        if (rank < 0 || rank >= board[0].length) {
            return false;
        }
        return true;
    }

    public Set<Square> blackPossibleMoves() {
        Set<Square> set = new HashSet();

        blackPieces.stream().forEach((blackPiece) -> {
            set.addAll(blackPiece.possibleMoves(this));
        });

        return set;
    }

    public Set<Square> whitePossibleMoves() {
        Set<Square> set = new HashSet();

        whitePieces.stream().forEach((whitePiece) -> {
            set.addAll(whitePiece.possibleMoves(this));
        });

        return set;
    }

    public Set<Square> possibleMoves(Player player) {
        if (player == Player.WHITE) {
            return whitePossibleMoves();
        } else {
            return blackPossibleMoves();
        }
    }

    public ChessBoard copy() {
        ChessBoard copy = new ChessBoard();
        copy.setBoard(board);
        copy.setBlackPieces(blackPieces);
        copy.setWhitePieces(whitePieces);
        return copy;
    }

    public void removePiece(Piece piece) {
        if (piece.getOwner() == Player.WHITE) {
            whitePieces.remove(piece);
        } else {
            blackPieces.remove(piece);
        }
    }

}
