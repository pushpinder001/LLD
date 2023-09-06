package com.pushpinder.model;

import com.pushpinder.exception.CarAlreadyParkedException;
import com.pushpinder.exception.NoCarPresentForSlot;

public class Slot {
    private int id;

    private Car car;

    public Slot(int id) {
        this.id = id;
    }

    public boolean addCar(Car car) throws CarAlreadyParkedException {
        if(this.car != null) {
            throw new CarAlreadyParkedException();
        }

        this.car = car;
        return true;
    }

    public Car removeCar() throws NoCarPresentForSlot {
        if(this.car == null) {
            throw new NoCarPresentForSlot();
        }

        Car c = this.car;
        this.car = null;
        return c;
    }

    public int getId() {
        return id;
    }

    public boolean isCarParked() {
        return this.car != null? true:false;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", car=" + car +
                '}';
    }
}
