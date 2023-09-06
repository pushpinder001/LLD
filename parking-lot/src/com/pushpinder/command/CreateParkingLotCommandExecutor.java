package com.pushpinder.command;

import com.pushpinder.exception.ParkingLotServiceAlreadyCreatedException;
import com.pushpinder.exception.ParkingStrategyNotFoundException;
import com.pushpinder.model.Command;
import com.pushpinder.model.parking.stragegy.ParkingStrategyFactory;
import com.pushpinder.service.ParkingLotService;

public class CreateParkingLotCommandExecutor extends CommandExecutor{

    public CreateParkingLotCommandExecutor(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    protected boolean validate(Command command) {
        return true;
    }

    @Override
    public void process(Command command) {
        try {
            parkingLotService.createParkingLot(Integer.parseInt(command.getCommandParams().get(0)), ParkingStrategyFactory.ParkingStrategyType.NATURAL_ORDER);
            System.out.printf("Created a parking lot with %s slots\n", command.getCommandParams().get(0));
        } catch (ParkingLotServiceAlreadyCreatedException e) {
            System.out.println(e);
        } catch (ParkingStrategyNotFoundException e) {
            System.out.println(e);
        }
    }
}
