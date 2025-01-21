package Masha.Strategy;

import Masha.Board;
import Masha.RoBoard;
import Masha.Stones.Stone;

import java.util.List;

public interface Strategy {

    // Метод для описания движения лисы
    Stone.Direction foxMove(RoBoard roBoard, Stone fox, List<Stone> geese);

    // Метод для описания движения гусей
    DirGeeseId geeseMove(RoBoard roBoard,  List<Stone> geese);


}
