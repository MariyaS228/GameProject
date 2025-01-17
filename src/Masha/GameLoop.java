package Masha;

import Masha.Logs.LogDirector;
import Masha.Stones.Fox;
import Masha.Stones.Stone;

import java.util.Scanner;

public class GameLoop {

    private Board board;
    private boolean isGameOn;
    private final Scanner scanner = new Scanner(System.in);
    private Fox fox = new Fox(2,3);

    public GameLoop(Board board){
        this.board = board;
        isGameOn = true;

    }

    //TODO maybe add some settings for the loop
    public void start(){

        while(isGameOn){
            board.showBoardStars();
            System.out.println("You can move: ");
            for (int i = 0; i < 8; i++) {
                if(board.canMoveDirection( board.getBoard()[fox.getX()][fox.getY()], Stone.Direction.values()[i])){
                    System.out.print(Stone.Direction.values()[i] + " / ");
                }
            }
            System.out.println();

            Stone.Direction direction;
            switch (scanner.nextLine().toLowerCase()){
                case "up": direction = Stone.Direction.UP;
                    break;
                case "down": direction = Stone.Direction.DOWN;
                    break;
                case "left": direction = Stone.Direction.LEFT;
                    break;
                case "right": direction = Stone.Direction.RIGHT;
                    break;
                case "up_left": direction = Stone.Direction.UP_LEFT;
                    break;
                case "up_right": direction = Stone.Direction.UP_RIGHT;
                    break;
                case "down_left": direction = Stone.Direction.DOWN_LEFT;
                    break;
                case "down_right": direction = Stone.Direction.DOWN_RIGHT;
                    break;
                default: direction = null;
            }


            assert direction != null;
            fox.move(direction, board);



            //clean the screen
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

    }
}
