/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.board;

/**
 *
 * @author sami
 */
public enum Player {

    WHITE(1), BLACK(-1);
    private int direction;

    private Player(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public static Player getOpponent(Player player) {
        if (player == Player.WHITE) {
            return Player.BLACK;
        } else {
            return Player.WHITE;
        }
    }

}
