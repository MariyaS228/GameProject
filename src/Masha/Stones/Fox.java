package Masha.Stones;

import Masha.Board;
import Masha.Point;

public class Fox implements Stone{
    private int x;
    private int y;

    public Fox(int x, int y){
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
    // [UP / DOWN / UP-RIGHT / LEFT] and depending on what the player types, the fox moves that way
    //ALSO IMPLEMENT THAT WHEN THERES A GOOSE IN THE DIRECTION YOU WANT TO MOVE TO YOU AUTOMATICALLY EAT IT
    public void move(Direction direction, Board board){

        switch (direction){
            case UP: moveUp(board);
            break;
            case DOWN: moveDown(board);
            break;
            case LEFT: moveLeft(board);
            break;
            case RIGHT: moveRight(board);
            break;
            case UP_LEFT: moveUpLeft(board);
                break;
            case UP_RIGHT: moveUpRight(board);
                break;
            case DOWN_LEFT: moveDownLeft(board);
                break;
            case DOWN_RIGHT: moveDownRight(board);
                break;

        }


    }
    private void moveUp(Board board){
        if(!outOfBounds(x-1,y,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x-1][y])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x-1,y,Point.HAS_FOX());
            x -= 1;
            board.update(board);
        }
    }
    private void moveDown(Board board){
        if(!outOfBounds(x+1,y,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x+1][y])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x+1,y,Point.HAS_FOX());
            x += 1;
            board.update(board);
        }
    }
    private void moveLeft(Board board){
        if(!outOfBounds(x,y-1,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x][y-1])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x,y-1,Point.HAS_FOX());
            y -= 1;
            board.update(board);
        }
    }
    private void moveRight(Board board){
        if(!outOfBounds(x,y+1,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x][y+1])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x,y+1,Point.HAS_FOX());
            y += 1;
            board.update(board);
        }
    }

   private void moveUpLeft(Board board){
        if(!outOfBounds(x-1,y-1,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x-1][y-1])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x-1,y-1,Point.HAS_FOX());
            x -= 1;
            y -= 1;
            board.update(board);
        }
    }

   private void moveUpRight(Board board){
        if(!outOfBounds(x-1,y+1,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x-1][y+1])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x-1,y+1,Point.HAS_FOX());
            x -= 1;
            y += 1;
            board.update(board);
        }
    }

   private void moveDownLeft(Board board){
        if(!outOfBounds(x+1,y-1,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x+1][y-1])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x+1,y-1,Point.HAS_FOX());
            x += 1;
            y -= 1;
            board.update(board);
        }
    }

   private void moveDownRight(Board board){
        if(!outOfBounds(x+1,y+1,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x+1][y+1])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x+1,y+1,Point.HAS_FOX());
            x += 1;
            y += 1;
            board.update(board);
        }
    }

    private boolean outOfBounds(int x, int y, Board board){
        return x < 0 || y < 0 || x >= board.getBoard().length || y >= board.getBoard().length;
    }


}
