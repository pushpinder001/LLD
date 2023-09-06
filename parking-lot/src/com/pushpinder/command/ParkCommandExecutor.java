package com.pushpinder.command;

import com.pushpinder.exception.ParkingLotFullException;
import com.pushpinder.model.Car;
import com.pushpinder.model.Ticket;
import com.pushpinder.service.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor{
    public ParkCommandExecutor(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    protected boolean validate(String[] commandSplits) {
        return true;
    }

    @Override
    public void process(String[] commandSplits) {
        try {
            Ticket ticket = parkingLotService.park(new Car(commandSplits[2], commandSplits[1]));
            System.out.printf("Allocated slot number: %s\n", ticket.slotNo);
        } catch (ParkingLotFullException e) {
            System.out.println(e);
        }
    }
}
