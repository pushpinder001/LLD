package entity;

import dto.Position;

public class Driver {
    private Integer id;

    private Cab cab;

    private boolean isAvailable;

    public Driver(Position position) {
        this.cab = new Cab(position);
        this.isAvailable = true;
    }

    public Integer getId() {
        return id;
    }

    public Position getCabPosition() {
        return this.cab.getPosition();
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean updateCabPosition(Position position) {
        return cab.updatePosition(position);
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", cab=" + cab +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
