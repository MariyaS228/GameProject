package Masha;

import Masha.Logs.LogDirector;
import Masha.Stones.Stone;


public class Main {
    public static void main(String[] args) {

        System.out.println("БУДУЩАЯ ИГРА \n");
        Board board = new Board();
//        board.showBoardNumbers();
//        System.out.println();
//        board.showBoardStars();
        GameLoop loop = new GameLoop(board);
        loop.start();



        //logging
        LogDirector.log();




    }
}
