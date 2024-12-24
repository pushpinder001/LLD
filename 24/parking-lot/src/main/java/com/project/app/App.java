package com.project.app;

import com.project.app.command.CommandRunner;
import com.project.app.command.strategy.*;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static CommandRunner commandRunner;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        commandRunner = new CommandRunner(List.of(new CreateParkingLotCommandHandler(),
                new ParkVehicleCommandHandler(), new LeaveVehicleCommandHandler(),
                new StatusCommandHandler(), new GetRegistrationNoForColorCommandHandler(),
                new GetSlotNoForRegNoCommandHandler(), new GetSlotNosForCarColorCommandHandler()));

        while(true) {
            System.out.println("Enter command : ");
            String cmd = scanner.nextLine();
            commandRunner.execute(cmd);
        }
    }
}
