package chess.logic.pieces;

import chess.logic.board.Player;

/**
 * This is an abstract parent class of all chess pieces containing information
 * of its location and owner. This class also offers methods to access that
 * knowledge.
 *
 * @author sami
 */
public abstract class Piece {

    protected int column;
    protected int row;
    protected Player owner;

    /**
     * This is a dummy constructor that is used to avoid copy-paste code on all
     * inheriting classes.
     *
     * @param column column of the square this piece will be placed
     * @param row row of the square this piece will be placed on
     * @param owner owner of this piece
     */
    public Piece(int column, int row, Player owner) {
        this.column = column;
        this.row = row;
        this.owner = owner;
    }

    @Override
    public abstract Piece clone();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Piece piece = (Piece) obj;

        if (this.column != piece.getColumn()) {
            return false;
        }

        if (this.row != piece.getRow()) {
            return false;
        }

        return this.owner == piece.getOwner();
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

    public Player getOwner() {
        return owner;
    }
}
