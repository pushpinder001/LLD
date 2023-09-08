package service;

import dto.Position;

import java.util.List;

public interface ChessGameService {
    List<Position> nextMoves(int playerId, Position curPos);

    boolean move(int playerId, Position curPos, Position targetPos);

    void printBoard();
}
