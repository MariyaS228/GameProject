package Masha;

import Masha.Stones.Stone;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        Board board = new Board();
        GameLoop.GameType type;
        boolean isPlayerFox = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Human VS Human or Human VS Bot or Bot vs Bot hh/hb/bb");
        switch (scanner.nextLine()){
            case "hh": type = GameLoop.GameType.HUMAN_VS_HUMAN;
            break;
            case "hb": type = GameLoop.GameType.HUMAN_VS_BOT;
            break;
            case "bb": type = GameLoop.GameType.BOT_VS_BOT;
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + scanner.nextLine());
        }

        if (type == GameLoop.GameType.HUMAN_VS_BOT){
            System.out.println("Play as Fox or Geese f/g");
            switch (scanner.nextLine()){
                case "f": isPlayerFox = true;
                    break;
                case "g": isPlayerFox = false;
                    break;
            }
        }



        GameLoop loop = new GameLoop(board, type, isPlayerFox);
        loop.start();


    }
}
