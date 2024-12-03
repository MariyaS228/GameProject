import java.util.Arrays;

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

                if((i < 2 && j > 1 && j < 5) || ((i > 1 && i < 4) && (j > 0 && j < 6))){ //TODO: не забыть лису
                    board[i][j] = new Point(i,j,Point.EMPTY());
                }

                if( (i > 1 && i < 4) && (j < 1 || j > 5) ){
                    board[i][j] = new Point(i,j, Point.HAS_GOOSE());
                }

                if( (i > 3 && i < 5) && (j <= 6) || (i > 4 && j > 1 && j < 5) ){
                    board[i][j] = new Point(i,j,Point.HAS_GOOSE());
                }

                if((i > 1 && i < 3) && (j > 2 && j < 4)){
                   board[i][j] = new Point(i, j, Point.HAS_FOX()) ; //я сделала лису)
                }

            }
        }
    }

    public void showBoard(){
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].showCords();
            }
        }
    }
}
