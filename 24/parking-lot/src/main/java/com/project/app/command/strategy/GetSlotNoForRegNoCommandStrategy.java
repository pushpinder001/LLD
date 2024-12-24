package com.project.app.command.strategy;

import com.project.app.service.ParkingLotService;

import java.util.List;

public class GetSlotNoForRegNoCommandStrategy implements ICommandStrategy{
    @Override
    public boolean validateParams(String[] params) {
        return params.length>0;
    }

    @Override
    public boolean doYouHandleIt(String cmd) {
        return "slot_number_for_registration_number".equals(cmd);
    }

    @Override
    public void execute(String[] params) {
        try {
            Integer regNo = ParkingLotService.getInstance().getSlotNoForRegNo(params[0]);
            System.out.println(regNo);
        } catch (Exception e) {
            System.out.println("Not found");
        }
    }
}
