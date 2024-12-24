package com.project.app.command.strategy;

import com.project.app.service.ParkingLotService;

import java.util.List;

public class GetSlotNosForCarColorCommandHandler extends ICommandHandler{
    public GetSlotNosForCarColorCommandHandler(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public boolean validateParams(String[] params) {
        return params.length>0;
    }

    @Override
    public boolean doYouHandleIt(String cmd) {
        return "slot_numbers_for_cars_with_colour".equals(cmd);
    }

    @Override
    public void execute(String[] params) {
        List<Integer> slotNos = parkingLotService.getSlotNosForCarWithColor(params[0]);
        System.out.println(slotNos);
    }
}
