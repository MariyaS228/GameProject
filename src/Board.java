public class Board {

    Point[][] board = new Point[7][7];


    // i высота
    // j ширина
    Board(){

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if((i < 2 && j < 2) || (i < 2 && j > 4) || (i > 4 && j < 2) || (i > 4 && j > 4)){
                    board[i][j] = new Point(i,j, Point.NULL());
                }

                if((i < 2 && j > 1 && j < 5) || ((i > 1 && i < 4) && (j > 0 && j < 6))){
                    board[i][j] = new Point(i,j,Point.EMPTY());
                }

                if( (i > 1 && i < 4) && (j < 1 || j > 5) ){
                    board[i][j] = new Point(i,j, Point.HAS_GOOSE());
                }

                if( (i == 4) && (j <= 6) || (i > 4 && j > 1 && j < 5) ){
                    board[i][j] = new Point(i,j,Point.HAS_GOOSE());
                }

                if((i == 2) && (j == 3)){
                   board[i][j] = new Point(i, j, Point.HAS_FOX()) ; //я сделала лису)
                }


            }
        }
    }



    public boolean canMoveTo(Point from, Point to){
        if(to.getState() == Point.EMPTY() && (Math.abs((from.getX() - to.getX())) == 1 && from.getY() == to.getY()) ||
                                             (Math.abs((from.getY() - to.getY())) == 1 && from.getX() == to.getX())){
            return true;
        }
        for (int i = 0; i < 5; i++) {

            if (from.getX() == board[6 - i][4 - i].getX() && from.getY() == board[6 - i][4 - i].getY()) {

                if (to.getState() == Point.EMPTY() &&
                        ((to.getX() == from.getX() + 1) && (to.getY() == from.getY() + 1) ||
                        (to.getX() == from.getX() - 1) && (to.getY() == from.getY() - 1))) { //TODO: убрать это для гусей т.к они не ходят назад
                        return true;
                }
            }
        }


        return false;
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
    }
}
