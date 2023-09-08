package repo;

import model.Board;

public interface BoardRepo {
    boolean save(Board board);

    Board getBoard(Integer id);
}
