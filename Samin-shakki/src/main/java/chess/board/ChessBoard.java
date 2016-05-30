package chess.board;

import chess.pieces.Piece;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sami
 */
public class ChessBoard {

    private Square[][] board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    private Set<Square> blackThreatenedSquares;
    private Set<Square> whiteThreatenedSquares;

    public ChessBoard() {
        this.board = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new Square(i, j);
            }
        }
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
        this.blackThreatenedSquares = new HashSet();
        this.whiteThreatenedSquares = new HashSet();
    }

    public Set<Square> getBlackThreatenedSquares() {
        return blackThreatenedSquares;
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

    public Set<Square> getWhiteThreatenedSquares() {
        return whiteThreatenedSquares;
    }

    public void updateThreatenedSquares(Player player) {
        if (player == Player.WHITE) {
            whiteThreatenedSquares = whiteThreatenedSquares();
        } else {
            blackThreatenedSquares = blackThreatenedSquares();
        }
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

    public Set<Square> blackThreatenedSquares() {
        Set<Square> set = new HashSet();

        blackPieces.stream().forEach((blackPiece) -> {
            set.addAll(blackPiece.possibleMoves(this));
        });

        return set;
    }

    public Set<Square> whiteThreatenedSquares() {
        Set<Square> set = new HashSet();

        whitePieces.stream().forEach((whitePiece) -> {
            set.addAll(whitePiece.possibleMoves(this));
        });

        return set;
    }

    public Set<Square> threatenedSquares(Player player) {
        if (player == Player.WHITE) {
            return whiteThreatenedSquares;
        } else {
            return blackThreatenedSquares;
        }
    }

    public ChessBoard copy() throws CloneNotSupportedException {
        ChessBoard copy = new ChessBoard();
        Square[][] copyBoard = new Square[board.length][board[0].length];
        System.arraycopy(board, 0, copyBoard, 0, board.length);
        copy.setBoard(copyBoard);
        copy.setBlackPieces(blackPiecesCopy(copy));
        copy.setWhitePieces(whitePiecesCopy(copy));
        return copy;
    }

    private List<Piece> whitePiecesCopy(ChessBoard board) {
        List<Piece> copyWhitePieces = new ArrayList();
        whitePieces.stream().forEach(i -> {
            try {
                copyWhitePieces.add(i.clone(board));
            } catch (CloneNotSupportedException ex) {
            }
        });
        return copyWhitePieces;
    }

    private List<Piece> blackPiecesCopy(ChessBoard board) {
        List<Piece> bpc = new ArrayList();

        blackPieces.stream().forEach(i -> {
            try {
                bpc.add(i.clone(board));
            } catch (CloneNotSupportedException ex) {
            }
        });

        return bpc;
    }

    public void removePiece(Piece piece) {
        if (piece.getOwner() == Player.WHITE) {
            whitePieces.remove(piece);
        } else {
            blackPieces.remove(piece);
        }
    }

}
