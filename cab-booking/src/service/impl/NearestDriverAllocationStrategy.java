package service.impl;

import entity.Driver;
import entity.Rider;
import repo.IDriverRepo;
import service.IDriverAllocationStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NearestDriverAllocationStrategy implements IDriverAllocationStrategy {
    private IDriverRepo driverRepo;

    public NearestDriverAllocationStrategy(IDriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    @Override
    public Driver getDriver(Rider rider) {
        List<Driver> drivers = driverRepo.getAll();

        Comparator<Driver> driverComparator = (d1, d2) -> {
            final int x = rider.getPosition().X();
            final int y = rider.getPosition().Y();
            final int d1X = d1.getCabPosition().X();
            final int d1Y = d1.getCabPosition().Y();
            final int d2X = d2.getCabPosition().X();
            final int d2Y = d2.getCabPosition().Y();

            return (int)(Math.pow(d2X-x, 2.0) + Math.pow(d2Y-y, 2.0) - Math.pow(d1X-x, 2.0) - Math.pow(d1Y-y, 2.0));
        };

        PriorityQueue<Driver> p = new PriorityQueue<>(driverComparator);

        drivers.forEach(driver -> {
            p.add(driver);
            if(p.size()>1) p.poll();
        });


        return p.size()==1? p.poll():null;
    }
}
