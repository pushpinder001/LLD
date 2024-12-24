package com.project.app;

import com.project.app.command.CommandRunner;
import com.project.app.command.strategy.*;
import com.project.app.service.ParkingLotService;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static CommandRunner commandRunner;
    private static Scanner scanner = new Scanner(System.in);
    private static ParkingLotService parkingLotService = new ParkingLotService();

    public static void main(String[] args) {
        commandRunner = new CommandRunner(List.of(new CreateParkingLotCommandHandler(parkingLotService),
                new ParkVehicleCommandHandler(parkingLotService), new LeaveVehicleCommandHandler(parkingLotService),
                new StatusCommandHandler(parkingLotService), new GetRegistrationNoForColorCommandHandler(parkingLotService),
                new GetSlotNoForRegNoCommandHandler(parkingLotService), new GetSlotNosForCarColorCommandHandler(parkingLotService)));

        while(true) {
            System.out.println("Enter command : ");
            String cmd = scanner.nextLine();
            commandRunner.execute(cmd);
        }
    }
}
