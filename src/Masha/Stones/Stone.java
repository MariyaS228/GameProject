package Masha.Stones;

import Masha.Board;
import Masha.Point;

public interface Stone {



    enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT,
        UP_LEFT,
        UP_RIGHT,
        DOWN_LEFT,
        DOWN_RIGHT
    }

    int getX();
    int getY();
    void setX(int x);
    void setY(int y);

    boolean canMoveTo(Point from, Point to);

    //the idea is that after every move the app prints a set of available moves e.g
    // [UP / DOWN / UP-RIGHT / LEFT] and depending on what the player types, the fox moves that way
    void move(Direction direction, Board board);

}
