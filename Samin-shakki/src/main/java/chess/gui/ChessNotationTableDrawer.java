package chess.gui;

import chess.board.ChessBoard;
import chess.board.Player;
import chess.pieces.Knight;
import chess.pieces.Piece;

/**
 *
 * @author sami
 */
public class ChessNotationTableDrawer {

    public String[][] graphicalBoard(ChessBoard board) {
        String[][] graphicalBoard = new String[8][8];
        Piece piece;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!board.getSquare(i, j).containsAPiece()) {
                    graphicalBoard[i][j] = " ";
                } else {
                    piece = board.getSquare(i, j).getPiece();
                    graphicalBoard[i][j] = chessNotation(piece);
                }
            }
        }
        return graphicalBoard;
    }

    public void draw(ChessBoard board) {
        Piece piece;

        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                if (!board.getSquare(i, j).containsAPiece()) {
                    System.out.print("  ");
                } else {
                    piece = board.getSquare(i, j).getPiece();
                    System.out.print(chessNotation(piece));
                }
            }
            System.out.println("");
        }
    }

    public String chessNotation(Piece piece) {
        char suffix;
        char prefix;
        if (piece.getClass() == Knight.class) {
            suffix = 'N';
        } else {
            suffix = piece.getClass().toString().charAt(19);
        }

        if (piece.getOwner() == Player.WHITE) {
            prefix = 'w';
        } else {
            prefix = 'b';
        }
        return "" + prefix + suffix;
    }

}
