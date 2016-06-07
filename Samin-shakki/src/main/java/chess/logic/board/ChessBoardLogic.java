package chess.logic.board;

import chess.logic.pieces.King;
import chess.logic.pieces.Piece;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is responsible for keeping track of the current situation on
 * board. This class also offers methods to access every piece on board and
 * Squares they threaten or can move to.
 *
 * @author samisalo
 */
public class ChessBoardLogic {

    private Square[][] board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    private Set<Square> squaresThreatenedByBlack;
    private Set<Square> squaresThreatenedByWhite;
    private Map<Player, King> kings;

    public ChessBoardLogic() {
        initializeBoard();
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
        this.squaresThreatenedByBlack = new HashSet();
        this.squaresThreatenedByWhite = new HashSet();
        this.kings = new HashMap();
    }

    /**
     * Initialises a new 8x8 board.
     */
    private void initializeBoard() {
        board = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new Square(i, j);
            }
        }
    }

    /**
     * Returns the Square[][] saved to field board.
     *
     * @return Reference to Square[][] that is saved to field board.
     */
    public Square[][] getBoard() {
        return board;
    }

    /**
     * Sets the board given as parameter to field board.
     *
     * @param newBoard Square[][] to be saved to field board.
     */
    public void setBoard(Square[][] newBoard) {
        this.board = newBoard;
    }

    /**
     * Returns a map with references from each player to their king.
     *
     * @return map with references from each player to their king.
     */
    public Map<Player, King> getKings() {
        return this.kings;
    }

    /**
     * Updates the Squares that player's pieces threaten to corresponding field.
     *
     * @param player Player whose corresponding field you want to update.
     */
    public void updateThreatenedSquares(Player player) {
        if (player == Player.WHITE) {
            squaresThreatenedByWhite = squaresThreatenedByWhite();
        } else {
            squaresThreatenedByBlack = squaresThreatenedByBlack();
        }
    }

    /**
     * Returns a list containing all pieces currently on board and owned by the
     * player.
     *
     * @param player player whose pieces you want.
     * @return list containing all pieces owned by the player.
     */
    public List<Piece> getPieces(Player player) {
        if (player == Player.WHITE) {
            return whitePieces;
        } else {
            return blackPieces;
        }
    }

    /**
     * Returns the Square at given location on board.
     *
     * @param column Column of the Square you want.
     * @param row Row of the Square you want.
     * @return Square at given location.
     */
    public Square getSquare(int column, int row) {
        return board[column][row];
    }

    /**
     * Checks if the given location is on the chessboard.
     *
     * @param column Column of the Square you want.
     * @param row Row of the Square you want.
     * @return true if given coordinates are within the table.
     */
    public boolean withinTable(int column, int row) {
        if (column < 0 || column >= board.length) {
            return false;
        }
        if (row < 0 || row >= board[0].length) {
            return false;
        }
        return true;
    }

    private Set<Square> squaresThreatenedByBlack() {
        Set<Square> set = new HashSet();

        blackPieces.stream().forEach((blackPiece) -> {
            set.addAll(blackPiece.possibleMoves(this));
        });

        return set;
    }

    private Set<Square> squaresThreatenedByWhite() {
        Set<Square> set = new HashSet();

        whitePieces.stream().forEach((whitePiece) -> {
            set.addAll(whitePiece.possibleMoves(this));
        });

        return set;
    }

    /**
     * Returns a set containing all Squares that player threatens.
     *
     * @param player Player
     * @return set containing all squares player threatens
     */
    public Set<Square> threatenedSquares(Player player) {
        if (player == Player.WHITE) {
            return squaresThreatenedByWhite;
        } else {
            return squaresThreatenedByBlack;
        }
    }

    /**
     * Returns a new ChessBoard that deeply equals this one.
     *
     * @return a deep copy of this board.
     */
    public ChessBoardLogic copy() {
        ChessBoardLogic copy = new ChessBoardLogic();
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
                addPieceToOwner(board[i][j]);
            }
        }
    }

    /**
     * Adds the piece on target square to list of pieces its owner owns. Also
     * adds a reference to Map Kings if the piece is of King class.
     *
     * @param target Square
     */
    public void addPieceToOwner(Square target) {
        if (target.containsAPiece()) {
            Piece piece = target.getPiece();

            if (piece.getClass() == King.class) {
                kings.put(piece.getOwner(), (King) piece);
            }

            getPieces(piece.getOwner()).add(piece);
        }
    }

    /**
     * Removes target piece from its owner's owned pieces list.
     *
     * @param piece The piece you want to remove.
     */
    public void removePieceFromOwner(Piece piece) {
        if (piece.getOwner() == Player.WHITE) {
            whitePieces.remove(piece);
        } else {
            blackPieces.remove(piece);
        }
    }

}
