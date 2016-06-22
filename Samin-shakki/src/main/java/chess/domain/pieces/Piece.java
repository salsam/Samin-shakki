package chess.domain.pieces;

import chess.domain.board.Player;

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
    protected String pieceCode;
    protected boolean taken;

    /**
     * This is a dummy constructor that is used to avoid copy-paste code in all
     * inheriting classes.
     *
     * @param column column of the square this piece will be placed
     * @param row row of the square this piece will be placed on
     * @param owner owner of this piece
     * @param pieceCode pieceCode of this piece
     */
    public Piece(int column, int row, Player owner, String pieceCode) {
        this.column = column;
        this.row = row;
        this.owner = owner;
        this.pieceCode = pieceCode;
        this.taken = false;
    }

    public void makeFieldsEqualTo(Piece piece) {
        this.column = piece.getColumn();
        this.row = piece.getRow();
        this.owner = piece.getOwner();
        this.taken = piece.isTaken();
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

        return this.pieceCode.equals(piece.getPieceCode());
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

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public String getPieceCode() {
        return pieceCode;
    }

    public void setPieceCode(String pieceCode) {
        this.pieceCode = pieceCode;
    }

}
