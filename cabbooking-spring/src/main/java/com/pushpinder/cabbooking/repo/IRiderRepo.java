package com.pushpinder.cabbooking.repo;

import com.pushpinder.cabbooking.entity.Rider;

public interface IRiderRepo {
    boolean save(Rider rider);

    Rider get(Integer id);
}
