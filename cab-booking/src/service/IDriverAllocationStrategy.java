package service;

import entity.Driver;
import entity.Rider;

public interface IDriverAllocationStrategy {
    Driver getDriver(Rider rider);
}
