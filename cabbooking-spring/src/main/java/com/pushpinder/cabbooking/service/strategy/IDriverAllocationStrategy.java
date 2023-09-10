package com.pushpinder.cabbooking.service.strategy;

import com.pushpinder.cabbooking.dto.Position;
import com.pushpinder.cabbooking.entity.Driver;

public interface IDriverAllocationStrategy {
    Driver getDriver(Position startPos);
}
