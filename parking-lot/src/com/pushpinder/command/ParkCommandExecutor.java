package com.pushpinder.command;

import com.pushpinder.exception.ParkingLotFullException;
import com.pushpinder.model.Car;
import com.pushpinder.model.Command;
import com.pushpinder.model.Ticket;
import com.pushpinder.service.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor{
    public ParkCommandExecutor(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    protected boolean validate(Command command) {
        return true;
    }

    @Override
    public void process(Command command) {
        try {
            Ticket ticket = parkingLotService.park(new Car(command.getCommandParams().get(1),
                    command.getCommandParams().get(0)));
            System.out.printf("Allocated slot number: %s\n", ticket.slotNo);
        } catch (ParkingLotFullException e) {
            System.out.println(e);
        }
    }
}
