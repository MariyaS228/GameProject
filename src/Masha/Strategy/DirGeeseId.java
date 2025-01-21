package Masha.Strategy;

import Masha.Stones.Goose;
import Masha.Stones.Stone;

public class DirGeeseId {
    private Stone goose;
    private Stone.Direction direction;

    public DirGeeseId(Stone goose, Stone.Direction direction){
        this.goose = goose;
        this.direction = direction;
    }

    public Stone getGoose(){
        return goose;
    }
    public Stone.Direction getDirection(){
        return direction;
    }
}
