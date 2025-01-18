package Masha;

import Masha.Logs.LogDirector;
import Masha.Stones.Fox;
import Masha.Stones.Goose;
import Masha.Stones.Stone;

import java.io.IOException;
import java.util.Scanner;

public class GameLoop {

    private Board board;
    private boolean isGameOn;
    private boolean foxTurn = true;
    private final Scanner scanner = new Scanner(System.in);
    private final Fox fox = new Fox(2,3);

    private final Goose goose1 = new Goose(2,0);
    private final Goose goose2 = new Goose(2,6);
    private final Goose goose3 = new Goose(3,0);
    private final Goose goose4 = new Goose(3,6);
    private final Goose goose5 = new Goose(4,0);
    private final Goose goose6 = new Goose(4,1);
    private final Goose goose7 = new Goose(4,2);
    private final Goose goose8 = new Goose(4,3);
    private final Goose goose9 = new Goose(4,4);
    private final Goose goose10 = new Goose(4,5);
    private final Goose goose11 = new Goose(4,6);
    private final Goose goose12 = new Goose(5,2);
    private final Goose goose13 = new Goose(5,3);
    private final Goose goose14 = new Goose(5,4);
    private final Goose goose15 = new Goose(6,2);
    private final Goose goose16 = new Goose(6,3);
    private final Goose goose17 = new Goose(6,4);

    private final Goose[] geeseArray = new Goose[]{goose1,goose2,goose3,goose4,goose5,goose6,goose6,goose7,goose8,goose9,goose10,goose11,goose12,goose13,goose14,goose15,goose16,goose17};






    public GameLoop(Board board){
        this.board = board;
        isGameOn = true;
    }

    //TODO maybe add some settings for the loop
    public void start(){

        while(isGameOn){
            board.showBoardStars();
            if(foxTurn) foxMove();
            else geeseMove();
            foxTurn = !foxTurn;


            //clean the screen
            clearConsole();
        }
    }

    private void geeseMove() {
        System.out.println();
        System.out.println("Choose the goose by ID: ");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                // Check if the state of the Point is 1
                if (board.getBoard()[i][j].getState() == Point.HAS_GOOSE()) {
                    System.out.print(board.getBoard()[i][j].getX() + "" + board.getBoard()[i][j].getY() + " "); // Print the Point
                } else if (board.getBoard()[i][j].getState() == Point.EMPTY()) {
                    System.out.print("+  ");
                }else if(board.getBoard()[i][j].getState() == Point.HAS_FOX()){
                    System.out.print("@  ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        Goose currentGoose = getGooseById(scanner.nextInt());
        scanner.nextLine();

        System.out.println("You can move: ");
        for (int i = 0; i < 8; i++) {
            if(Stone.Direction.values()[i]!= Stone.Direction.DOWN &&
                    Stone.Direction.values()[i]!= Stone.Direction.DOWN_LEFT &&
                    Stone.Direction.values()[i]!= Stone.Direction.DOWN_RIGHT &&
                    board.canMoveDirection(board.getBoard()[currentGoose.getX()][currentGoose.getY()], Stone.Direction.values()[i])){
                System.out.print(Stone.Direction.values()[i] + " / ");
            }
        }
        System.out.println();

        Stone.Direction direction;
        switch (scanner.nextLine().toLowerCase()){
            case "up": direction = Stone.Direction.UP;
                break;
            case "left": direction = Stone.Direction.LEFT;
                break;
            case "right": direction = Stone.Direction.RIGHT;
                break;
            case "up_left": direction = Stone.Direction.UP_LEFT;
                break;
            case "up_right": direction = Stone.Direction.UP_RIGHT;
                break;
            default: direction = null;
        }

            currentGoose.move(direction, board);
    }

    private void foxMove(){
        int availableMoves = 0;
        System.out.println("You can move: ");
        for (int i = 0; i < 8; i++) {
            if(board.canMoveDirection( board.getBoard()[fox.getX()][fox.getY()], Stone.Direction.values()[i]) ||
                    board.canEatDirection(board.getBoard()[fox.getX()][fox.getY()], Stone.Direction.values()[i])){
                availableMoves++;
                System.out.print(Stone.Direction.values()[i] + " / ");
            }
        }

        if(availableMoves==0){
            gameOver();
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
        if(board.canMoveDirection(board.getBoard()[fox.getX()][fox.getY()], direction)) fox.move(direction, board);
        else if(board.canEatDirection(board.getBoard()[fox.getX()][fox.getY()], direction)) fox.eat(direction, board);
        if(geeseArray.length==0) gameOver();
    }

    private Goose getGooseById(int id){

        int x = (id-id%10)/10;
        int y = id%10;
        for (Goose goose : geeseArray){
            if(goose.getX() == x && goose.getY() == y) {
                return goose;
            }
        }
        return null;
    }

    private void clearConsole(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void gameOver(){
        isGameOn = false;
        clearConsole();
//        scanner.close();
        board.showBoardStars();
        System.out.print("GAME OVER ");
        if(foxTurn) System.out.print("THE FOX WINS");
        if(!foxTurn) System.out.print("THE GEESE WIN");
        System.out.println();
        System.out.println("Press any key to exit...");
        try {
            System.in.read(); // Wait for the user to press any key
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
