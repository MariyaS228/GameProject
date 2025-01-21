package Masha.Strategy;

import Masha.Board;
import Masha.GameLoop;
import Masha.Point;
import Masha.RoBoard;
import Masha.Stones.Fox;
import Masha.Stones.Goose;
import Masha.Stones.Stone;

import java.util.List;
import java.util.Scanner;

public class Player implements Strategy {
    @Override
    public Stone.Direction foxMove(RoBoard board, Stone fox, List<Stone> geese) { //тут лист гусей чтобы после пожирания гуся удалять его из массива
        System.out.println("You can move: ");
        for (int i = 0; i < 8; i++) {
            if(board.canMoveDirection( board.getPoint(fox.getX(), fox.getY()), Stone.Direction.values()[i]) ||
                    board.canEatDirection(board.getPoint(fox.getX(),fox.getY()), Stone.Direction.values()[i])){ //если можем кушать либо двигаться то выводим в чат

                System.out.print(Stone.Direction.values()[i] + " / ");
            }
        }

        System.out.println();
        Scanner scanner = new Scanner(System.in);
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
        return direction;
    }

    @Override
    public DirGeeseId geeseMove(RoBoard board,  List<Stone> geese) {
        Stone currentGoose;


        System.out.println();
        System.out.println("Choose the goose by ID: ");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                // Check if the state of the Point is 1
                if (board.getPoint(i,j).getState() == Point.HAS_GOOSE()) {
                    System.out.print(board.getPoint(i,j).getX() + "" + board.getPoint(i,j).getY() + " "); // Print the Point
                } else if (board.getPoint(i,j).getState() == Point.EMPTY()) {
                    System.out.print("+  ");
                }else if(board.getPoint(i,j).getState() == Point.HAS_FOX()){
                    System.out.print("@  ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        Scanner scanner = new Scanner(System.in);
        currentGoose = getGooseById(scanner.nextInt(),geese);
        scanner.nextLine();

        System.out.println("You can move: ");
        for (int i = 0; i < 8; i++) {
            if(Stone.Direction.values()[i]!= Stone.Direction.DOWN &&
                    Stone.Direction.values()[i]!= Stone.Direction.DOWN_LEFT &&
                    Stone.Direction.values()[i]!= Stone.Direction.DOWN_RIGHT &&
                    board.canMoveDirection(board.getPoint(currentGoose.getX(),currentGoose.getY()), Stone.Direction.values()[i])){
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

//        currentGoose.move(direction, board); //полиморфизм

        return new DirGeeseId(currentGoose, direction);
    }


    private Stone getGooseById(int id,List<Stone> geeseArray){

        int x = (id-id%10)/10;
        int y = id%10;
        for (Stone goose : geeseArray){
            if(goose.getX() == x && goose.getY() == y) {
                return goose;
            }
        }
        return null;
    }

}
