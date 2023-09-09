package repo.impl;

import entity.Driver;
import repo.IDriverRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryDriverRepo implements IDriverRepo {
    private static int COUNTER = 0;
    private Map<Integer, Driver> driverIdToDriver;

    public InMemoryDriverRepo() {
        this.driverIdToDriver = new HashMap<>();
    }

    @Override
    public boolean save(Driver driver) {
        if(driver.getId() == null) {
            driver.setId(COUNTER++);
        }
        driverIdToDriver.put(driver.getId(), driver);

        return false;
    }

    @Override
    public Driver get(Integer id) {
        return driverIdToDriver.get(id);
    }

    @Override
    public List<Driver> getAll() {
        return this.driverIdToDriver.values().stream().filter(Driver::isAvailable).collect(Collectors.toList());
    }
}
