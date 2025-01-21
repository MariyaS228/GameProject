package Masha.Strategy;

import Masha.Board;
import Masha.RoBoard;
import Masha.Stones.Stone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot implements  Strategy{

    @Override
    public Stone.Direction foxMove(RoBoard board, Stone fox, List<Stone> geese) {
        Random random = new Random();
        List<Stone.Direction> directions = new ArrayList<>() ;
        for(Stone.Direction direction : Stone.Direction.values()){
           if ( board.canMoveDirection(board.getPoint(fox.getX(), fox.getY()), direction) ||  board.canEatDirection(board.getPoint(fox.getX(), fox.getY()), direction)){
               directions.add(direction);
           };
        }
        return directions.get(random.nextInt(directions.size()));
    }

    @Override
    public DirGeeseId geeseMove(RoBoard board, List<Stone> geese) {
        //массив доступных гусей (те гуси которые могут куда-то пойти) чтобы ии сделал ход одним из них
        List<Stone> availableGeeseArray = new ArrayList<>();
        //проходимся по каждому гусю
        for (Stone goose : geese){
            //по кажому направлению
            for (Stone.Direction direction : Stone.Direction.values()){
                //если этот гусь может хоть куда-то двинуться то добавляем в массив доступных гусей
                if(board.canMoveDirection(board.getPoint(goose.getX(), goose.getY()),direction)){
                    if(!availableGeeseArray.contains(goose)){
                        availableGeeseArray.add(goose);
                    }
                }
            }
        }
        Random random = new Random();
        //из массива доступных гусей берем рандомного (от 0 до размера массива число) и вызываем .move() в рандомном направлении из
        Stone currentGoose = availableGeeseArray.get(random.nextInt(availableGeeseArray.size()));
        List<Stone.Direction> availableDirs = new ArrayList<>();
        for (Stone.Direction direction : Stone.Direction.values()){
            if(board.canMoveDirection(board.getPoint(currentGoose.getX(),currentGoose.getY()),direction)) availableDirs.add(direction);
        }

       // System.out.println(currentGoose.getX() +" "+ currentGoose.getY());
             return  new DirGeeseId(currentGoose,availableDirs.get(random.nextInt(availableDirs.size())));
//        currentGoose.move(availableDirs.get(random.nextInt(availableDirs.size())),board);
    }
}
