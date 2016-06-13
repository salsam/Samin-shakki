package chess.pieces;

import chess.logic.board.Player;

/**
 *
 * @author samisalo
 */
public class Knight {

    private Player owner;
    private int column;
    private int row;

    public Knight(Player owner, int column, int row) {
        this.owner = owner;
        this.column = column;
        this.row = row;
    }

    public Player getOwner() {
        return owner;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

}
