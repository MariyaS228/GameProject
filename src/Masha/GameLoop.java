package Masha;

import Masha.Stones.Fox;
import Masha.Stones.Goose;
import Masha.Stones.Stone;
import Masha.Strategy.Bot;
import Masha.Strategy.DirGeeseId;
import Masha.Strategy.Player;
import Masha.Strategy.Strategy;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameLoop {

    private Board board;
    private boolean isGameOn;
    private boolean foxTurn = true;
    private final Scanner scanner = new Scanner(System.in);
    private final Stone fox = new Fox(2,3);

    private final Stone goose1 = new Goose(2,0);
    private final Stone goose2 = new Goose(2,6);
    private final Stone goose3 = new Goose(3,0);
    private final Stone goose4 = new Goose(3,6);
    private final Stone goose5 = new Goose(4,0);
    private final Stone goose6 = new Goose(4,1);
    private final Stone goose7 = new Goose(4,2);
    private final Stone goose8 = new Goose(4,3);
    private final Stone goose9 = new Goose(4,4);
    private final Stone goose10 = new Goose(4,5);
    private final Stone goose11 = new Goose(4,6);
    private final Stone goose12 = new Goose(5,2);
    private final Stone goose13 = new Goose(5,3);
    private final Stone goose14 = new Goose(5,4);
    private final Stone goose15 = new Goose(6,2);
    private final Stone goose16 = new Goose(6,3);
    private final Stone goose17 = new Goose(6,4);

    private List<Stone> geeseArray = new ArrayList<>();

    Strategy player1;
    Strategy player2;
    enum GameType{
        HUMAN_VS_BOT, HUMAN_VS_HUMAN, BOT_VS_BOT
    }

    public GameLoop(Board board, GameType type, boolean isPlayerFox){
        this.board = board;
        isGameOn = true;
        geeseArray.add(goose1);
        geeseArray.add(goose2);
        geeseArray.add(goose3);
        geeseArray.add(goose4);
        geeseArray.add(goose5);
        geeseArray.add(goose6);
        geeseArray.add(goose7);
        geeseArray.add(goose8);
        geeseArray.add(goose9);
        geeseArray.add(goose10);
        geeseArray.add(goose11);
        geeseArray.add(goose12);
        geeseArray.add(goose13);
        geeseArray.add(goose14);
        geeseArray.add(goose15);
        geeseArray.add(goose16);
        geeseArray.add(goose17);

        if (type == GameType.HUMAN_VS_BOT) {
            if (isPlayerFox){
             player1 = new Player();
             player2 = new Bot();
            }
            else {
                player1 = new Bot();
                player2 = new Player();
            }
        }
        else if (type == GameType.HUMAN_VS_HUMAN) {
            player1 = new Player();
            player2 = new Player();
        }
        else {
            player1 = new Bot();
            player2 = new Bot();
        }
    }

    //TODO maybe add some settings for the loop
    public void start(){
//TODO ТУТ С ГЕМОВЕРОМ ГОВОРИЛИ ЧТОТО ВЫЗЫВАТЬ ЕЕ ТУТ
        while(isGameOn){
            board.showBoardStars();
            if(foxTurn) foxMove();
            else geeseMove();
            foxTurn = !foxTurn;
            if (!canMoveSomeWhere()){ //тут чтобы корректно писалось кто победил гуси или лиса
                gameOver();
            }
            if(geeseArray.isEmpty()){
                gameOver();
            }

            //clean the screen
            clearConsole();
        }
    }

    private void geeseMove() {
        RoBoard roBoard = new RoBoard(board);
        DirGeeseId dirGeeseId = player2.geeseMove(roBoard,geeseArray);
        Stone currentGoose = dirGeeseId.getGoose();
        currentGoose.move(dirGeeseId.getDirection(),board);
    }

    private void foxMove(){
        RoBoard roBoard = new RoBoard(board);
        Stone.Direction direction = player1.foxMove(roBoard,fox,geeseArray);


        if(roBoard.canMoveDirection(roBoard.getPoint(fox.getX(),fox.getY()), direction)) fox.move(direction, board);
        else if(roBoard.canEatDirection(roBoard.getPoint(fox.getX(),fox.getY()), direction)){
            geeseArray = fox.eat(direction, board, geeseArray);

        }
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

    private void clearConsole(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean canMoveSomeWhere() {
        for (int i = 0; i < 8; i++) {
            if (board.canMoveDirection(board.getBoard()[fox.getX()][fox.getY()], Stone.Direction.values()[i]) ||
                    board.canEatDirection(board.getBoard()[fox.getX()][fox.getY()], Stone.Direction.values()[i])) { //если можем кушать либо двигаться то выводим в чат

                return true;
            }
        }
        return false;
    }

    public void gameOver(){
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
