package chess.logic.pieces;

import chess.logic.board.Player;

/**
 *
 * @author samisalo
 */
public abstract class Piece {

    protected int column;
    protected int row;
    protected Player owner;

    public Piece(int column, int row, Player owner) {
        this.column = column;
        this.row = row;
        this.owner = owner;
    }

    @Override
    public abstract Piece clone();

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

    public Player getOwner() {
        return owner;
    }
}
