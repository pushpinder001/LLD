package repo;

import model.Cell;

import java.util.List;

public interface CellRepo {
    List<Cell> getCells(Integer id);

    boolean saveAll(List<Cell> cells);
}
