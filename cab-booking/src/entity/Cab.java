package entity;

import dto.Position;

public class Cab {
    private Position position;

    public Cab(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    boolean updatePosition(Position position) {
        this.position  = position;
        return true;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "position=" + position +
                '}';
    }
}
