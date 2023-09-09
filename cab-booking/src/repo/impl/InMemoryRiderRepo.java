package repo.impl;

import entity.Rider;
import repo.IRiderRepo;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRiderRepo implements IRiderRepo {
    private static int COUNTER = 0;
    private Map<Integer, Rider> riderIdToDriver;

    public InMemoryRiderRepo() {
        this.riderIdToDriver = new HashMap<>();
    }

    @Override
    public boolean save(Rider rider) {
        if(rider.getId() == null) {
            rider.setId(COUNTER++);
        }
        this.riderIdToDriver.put(rider.getId(), rider);
        return true;
    }

    @Override
    public Rider get(Integer id) {
        return this.riderIdToDriver.get(id);
    }
}
