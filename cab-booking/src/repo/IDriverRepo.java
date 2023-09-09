package repo;

import entity.Driver;

import java.util.List;

public interface IDriverRepo {
    boolean save(Driver driver);

    Driver get(Integer id);

    List<Driver> getAll();
}
