package Masha;

import Masha.Stones.Stone;

public class Board {

    private Point[][] board = new Point[7][7];


    // i высота
    // j ширина
    public Board(){

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if((i < 2 && j < 2) || (i < 2 && j > 4) || (i > 4 && j < 2) || (i > 4 && j > 4)){
                    board[i][j] = new Point(i,j, Point.NULL());
                }

                if((i < 2 && j > 1 && j < 5) || ((i > 1 && i < 4) && (j > 0 && j < 6))){
                    board[i][j] = new Point(i,j, Point.EMPTY());
                }

                if( (i > 1 && i < 4) && (j < 1 || j > 5) ){
                    board[i][j] = new Point(i,j, Point.HAS_GOOSE());
                }

                if( (i == 4) && (j <= 6) || (i > 4 && j > 1 && j < 5) ){
                    board[i][j] = new Point(i,j, Point.HAS_GOOSE());
                }

                if((i == 2) && (j == 3)){
                   board[i][j] = new Point(i, j, Point.HAS_FOX()) ; //я сделала лису)
                }


            }
        }
    }


    public Point[][] getBoard() {
        return board;
    }

    public int getStateByCords(int x, int y){
     return board[x][y].getState();
    }


    public boolean canEatDirection(Point from, Stone.Direction direction){
        int x;
        int y;
        int farX;
        int farY;

        switch (direction){
            case UP: x = from.getX() - 1; y = from.getY(); farX = from.getX() - 2; farY = from.getY();
                break;
            case DOWN: x = from.getX() + 1; y = from.getY(); farX = from.getX() + 2; farY = from.getY();
                break;
            case LEFT: x = from.getX(); y = from.getY() - 1; farX = from.getX(); farY = from.getY() - 2;
                break;
            case RIGHT: x = from.getX(); y = from.getY() + 1; farX = from.getX(); farY = from.getY() + 2;
                break;
            case UP_LEFT: x = from.getX() - 1; y = from.getY() - 1; farX = from.getX() - 2; farY = from.getY() - 2;
                break;
            case UP_RIGHT: x = from.getX() - 1; y = from.getY() + 1; farX = from.getX() - 2; farY = from.getY() + 2;
                break;
            case DOWN_LEFT: x = from.getX() + 1; y = from.getY() - 1; farX = from.getX() + 2; farY = from.getY() - 2;
                break;
            case DOWN_RIGHT: x = from.getX() + 1; y = from.getY() + 1; farX = from.getX() + 2; farY = from.getY() + 2;
                break;
            default: x = from.getX(); y = from.getY(); farX = from.getX(); farY = from.getY();
        }

        if(!(x < 0 || y < 0 || x >= board.length || y >= board.length) && !(farX < 0 || farY < 0 || farX >= board.length || farY >= board.length)) {
            Point to = board[x][y];
            Point farTo = board[farX][farY];

            return from.getState() == Point.HAS_FOX() && to.getState() == Point.HAS_GOOSE() && farTo.getState() == Point.EMPTY();
        }
        return false;
    }

    public boolean canMoveDirection(Point from, Stone.Direction direction){
        int x;
        int y;

        switch (direction){
            case UP: x = from.getX() - 1; y = from.getY();
            break;
            case DOWN: x = from.getX() + 1; y = from.getY();
            break;
            case LEFT: x = from.getX(); y = from.getY() - 1;
            break;
            case RIGHT: x = from.getX(); y = from.getY() + 1;
            break;
            case UP_LEFT: x = from.getX() - 1; y = from.getY() - 1;
            break;
            case UP_RIGHT: x = from.getX() - 1; y = from.getY() + 1;
            break;
            case DOWN_LEFT: x = from.getX() + 1; y = from.getY() - 1;
            break;
            case DOWN_RIGHT: x = from.getX() + 1; y = from.getY() + 1;
            break;
            default: x = from.getX(); y = from.getY();
        }

        if(!(x < 0 || y < 0 || x >= board.length || y >= board.length)) {
            Point to = board[x][y];
            return canMoveTo(from, to);
        }
        return false;
    }

    public boolean canMoveTo(Point from, Point to){
        

        return canMoveNonDiagonal(from, to) || canMoveDiagonal(from, to);

    }

    private boolean canMoveNonDiagonal(Point from, Point to){
        if(to.getState() == Point.EMPTY() && ((Math.abs((from.getX() - to.getX())) == 1 && from.getY() == to.getY()) ||
                (Math.abs((from.getY() - to.getY())) == 1 && from.getX() == to.getX()))){
            return true;
        }
        return false;
    }

    private boolean canMoveDiagonal(Point from, Point to){

        for (int i = 0; i < 5; i++) {

            if (from.getX() == board[6 - i][4 - i].getX() && from.getY() == board[6 - i][4 - i].getY()) {
                if (to.getState() == Point.EMPTY() &&
                        ((to.getX() == from.getX() + 1) && (to.getY() == from.getY() + 1) ||
                                (to.getX() == from.getX() - 1) && (to.getY() == from.getY() - 1))) {
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (from.getX() == board[4 - i][4 - i].getX() && from.getY() == board[4 - i][4 - i].getY()) {
                if (to.getState() == Point.EMPTY() &&
                        ((to.getX() == from.getX() + 1) && (to.getY() == from.getY() + 1) ||
                                (to.getX() == from.getX() - 1) && (to.getY() == from.getY() - 1))) {
                    return true;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (from.getX() == board[4 - i][6 - i].getX() && from.getY() == board[4 - i][6 - i].getY()) {
                if (to.getState() == Point.EMPTY() &&
                        ((to.getX() == from.getX() + 1) && (to.getY() == from.getY() + 1) ||
                                (to.getX() == from.getX() - 1) && (to.getY() == from.getY() - 1))) {
                    return true;
                }
            }
        }


        //right to left


        for (int i = 0; i < 5; i++) {
            if (from.getX() == board[4 - i][i].getX() && from.getY() == board[4 - i][i].getY()) {
                if (to.getState() == Point.EMPTY() &&
                        ((to.getX() == from.getX() - 1) && (to.getY() == from.getY() + 1) ||
                                (to.getX() == from.getX() + 1) && (to.getY() == from.getY() - 1))) {
                    return true;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (from.getX() == board[4 - i][i].getX() && from.getY() == board[4 - i][i].getY()) {
                if (to.getState() == Point.EMPTY() &&
                        ((to.getX() == from.getX() - 1) && (to.getY() == from.getY() + 1) ||
                                (to.getX() == from.getX() + 1) && (to.getY() == from.getY() - 1))) {
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (from.getX() == board[4 - i][2 + i].getX() && from.getY() == board[4 - i][2 + i].getY()) {
                if (to.getState() == Point.EMPTY() &&
                        ((to.getX() == from.getX() - 1) && (to.getY() == from.getY() + 1) ||
                                (to.getX() == from.getX() + 1) && (to.getY() == from.getY() - 1))) {
                    return true;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (from.getX() == board[6 - i][2 + i].getX() && from.getY() == board[6 - i][2 + i].getY()) {
                if (to.getState() == Point.EMPTY() &&
                        ((to.getX() == from.getX() - 1) && (to.getY() == from.getY() + 1) ||
                                (to.getX() == from.getX() + 1) && (to.getY() == from.getY() - 1))) {
                    return true;
                }
            }
        }




        return false;
    }




    public void update(Board board){
        this.board = board.getBoard();
    }

    public void setPoint(int x, int y, int state){
        board[x][y].setState(state);
    }


    // interface code

    public void showBoardNumbers(){
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].showCords();
            }
        }
    }
    public void showBoardStars(){
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[0].length; j++) {

                int state = board[i][j].getState();
                if(state == Point.NULL()){
                    System.out.print("  ");
                }
                if(state == Point.EMPTY()){
                    System.out.print("+ ");
                }
                if(state == Point.HAS_FOX()){
                    System.out.print("@ ");
                }
                if(state == Point.HAS_GOOSE()){
                    System.out.print("& ");
                }

            }
        }
        System.out.println();
    }
}
