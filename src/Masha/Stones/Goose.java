package Masha.Stones;

import Masha.Board;
import Masha.Point;

public class Goose implements Stone {
    private int x;
    private int y;

    public Goose(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }


    //TODO implement this
    public boolean canMoveTo(Point from, Point to){

        return true;
    }

    //TODO implement this
    //the idea is that after every move the app prints a set of available moves e.g
    // [UP / DOWN / UP-RIGHT / LEFT] and depending on what the player types, the goose moves that way
    public void move(Direction direction, Board board){

    }


}
