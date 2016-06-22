package chess.domain.board;

import chess.logic.movementlogic.MovementLogic;
import chess.domain.pieces.King;
import chess.domain.pieces.Piece;
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
 * @author sami
 */
public class ChessBoard {

    private Square[][] table;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    private Set<Square> squaresThreatenedByBlack;
    private Set<Square> squaresThreatenedByWhite;
    private MovementLogic movementLogic;
    private Map<Player, King> kings;

    /**
     * Creates a new chessboard with given movement logic.
     *
     * @param movementLogic movement logic to be applied on this chessboard.
     */
    public ChessBoard(MovementLogic movementLogic) {
        initializeBoard();
        this.movementLogic = movementLogic;
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
        this.squaresThreatenedByBlack = new HashSet();
        this.squaresThreatenedByWhite = new HashSet();
        this.kings = new HashMap();
    }

    /**
     * Initializes a new 8x8 board.
     */
    private void initializeBoard() {
        table = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.table[i][j] = new Square(i, j);
            }
        }
    }

    /**
     * Returns the Square[][] saved to field board.
     *
     * @return Reference to Square[][] that is saved to field board.
     */
    public Square[][] getTable() {
        return table;
    }

    public MovementLogic getMovementLogic() {
        return movementLogic;
    }

    /**
     * Sets the board given as parameter to field board.
     *
     * @param newBoard Square[][] to be saved to field board.
     */
    public void setTable(Square[][] newBoard) {
        this.table = newBoard;
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
     * This methods uses the MovementLogic given to it in constructor to check
     * which squares are currently threatened by player.
     *
     * @param player Player whose corresponding field you want to update.
     */
    public void updateThreatenedSquares(Player player) {
        if (player == Player.WHITE) {
            squaresThreatenedByWhite = movementLogic.squaresThreatenedByPlayer(Player.WHITE, this);
        } else {
            squaresThreatenedByBlack = movementLogic.squaresThreatenedByPlayer(Player.BLACK, this);
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

    public void setBlackPieces(List<Piece> blackPieces) {
        this.blackPieces = blackPieces;
    }

    public void setWhitePieces(List<Piece> whitePieces) {
        this.whitePieces = whitePieces;
    }

    /**
     * Returns the Square at given location on board.
     *
     * @param column Column of the Square you want.
     * @param row Row of the Square you want.
     * @return Square at given location.
     */
    public Square getSquare(int column, int row) {
        return table[column][row];
    }

    /**
     * Checks if the given location is on the chessboard.
     *
     * @param column Column of the Square being checked.
     * @param row Row of the Square being checked.
     * @return true if given coordinates are within the table.
     */
    public boolean withinTable(int column, int row) {
        if (column < 0 || column >= table.length) {
            return false;
        }
        return !(row < 0 || row >= table[0].length);
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

    public void makeFieldsEqualTo(ChessBoard chessboard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                table[i][j].setPiece(null);
            }
            
        }
        for (Player player : Player.values()) {
            getPieces(player).stream().forEach(playersPiece -> {
                chessboard.getPieces(player).stream().forEach(piece -> {
                    if (piece.equals(playersPiece)) {
                        playersPiece.makeFieldsEqualTo(piece);
                    }
                });
            });
        }

        for (Player player : Player.values()) {
            getPieces(player).stream().forEach(piece -> {
                Square location = table[piece.getColumn()][piece.getRow()];
                if (!piece.isTaken()) {
                    location.setPiece(piece);
                }
            });
        }
    }
}
