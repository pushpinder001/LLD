package com.project.app.command.strategy;

import com.project.app.service.ParkingLotService;

import java.util.List;

public class GetSlotNosForCarColorCommandHandler implements ICommandHandler{
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
        List<Integer> slotNos = ParkingLotService.getInstance().getSlotNosForCarWithColor(params[0]);
        System.out.println(slotNos);
    }
}
