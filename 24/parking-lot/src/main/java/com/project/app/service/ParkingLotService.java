package com.project.app.service;


import com.project.app.entity.ParkingLot;
import com.project.app.entity.Ticket;
import com.project.app.service.strategy.ISlotFinderStrategy;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public class ParkingLotService {
    private ParkingLot parkingLot;
    private ISlotFinderStrategy slotFinderStrategy;

    public ParkingLotService() {
    }

    public boolean createParkingLot(ParkingLot parkingLot, ISlotFinderStrategy slotFinderStrategy) throws Exception {
        if(this.parkingLot != null) {
            throw new Exception("Parking Lot already created");
        }

        this.parkingLot = parkingLot;
        this.slotFinderStrategy = slotFinderStrategy;
        return true;
    }

    public Ticket parkVehicle(@NonNull final String regNo, @NonNull final String color) throws Exception {
        Integer slotNo = slotFinderStrategy.getSlot();

        if(slotNo == null) {
            throw new Exception("Parking lot full");
        }

        Ticket ticket = new Ticket(UUID.randomUUID().toString(), regNo, color, slotNo);

        //Save ticket
        parkingLot.addTicket(slotNo, ticket);
        slotFinderStrategy.removeSlot(slotNo);
        return ticket;
    }

    public void unParkVehicle(@NonNull final Integer slotNo) throws Exception {
        if(!parkingLot.isSlotValid(slotNo)) {
            throw new Exception("Slot No Invalid");
        }

        //TODO:: validate if slot is free
        if(!parkingLot.isSlotFree(slotNo)) {
            new Exception("Slot is already free");
        }
        parkingLot.freeSlot(slotNo);
        slotFinderStrategy.addSlot(slotNo);
    }

    public void getStatus() {
        System.out.println("Slot No. Registration No Colour");
        for(var slot : parkingLot.getSlotNos()) {
            if(!parkingLot.isSlotFree(slot)) {
                System.out.printf("%7d %15s %6s\n", slot, parkingLot.getTicket(slot).getRegNo(),
                        parkingLot.getTicket(slot).getColor());
            }
        }
    }

    public List<String> getRegNoForCarWithColor(@NonNull final String color) {
        return  parkingLot.getSlotNos().stream().filter(slot -> !parkingLot.isSlotFree(slot))
                .map(slot -> parkingLot.getTicket(slot))
                .filter(ticket-> ticket.getColor().equals(color))
                .map(Ticket::getRegNo)
                .toList();
    }

    public List<Integer> getSlotNosForCarWithColor(@NonNull final String color) {
        return parkingLot.getSlotNos().stream().filter(slot -> !parkingLot.isSlotFree(slot))
                .filter(slot -> parkingLot.getTicket(slot).getColor().equals(color))
                .toList();
    }

    public Integer getSlotNoForRegNo(@NonNull final String regNo) throws Exception {
        return parkingLot.getSlotNos().stream().filter(slot -> !parkingLot.isSlotFree(slot))
                .filter(slot -> parkingLot.getTicket(slot).getRegNo().equals(regNo))
                .findFirst().orElseThrow(() -> new Exception("Not found"));
    }
}
