package chess.board;

import chess.pieces.King;
import chess.pieces.Piece;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author sami
 */
public class ChessBoard {

    private Square[][] board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    private Set<Square> squaresThreatenedByBlack;
    private Set<Square> squaresThreatenedByWhite;
    private Map<Player, King> kings;

    public ChessBoard() {
        initializeBoard();
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
        this.squaresThreatenedByBlack = new HashSet();
        this.squaresThreatenedByWhite = new HashSet();
        this.kings = new HashMap();
    }

    private void initializeBoard() {
        board = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new Square(i, j);
            }
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    public void setBoard(Square[][] newBoard) {
        this.board = newBoard;
    }

    public Map<Player, King> getKings() {
        return this.kings;
    }

    public void updateThreatenedSquares(Player player) {
        if (player == Player.WHITE) {
            squaresThreatenedByWhite = squaresThreatenedByWhite();
        } else {
            squaresThreatenedByBlack = squaresThreatenedByBlack();
        }
    }

    public List<Piece> getPieces(Player player) {
        if (player == Player.WHITE) {
            return whitePieces;
        } else {
            return blackPieces;
        }
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

    public Set<Square> squaresThreatenedByBlack() {
        Set<Square> set = new HashSet();

        blackPieces.stream().forEach((blackPiece) -> {
            set.addAll(blackPiece.possibleMoves(this));
        });

        return set;
    }

    public Set<Square> squaresThreatenedByWhite() {
        Set<Square> set = new HashSet();

        whitePieces.stream().forEach((whitePiece) -> {
            set.addAll(whitePiece.possibleMoves(this));
        });

        return set;
    }

    public Set<Square> threatenedSquares(Player player) {
        if (player == Player.WHITE) {
            return squaresThreatenedByWhite;
        } else {
            return squaresThreatenedByBlack;
        }
    }

    public ChessBoard copy() {
        ChessBoard copy = new ChessBoard();
        Square[][] copyBoard = copyBoard();

        copy.setBoard(copyBoard);
        copy.findPieces();

        return copy;
    }

    private Square[][] copyBoard() {
        Square[][] copyBoard = new Square[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                copyBoard[i][j] = board[i][j].clone();
            }
        }
        return copyBoard;
    }

    private void findPieces() {
        this.blackPieces = new ArrayList();
        this.whitePieces = new ArrayList();

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                addPieceToOwner(i, j);
            }
        }
    }

    private void addPieceToOwner(int file, int rank) {
        if (board[file][rank].containsAPiece()) {
            Piece piece = board[file][rank].getPiece();

            if (piece.getClass() == King.class) {
                kings.put(piece.getOwner(), (King) piece);
            }

            getPieces(piece.getOwner()).add(piece);
        }
    }

    public void removePiece(Piece piece) {
        if (piece.getOwner() == Player.WHITE) {
            whitePieces.remove(piece);
        } else {
            blackPieces.remove(piece);
        }
    }

}
