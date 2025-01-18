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

    public void move(Direction direction, Board board){

        switch (direction){
            case UP: moveUp(board);
                break;
            case LEFT: moveLeft(board);
                break;
            case RIGHT: moveRight(board);
                break;
            case UP_LEFT: moveUpLeft(board);
                break;
            case UP_RIGHT: moveUpRight(board);
                break;

        }


    }
    private void moveUp(Board board) {
        if (!outOfBounds(x - 1, y, board) && board.canMoveTo(board.getBoard()[x][y], board.getBoard()[x - 1][y])) {

            board.setPoint(x, y, Point.EMPTY());
            board.setPoint(x - 1, y, Point.HAS_GOOSE());
            x -= 1;
            board.update(board);
        }
    }
    private void moveLeft(Board board){
        if(!outOfBounds(x,y-1,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x][y-1])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x,y-1,Point.HAS_GOOSE());
            y -= 1;
            board.update(board);
        }
    }
    private void moveRight(Board board){
        if(!outOfBounds(x,y+1,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x][y+1])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x,y+1,Point.HAS_GOOSE());
            y += 1;
            board.update(board);
        }
    }

    private void moveUpLeft(Board board){
        if(!outOfBounds(x-1,y-1,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x-1][y-1])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x-1,y-1,Point.HAS_GOOSE());
            x -= 1;
            y -= 1;
            board.update(board);
        }
    }

    private void moveUpRight(Board board){
        if(!outOfBounds(x-1,y+1,board) && board.canMoveTo(board.getBoard()[x][y],board.getBoard()[x-1][y+1])){

            board.setPoint(x,y,Point.EMPTY());
            board.setPoint(x-1,y+1,Point.HAS_GOOSE());
            x -= 1;
            y += 1;
            board.update(board);
        }
    }

    private boolean outOfBounds(int x, int y, Board board){
        return x < 0 || y < 0 || x >= board.getBoard().length || y >= board.getBoard().length;
    }


}
