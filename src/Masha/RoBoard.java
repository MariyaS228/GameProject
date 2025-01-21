package Masha;

import Masha.Stones.Stone;

public class RoBoard {
    private  Board board;

    public RoBoard(Board board){
        this.board = board;
    }

    public void showBoardStars(){
        board.showBoardStars();
    }

    public void showBoardNumbers(){
        board.showBoardNumbers();
    }
    public boolean canMoveTo(Point from, Point to){
        return board.canMoveTo(from,to);
    }
    public boolean canMoveDirection(Point from, Stone.Direction direction){
        return board.canMoveDirection(from, direction);
    }
    public boolean canEatDirection(Point from, Stone.Direction direction){
        return board.canEatDirection(from,direction);
    }
    public int getStateByCords(int x, int y){
        return board.getStateByCords(x,y);
    }
    public Point getPoint(int x, int y){
        return board.getBoard()[x][y];
    }

public class RoPoint{
      private  Point point;

      public RoPoint(Point point){
          this.point = point;
      }

    public int getY() {
        return point.getY();
    }

    public int getX() {
        return point.getX();
    }

    public int[] getCords(){
        return point.getCords();
    }

    public int getState(){
        return point.getState();
    }
}
}

