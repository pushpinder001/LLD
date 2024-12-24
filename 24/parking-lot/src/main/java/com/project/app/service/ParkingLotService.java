package com.project.app.service;


import com.project.app.entity.Slot;
import com.project.app.entity.Ticket;
import com.project.app.service.strategy.ISlotFinderStrategy;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ParkingLotService {
    private List<Slot> slots;
    private ISlotFinderStrategy slotFinderStrategy;
    private final Map<String, Ticket> ticketIdToTicketMap;

    private static ParkingLotService parkingLotService = null;

    private ParkingLotService(List<Slot> slots, ISlotFinderStrategy slotFinderStrategy) {
        this.slots = slots;
        this.slotFinderStrategy = slotFinderStrategy;
        for(int slotNo=1; slotNo<=slots.size(); slotNo++) {
            slotFinderStrategy.addSlot(slotNo);
        }
        ticketIdToTicketMap = new HashMap<>();
    }

    public static ParkingLotService createInstance(List<Slot> slots, ISlotFinderStrategy slotFinderStrategy) {
        ParkingLotService.parkingLotService = new ParkingLotService(slots, slotFinderStrategy);
        return parkingLotService;
    }

    public static ParkingLotService getInstance() {
        return ParkingLotService.parkingLotService;
    }

    public Ticket parkVehicle(@NonNull final String regNo, @NonNull final String color) throws Exception {
        Integer slotNo = slotFinderStrategy.getSlot();

        if(slotNo == null) {
            throw new Exception("Parking lot full");
        }

        Slot slot = slots.stream().filter(s -> s.getSlotNo() == slotNo).findAny().get();
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), regNo, color, slot.getSlotNo());

        //Save ticket
        ticketIdToTicketMap.put(ticket.getId(), ticket);
        slot.setTicket(ticket);
        slotFinderStrategy.removeSlot(slot.getSlotNo());
        return ticket;
    }

    public void unParkVehicle(@NonNull final Integer slotNo) throws Exception {
        Slot slot = slots.stream().filter(s -> s.getSlotNo().equals(slotNo)).findAny().orElseThrow(
                () -> new Exception("No slot with given no is avail"));

        //TODO:: validate if slot is free
        if(slot.isSlotFree()) {
            new Exception("Slot is already free");
        }
        slot.freeSlot();
        slotFinderStrategy.addSlot(slot.getSlotNo());
    }

    public void getStatus() {
        System.out.println("Slot No. Registration No Colour");
        for(var slot : slots) {
            if(!slot.isSlotFree()) {
                System.out.printf("%7d %15s %6s\n", slot.getSlotNo(), slot.getTicket().getRegNo(),
                        slot.getTicket().getColor());
            }
        }
    }

    public List<String> getRegNoForCarWithColor(@NonNull final String color) {
        return slots.stream().filter(slot -> !slot.isSlotFree())
                .map(Slot::getTicket)
                .map(Ticket::getRegNo)
                .toList();
    }

    public List<Integer> getSlotNosForCarWithColor(@NonNull final String color) {
        return slots.stream().filter(slot -> !slot.isSlotFree())
                .filter(slot -> slot.getTicket().getColor().equals(color))
                .map(Slot::getSlotNo)
                .toList();
    }

    public Integer getSlotNoForRegNo(@NonNull final String regNo) throws Exception {
        return slots.stream().filter(slot -> !slot.isSlotFree())
                .filter(slot -> slot.getTicket().getRegNo().equals(regNo))
                .map(Slot::getSlotNo)
                .findFirst().orElseThrow(() -> new Exception("Not found"));
    }
}
