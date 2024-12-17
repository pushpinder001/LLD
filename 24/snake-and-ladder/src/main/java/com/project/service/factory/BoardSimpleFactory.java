package com.project.service.factory;

import com.project.service.Board;
import com.project.service.IBoardObject;
import com.project.service.Ladder;
import com.project.service.Snake;

import java.util.Arrays;
import java.util.List;

public class BoardSimpleFactory {
    public static Board getSimpleBoardObject() {
        List<IBoardObject> boardObjects = Arrays.asList(new Snake(1, 2, 0, 0),
                new Ladder(1, 1, 2, 2));

        return new Board(3, 3, boardObjects);
    }
}
