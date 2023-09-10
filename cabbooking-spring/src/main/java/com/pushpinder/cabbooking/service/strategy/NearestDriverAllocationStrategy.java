package com.pushpinder.cabbooking.service.strategy;

import com.pushpinder.cabbooking.dto.Position;
import com.pushpinder.cabbooking.entity.Driver;
import com.pushpinder.cabbooking.repo.IDriverRepo;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NearestDriverAllocationStrategy implements IDriverAllocationStrategy {
    private IDriverRepo driverRepo;

    public NearestDriverAllocationStrategy(IDriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    @Override
    public Driver getDriver(Position startPosition) {
        List<Driver> drivers = driverRepo.getAll();

        Comparator<Driver> driverComparator = Comparator.comparingInt(d -> d.getCabPosition().dist(startPosition));

        PriorityQueue<Driver> p = new PriorityQueue<>(driverComparator);

        drivers.forEach(driver -> {
            p.add(driver);
            if(p.size()>1) p.poll();
        });


        return p.size()==1? p.poll():null;
    }
}
