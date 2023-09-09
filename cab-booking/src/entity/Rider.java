package entity;

import dto.Position;

public class Rider {
    private Integer id;

    private Position position;

    public Rider(Position position) {
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Rider{" +
                "id=" + id +
                ", position=" + position +
                '}';
    }
}
