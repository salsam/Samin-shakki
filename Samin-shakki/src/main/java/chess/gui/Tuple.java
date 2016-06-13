package chess.gui;

import chess.logic.board.Player;
import java.util.Objects;

/**
 *
 * @author samisalo
 */
public class Tuple {

    private Class klass;
    private Player owner;

    public Tuple(Class klass, Player owner) {
        this.klass = klass;
        this.owner = owner;
    }

    public Class getKlass() {
        return klass;
    }

    public void setKlass(Class klass) {
        this.klass = klass;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.klass);
        hash = 23 * hash + Objects.hashCode(this.owner);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tuple other = (Tuple) obj;
        if (!Objects.equals(this.klass, other.klass)) {
            return false;
        }
        if (this.owner != other.owner) {
            return false;
        }
        return true;
    }

}
