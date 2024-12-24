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
        Slot slot = slotFinderStrategy.getSlot(slots);

        if(slot == null) {
            throw new Exception("Parking lot full");
        }

        Ticket ticket = new Ticket(UUID.randomUUID().toString(), regNo, color, slot.getSlotNo());

        //Save ticket
        ticketIdToTicketMap.put(ticket.getId(), ticket);
        slot.setTicket(ticket);
        return ticket;
    }

    public void unParkVehicle(@NonNull final Integer slotNo) throws Exception {
        Slot slot = slots.stream().filter(s -> s.getSlotNo().equals(slotNo)).findAny().orElseThrow(
                () -> new Exception("No slot with given no is avail"));

        //TODO:: validate if slot is free
        slot.freeSlot();
    }

    public void getStatus() {
        System.out.println("Slot No. Registration No Colour");
        System.out.println(slots);
    }

    private List<String> getRegNoForCarWithColor(@NonNull final String color) {
        return List.of();
    }

    private List<Integer> getSlotNoForCarWithColor(@NonNull final String color) {
        return List.of();
    }

    private Integer getSlotNoForRegNo(@NonNull final String regNo) {
        return null;
    }
}
