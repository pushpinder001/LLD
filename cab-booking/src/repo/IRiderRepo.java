package repo;

import entity.Rider;

public interface IRiderRepo {
    boolean save(Rider rider);

    Rider get(Integer id);
}
