package entity;

import dto.Position;
import enums.RideStatus;

public class Ride {
    private Integer id;

    private Position startPos;

    private Position endPos;

    private Integer riderId;

    private Integer driverId;

    private RideStatus rideStatus;

    public Ride(Position startPos, Integer riderId, Integer driverId, RideStatus rideStatus) {
        this.startPos = startPos;
        this.riderId = riderId;
        this.driverId = driverId;
        this.rideStatus = rideStatus;
    }

    public Integer getId() {
        return id;
    }

    public Position getStartPos() {
        return startPos;
    }

    public Position getEndPos() {
        return endPos;
    }

    public Integer getRiderId() {
        return riderId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setEndPos(Position endPos) {
        this.endPos = endPos;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", startPos=" + startPos +
                ", endPos=" + endPos +
                ", riderId=" + riderId +
                ", driverId=" + driverId +
                '}';
    }
}
