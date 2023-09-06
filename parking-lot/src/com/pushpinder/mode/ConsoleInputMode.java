package com.pushpinder.mode;

import com.pushpinder.command.CommandExecutorFactory;
import com.pushpinder.exception.*;
import com.pushpinder.service.ParkingLotService;

import java.util.Scanner;

public class ConsoleInputMode extends Mode{
    public ConsoleInputMode(ParkingLotService parkingLotService, CommandExecutorFactory commandExecutorFactory) {
        super();
        this.parkingLotService = parkingLotService;
        this.commandExecutorFactory = commandExecutorFactory;
    }

    @Override
    public void process() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            try {
                executeCommand(input);
            } catch (NoCommandFoundException e) {
                System.out.println("Invalid Command");
            }
//            String[] inputs = input.split(" ");
//
//            switch (inputs[0]) {
//                case "create_parking_lot" -> {
//                    try {
//                        parkingLotService.createParkingLot(Integer.parseInt(inputs[1]), ParkingStrategyFactory.ParkingStrategyType.NATURAL_ORDER);
//                        System.out.printf("Created a parking lot with %s slots\n", inputs[1]);
//                    } catch (ParkingLotServiceAlreadyCreatedException e) {
//                        System.out.println(e);
//                    } catch (ParkingStrategyNotFoundException e) {
//                        System.out.println(e);
//                    }
//                }
//                case "park" -> {
//                    try {
//                        Ticket ticket = parkingLotService.park(new Car(inputs[2], inputs[1]));
//                        System.out.printf("Allocated slot number: %s\n", ticket.slotNo);
//                    } catch (ParkingLotFullException e) {
//                        System.out.println(e);
//                    }
//                }
//                case "leave" -> {
//                    try {
//                        parkingLotService.unPark(Integer.parseInt(inputs[1]));
//                        System.out.printf("Slot number %s is free\n", inputs[1]);
//                    } catch (NoCarPresentForSlot e) {
//                        System.out.println(e);
//                    }
//                }
//                case "status" -> {
//                    System.out.println(parkingLotService.toString());
//                }
//                case  "registration_numbers_for_cars_with_colour" -> System.out.println(parkingLotService.getRegistrationNoForCarColor(inputs[1]));
//                case "slot_number_for_registration_number" -> {int slotNo = parkingLotService.slotNoForCar(inputs[1]); if(slotNo !=0)
//                    System.out.println(slotNo);}
//                case "slot_numbers_for_cars_with_colour" -> System.out.println(parkingLotService.getSlotNoOfCarColor(inputs[1]));
//                default -> throw new RuntimeException("Invalid Input");
//            }
        }
    }
}
